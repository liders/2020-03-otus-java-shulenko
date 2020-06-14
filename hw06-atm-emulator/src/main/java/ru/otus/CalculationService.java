package ru.otus;

import ru.otus.model.Banknote;
import ru.otus.model.Nominal;

import java.util.*;
import java.util.stream.Collectors;

public class CalculationService {
    private final BlockCellsService blockCellsService;

    public CalculationService(BlockCellsService blockCellsService) {
        this.blockCellsService = blockCellsService;
    }

    public void acceptBanknotes(List<Banknote> banknotes) throws Exception {
        for (Banknote banknote: banknotes) {
            acceptBanknote(banknote);
        }
    }

    public void acceptBanknote(Banknote banknote) throws Exception {
        blockCellsService.setBanknote(banknote);
    }

    public int getBalance() {
        int balance = 0;
        for (int n: blockCellsService.getAvailableNominalValues()) {
            balance += n * blockCellsService.getBanknotesInCellByNominal(Nominal.valueOf(n)).size();
        }
        return balance;
    }

    public List<Banknote> giveAmountMinimumNumberBanknotes(int amount) throws Exception {
        Map<Nominal, Integer> map = findNominalAndBanknotesByAmount(amount);
        List<Banknote> banknotes = new ArrayList<>();
        List<Nominal> reverseOrderNominalValues = map.keySet()
                .stream()
                .sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (Nominal n: reverseOrderNominalValues) {
            for (int i = 0; i < map.get(n); i++) {
                Banknote banknote = blockCellsService.removeBanknoteByNominal(n);
                banknotes.add(banknote);
            }
        }
        return banknotes;
    }

    private Map<Nominal, Integer> findNominalAndBanknotesByAmount(int amount) throws Exception {
        Map<Nominal, Integer> map = new HashMap<>();
        for (int n: blockCellsService.getAvailableNominalValues()) {
            if (amount >= n) {
                int k = amount / n;
                int size = blockCellsService.getBanknoteSizeByNominal(Nominal.valueOf(n));
                if (size >= k) {
                    amount = amount - k * n;
                    map.put(Nominal.valueOf(n), k);
                }
            }
        }
        if (amount > 0) {
            throw new Exception("Impossible to give out the requested amount");
        }
        return map;
    }
}

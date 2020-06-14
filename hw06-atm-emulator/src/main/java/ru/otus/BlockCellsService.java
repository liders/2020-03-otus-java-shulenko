package ru.otus;

import ru.otus.model.Banknote;
import ru.otus.model.Cell;
import ru.otus.model.Nominal;

import java.util.*;
import java.util.stream.Collectors;

import static ru.otus.model.Nominal.*;

public class BlockCellsService {
    private static final List<Nominal> AVAILABLE_NOMINAL_VALUES = Arrays.asList(N_100, N_500, N_1000, N_5000);
    private final Map<Nominal, Cell> cells = initCells();
    private static Map<Nominal, Cell> initCells() {
        Map<Nominal, Cell> cells = new HashMap<>();
        for (Nominal n: AVAILABLE_NOMINAL_VALUES) {
            cells.put(n, new Cell());
        }
        return cells;
    }

    public List<Banknote> getBanknotesInCellByNominal(Nominal nominal) {
        return cells.get(nominal).getBanknotes();
    }

    public List<Integer> getAvailableNominalValues() {
        return AVAILABLE_NOMINAL_VALUES.stream()
                .map(Nominal::getValue)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public int getBanknoteSizeByNominal(Nominal nominal) {
        return getBanknotesInCellByNominal(nominal).size();
    }

    public Banknote removeBanknoteByNominal(Nominal nominal) {
        List<Banknote> banknotes = getBanknotesInCellByNominal(nominal);
        return banknotes.remove(banknotes.size() - 1);
    }

    public void setBanknote(Banknote banknote) throws Exception {
        Nominal banknoteNominal = banknote.getNominal();
        if (!isAvailableNominal(banknoteNominal)) {
            throw new Exception("ATM doest not accept banknote nominal: " + banknoteNominal);
        }

        List<Banknote> banknotes = getBanknotesInCellByNominal(banknoteNominal);
        banknotes.add(banknote);
    }

    private boolean isAvailableNominal(Nominal nominal) {
        return cells.containsKey(nominal);
    }
}

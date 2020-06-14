package ru.otus;

import ru.otus.model.Banknote;

import java.util.List;

public class ATM {
    private final CalculationService calculationService;

    public ATM() {
        BlockCellsService blockCellsService = new BlockCellsService();
        calculationService = new CalculationService(blockCellsService);
    }

    public void accept(List<Banknote> banknotes) throws Exception {
        calculationService.acceptBanknotes(banknotes);
    }

    public List<Banknote> giveOut(Integer amount) throws Exception {
        return calculationService.giveAmountMinimumNumberBanknotes(amount);
    }

    public Integer getBalanceAmount() {
        return calculationService.getBalance();
    }
}

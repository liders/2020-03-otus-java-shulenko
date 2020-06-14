package ru.otus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.model.Banknote;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static ru.otus.model.Nominal.*;

class CalculationServiceTest {
    private BlockCellsService blockCellsService;

    private CalculationService calculationService;
    private static final List<Banknote> BANKNOTES_100_X_7 = Arrays.asList(
            new Banknote(N_100),
            new Banknote(N_100),
            new Banknote(N_100),
            new Banknote(N_100),
            new Banknote(N_100),
            new Banknote(N_100),
            new Banknote(N_100),
            new Banknote(N_100),
            new Banknote(N_100));

    @BeforeEach
    void setUp() {
        blockCellsService = mock(BlockCellsService.class);
        calculationService = new CalculationService(blockCellsService);
    }

    @Test
    void shouldAcceptOneBanknote() throws Exception {
        doNothing().when(blockCellsService).setBanknote(isA(Banknote.class));
        calculationService.acceptBanknotes(Collections.singletonList(new Banknote(N_100)));
        verify(blockCellsService, times(1)).setBanknote(new Banknote(N_100));
    }

    @Test
    void shouldAcceptTwoBanknotes() throws Exception {
        doNothing().when(blockCellsService).setBanknote(isA(Banknote.class));
        calculationService.acceptBanknotes(Arrays.asList(new Banknote(N_100), new Banknote(N_100)));
        verify(blockCellsService, times(2)).setBanknote(new Banknote(N_100));
    }

    @Test
    void shouldAcceptTwoDiffBanknotes() throws Exception {
        doNothing().when(blockCellsService).setBanknote(isA(Banknote.class));
        calculationService.acceptBanknotes(Arrays.asList(new Banknote(N_100), new Banknote(N_500)));
        verify(blockCellsService, times(1)).setBanknote(new Banknote(N_100));
        verify(blockCellsService, times(1)).setBanknote(new Banknote(N_500));
    }

    @Test()
    void shouldThrowExceptionAcceptBanknotes() throws Exception {
        doThrow(Exception.class).when(blockCellsService).setBanknote(isA(Banknote.class));
        assertThrows(Exception.class, () -> calculationService.acceptBanknotes(Collections.singletonList(new Banknote(N_10))));
    }

    @Test
    void shouldGetBalance() {
        when(blockCellsService.getAvailableNominalValues()).thenReturn(Arrays.asList(5000, 1000, 500, 100));
        when(blockCellsService.getBanknotesInCellByNominal(N_5000)).thenReturn(Collections.emptyList());
        when(blockCellsService.getBanknotesInCellByNominal(N_1000)).thenReturn(Arrays.asList(new Banknote(N_1000), new Banknote(N_1000), new Banknote(N_1000)));
        when(blockCellsService.getBanknotesInCellByNominal(N_500)).thenReturn(Arrays.asList(new Banknote(N_500), new Banknote(N_500)));
        when(blockCellsService.getBanknotesInCellByNominal(N_100)).thenReturn(BANKNOTES_100_X_7);

        assertThat(calculationService.getBalance()).isEqualTo(4900);
    }

    @Test
    void shouldGiveAmountMinimumNumberBanknotes() throws Exception {
        when(blockCellsService.getAvailableNominalValues()).thenReturn(Arrays.asList(5000, 1000, 500, 100));
        when(blockCellsService.getBanknoteSizeByNominal(N_5000)).thenReturn(0);
        when(blockCellsService.getBanknoteSizeByNominal(N_1000)).thenReturn(3);
        when(blockCellsService.getBanknoteSizeByNominal(N_500)).thenReturn(2);
        when(blockCellsService.getBanknoteSizeByNominal(N_100)).thenReturn(9);
        when(blockCellsService.removeBanknoteByNominal(N_1000)).thenReturn(new Banknote(N_1000));
        when(blockCellsService.removeBanknoteByNominal(N_500)).thenReturn(new Banknote(N_500));
        when(blockCellsService.removeBanknoteByNominal(N_100)).thenReturn(new Banknote(N_100));

        List<Banknote> banknotes = calculationService.giveAmountMinimumNumberBanknotes(3700);
        assertThat(banknotes).isEqualTo(Arrays.asList(
                new Banknote(N_1000),
                new Banknote(N_1000),
                new Banknote(N_1000),
                new Banknote(N_500),
                new Banknote(N_100),
                new Banknote(N_100)));
    }

    @Test
    void shouldThrowExceptionWhenGetBanknotes() {
        when(blockCellsService.getAvailableNominalValues()).thenReturn(Arrays.asList(5000, 1000, 500, 100));
        when(blockCellsService.getBanknoteSizeByNominal(N_5000)).thenReturn(0);
        when(blockCellsService.getBanknoteSizeByNominal(N_1000)).thenReturn(3);
        when(blockCellsService.getBanknoteSizeByNominal(N_500)).thenReturn(2);
        when(blockCellsService.getBanknoteSizeByNominal(N_100)).thenReturn(9);
        when(blockCellsService.removeBanknoteByNominal(N_1000)).thenReturn(new Banknote(N_1000));
        when(blockCellsService.removeBanknoteByNominal(N_500)).thenReturn(new Banknote(N_500));
        when(blockCellsService.removeBanknoteByNominal(N_100)).thenReturn(new Banknote(N_100));

        assertThrows(Exception.class, () -> calculationService.giveAmountMinimumNumberBanknotes(123456));
    }
}
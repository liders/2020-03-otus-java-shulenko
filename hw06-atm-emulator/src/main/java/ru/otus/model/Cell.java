package ru.otus.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cell {
    private final List<Banknote> banknotes;

    public Cell(List<Banknote> banknotes) {
        this.banknotes = banknotes;
    }

    public Cell() {
        this.banknotes = new ArrayList<>();
    }

    public List<Banknote> getBanknotes() {
        return banknotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return banknotes.equals(cell.banknotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(banknotes);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "banknotes=" + banknotes +
                '}';
    }
}

package ru.otus;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class DIYArrayListTest {
    private static final String DIY = "DIY";
    private static final String ARRAYLIST = "ArrayList";
    List<Integer> list;
    List<String> stringsList;

    @BeforeEach
    void setUp() {
        list = new DIYArrayList<>();
        stringsList = new DIYArrayList<>();
    }

    @Test
    void shouldListSizeZero() {
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    void shouldListSizeOne() {
        Collections.addAll(list, 7);
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void shouldListSizeMoreZero() {
        Collections.addAll(list, 1, 3, 5, 7, 9, 11, 13);
        assertThat(list.size()).isEqualTo(7);
    }

    @Test
    void shouldListSize21() {
        Collections.addAll(list, 1, 3, 5, 7, 9, 11, 13, 1, 3, 5, 7, 9, 11, 13,
                1, 3, 5, 7, 9, 11, 13);
        assertThat(list.size()).isEqualTo(21);
    }

    @Test
    void shouldAddAndGetStringElements() {
        stringsList.add(DIY);
        stringsList.add(ARRAYLIST);
        assertThat(stringsList.get(0)).isEqualTo(DIY);
        assertThat(stringsList.get(1)).isEqualTo(ARRAYLIST);
    }

    @Test
    void shouldSort() {
        DIYArrayList<Integer> list = new DIYArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.sort(Comparator.reverseOrder());
        assertThat(list).isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    void shouldSortWithCollectionsSort() {
        Collections.addAll(list, 10, 2, 5, -1, 3, 1, 2, -3, 6, 10);
        Collections.sort(list, Comparator.reverseOrder());
        assertThat(list).isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    void shouldSortWithCollectionsCopy() {
        List<Integer> copyDiyArrayList = new DIYArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        Collections.addAll(copyDiyArrayList, -100, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12,
                -13, -14, -15, -16, -17, -18, -19, -20);
        assertThat(list.size()).isEqualTo(20);
        assertThat(copyDiyArrayList.size()).isEqualTo(21);

        Collections.copy(copyDiyArrayList, list);

        list.add(-20);
        for (int i = 0; i < copyDiyArrayList.size(); i++) {
            assertThat(copyDiyArrayList.get(i)).isEqualTo(list.get(i));
        }
    }
}
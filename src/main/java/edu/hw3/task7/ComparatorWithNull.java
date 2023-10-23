package edu.hw3.task7;

import java.util.Comparator;

public class ComparatorWithNull<T extends Comparable<T>> implements Comparator<T> {
    /**
     * Все null-элементы добавляются в конце TreeMap,
     * остальные элементы сортируются по возрастанию
     */
    @Override
    public int compare(T o1, T o2) {
        if (o1 == null) {
            return 1;
        } else if (o2 == null) {
            return -1;
        } else {
            return o1.compareTo(o2);
        }
    }
}

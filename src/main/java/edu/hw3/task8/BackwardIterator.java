package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;

public class BackwardIterator<E, T extends List<E>> implements Iterator<E> {
    /*
     * Преподаватель сказал ограничиться только индексными коллекциями
     */
    private final T elements;
    private int currentIndex;

    public BackwardIterator(T elements) {
        if (elements == null || elements.isEmpty()) {
            throw new IllegalArgumentException("Коллекция пустая!");
        }
        this.elements = elements;
        currentIndex = elements.size();
    }

    @Override
    public boolean hasNext() {
        return currentIndex != 0;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new ArrayIndexOutOfBoundsException("Достигнуто начало коллекции!");
        }
        currentIndex--;
        return elements.get(currentIndex);
    }
}

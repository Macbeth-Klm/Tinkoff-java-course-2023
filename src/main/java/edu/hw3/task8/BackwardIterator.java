package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;

public class BackwardIterator<E, T extends List<E>> implements Iterator<E> {
    private final T elements;
    private int currentIndex;

    public BackwardIterator(T elements) {
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
            throw new ArrayIndexOutOfBoundsException("Данного элемента не существует!");
        }
        currentIndex--;
        return elements.get(currentIndex);
    }
}

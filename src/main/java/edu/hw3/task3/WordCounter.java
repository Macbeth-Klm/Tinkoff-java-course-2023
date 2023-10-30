package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class WordCounter {
    private WordCounter() {
    }

    public static <T> Map<T, Integer> freqDict(List<T> inputList) {
        if (inputList == null || inputList.isEmpty()) {
            throw new IllegalArgumentException("Список пустой!");
        }
        Map<T, Integer> result = new HashMap<>();
        for (T el : inputList) {
            result.merge(el, 1, Integer::sum);
        }
        return result;
    }
}

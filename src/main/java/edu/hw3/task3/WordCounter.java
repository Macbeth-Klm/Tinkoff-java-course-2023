package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class WordCounter {
    private WordCounter() {
    }

    public static <T> Map<T, Integer> freqDict(List<T> inputList) {
        if (inputList == null || inputList.isEmpty()) {
            throw new NullPointerException("Список пустой!");
        }
        Map<T, Integer> result = new HashMap<>();
        for (T el : inputList) {
            int count = 1;
            if (result.containsKey(el)) {
                count += result.get(el);
            }
            result.put(el, count);
        }
        return result;
    }
}

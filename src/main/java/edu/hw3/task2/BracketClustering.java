package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;

public final class BracketClustering {
    private BracketClustering() {
    }

    public static List<String> clusterize(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            throw new NullPointerException("Строка пустая!");
        }
        char[] inputToChars = inputString.toCharArray();
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder(inputString.length());
        int bracketsCount = 0;
        for (char el : inputToChars) {
            switch (el) {
                case '(':
                    bracketsCount++;
                    break;
                case ')':
                    bracketsCount--;
                    break;
                default:
                    throw new IllegalArgumentException("Строка некорректного формата!");
            }
            sb.append(el);
            if (bracketsCount == 0) {
                result.add(sb.toString());
                sb.delete(0, sb.length());
            } else if (bracketsCount < 0) {
                throw new IllegalArgumentException("Кластер несбалансированный!");
            }
        }
        return result;
    }
}

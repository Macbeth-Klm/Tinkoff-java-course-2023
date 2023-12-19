package edu.hw10.task2.sum;

import java.util.Arrays;

public class SumIntegersClass implements SumIntegers {
    @Override
    public int sum(int... numbers) {
        return Arrays.stream(numbers).sum();
    }
}

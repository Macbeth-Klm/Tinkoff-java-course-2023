package edu.hw10.task2.sum;

import edu.hw10.task2.Cache;

public interface SumIntegers {
    @Cache(persist = false)
    int sum(int... numbers);
}

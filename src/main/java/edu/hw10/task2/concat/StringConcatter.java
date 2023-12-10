package edu.hw10.task2.concat;

import edu.hw10.task2.Cache;

public interface StringConcatter {
    @Cache(persist = true)
    String concat(String... strings);
}

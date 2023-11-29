package edu.hw7.task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AtomicValueIncrementorTest {

    @ParameterizedTest
    @ValueSource(ints = {51194, 175803, 1980345, 100000297})
    public void shouldReturnCorrectValueFirst(int requiredValue) {
        int startValue = 0;

        int result = AtomicValueIncrementor.incrementValue(startValue, requiredValue);

        assertEquals(result, requiredValue);
    }
}

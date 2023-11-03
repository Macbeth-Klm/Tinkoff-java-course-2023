package edu.hw3.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class AtbashCipherToolTest {
    @Test
    void shouldConvertTheStringToAtbashCipherFirst() {
        String inputString = "Hello world!";

        String result = AtbashCipherTool.atbash(inputString);

        assertEquals("Svool dliow!", result);
    }

    @Test
    void shouldConvertTheStringToAtbashCipherSecond() {
        String inputString = "Any fool can write code that a computer can understand." +
            " Good programmers write code that humans can understand. ― Martin Fowler";

        String result = AtbashCipherTool.atbash(inputString);

        assertEquals("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. " +
            "Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi", result);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = "")
    void shouldThrowExceptionBecauseOfEmptyString(String inputString) {
        IllegalArgumentException resultException =
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                String result = AtbashCipherTool.atbash(inputString);
            });

        Assertions.assertEquals("Строка пустая!", resultException.getMessage());
    }
}

package edu.hw3.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AtbashCipherToolTest {

    @Test
    void atbashTestFirst() {
        // given
        String inputString = "Hello world!";

        // when
        String result = AtbashCipherTool.atbash(inputString);

        // then

        assertEquals("Svool dliow!", result);
    }

    @Test
    void atbashTestSecond() {
        // given
        String inputString = "Any fool can write code that a computer can understand." +
            " Good programmers write code that humans can understand. ― Martin Fowler";

        // when
        String result = AtbashCipherTool.atbash(inputString);

        // then

        assertEquals("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. " +
            "Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi", result);
    }

    @Test
    void incorrectInputString() {
        // given
        String inputString = "";

        // when
        IllegalArgumentException resultException =
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                String result = AtbashCipherTool.atbash(inputString);
            });

        // then
        Assertions.assertEquals("Строка некорректного формата!", resultException.getMessage());
    }
}

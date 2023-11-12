package edu.hw5.task6;

import edu.hw5.task6.Task6;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task6Test {

    @Test
    void shouldReturnTrueBecauseOfStringSIsSubstringOfStringT() {
        var s = "abc";
        var t = "achfdbaabgabcaabg";

        var result = Task6.isSubstringWithRegex(s, t);

        assertThat(result)
            .isTrue();
    }

    @Test
    void shouldReturnTrueBecauseOfStringSIsNotSubstringOfStringT() {
        var s = "zxc";
        var t = "achfdbaabgabcaabg";

        var result = Task6.isSubstringWithRegex(s, t);

        assertThat(result)
            .isFalse();
    }
}

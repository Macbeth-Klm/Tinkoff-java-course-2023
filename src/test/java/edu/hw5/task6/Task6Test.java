package edu.hw5.task6;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task6Test {

    @Test
    void shouldReturnTrueBecauseOfStringSIsSubstringOfStringTFirst() {
        var s = "abc";
        var t = "achfdbaabgabcaabg";

        var result = Task6.isSubstringWithRegex(s, t);

        assertThat(result)
            .isTrue();
    }

    @Test
    void shouldReturnTrueBecauseOfStringSIsSubstringOfStringTSecond() {
        var s = "\\s+";
        var t = "achfdbaab\\s+gabcaabg";

        var result = Task6.isSubstringWithRegex(s, t);

        assertThat(result)
            .isTrue();
    }

    @Test
    void shouldReturnFalseBecauseOfStringSIsNotSubstringOfStringTFirst() {
        var s = "zxc";
        var t = "achfdbaabgabcaabg";

        var result = Task6.isSubstringWithRegex(s, t);

        assertThat(result)
            .isFalse();
    }

    @Test
    void shouldReturnFalseBecauseOfStringSIsNotSubstringOfStringTSecond() {
        var s = "\\s";
        var t = "achfdb aab gabc aabg";

        var result = Task6.isSubstringWithRegex(s, t);

        assertThat(result)
            .isFalse();
    }
}

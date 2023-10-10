package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    void minutesToSecondsFirst() {
        // given correct duration
        String videoDuration = "13:56";

        // when
        int durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(836);
    }

    @Test
    void minutesToSecondsSecond() {
        // given input String without ":"
        String videoDuration = "10 60";

        // when
        int durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(-1);
    }

    @Test
    void minutesToSecondsThird() {
        // minutesAndSeconds[1].length() != 2
        String videoDuration = "13:503";

        // when
        int durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(-1);
    }

    @Test
    void minutesToFourth() {
        // given NumberFormatException in String.parseInt()
        String videoDuration = "21324653634232111:60";

        // when
        int durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(-1);
    }

    @Test
    void minutesToSecondsFifth() {
        // given minutes < 0 || seconds < 0
        String videoDuration = "-5:15";

        // when
        int durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(-1);
    }

    @Test
    void minutesToSecondsSixth() {
        // given seconds >= MAX_SECONDS
        String videoDuration = "10:60";

        // when
        int durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(-1);
    }

    @Test
    void minutesToSecondsSeventh() {
        // given (minutes * 60 + seconds) > Integer.MAX_VALUE
        String videoDuration = "90000000:15";

        // when
        int durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(-1);
    }
}

package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {
    @Test
    void minutesToSecondsFirst() {
        // given seconds = 0
        String videoDuration = "10:00";

        // when
        int durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(600);
    }

    @Test
    void minutesToSecondsSecond() {
        // given minutes = 0
        String videoDuration = "00:13";

        // when
        int durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(13);
    }

    @Test
    void minutesToSecondsThird() {
        // given
        String videoDuration = "13:56";

        // when
        int durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(836);
    }

    @Test
    void minutesToSecondsFourth() {
        // given seconds >= MAX_SECONDS
        String videoDuration = "10:60";

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
        // given (minutes * 60 + seconds) > Integer.MAX_VALUE
        String videoDuration = "300000000:15";

        // when
        int durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(-1);
    }

    @Test
    void minutesToSecondsSeventh() {
        // given invalid string
        String videoDuration = "16.40";

        // when
        int durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(-1);
    }
}

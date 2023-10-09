package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {
    @Test
    @DisplayName("Длина видео в секундах")
    void minutesToSeconds() {
        // given duration without seconds
        String videoDuration = "01:00";

        // when
        int durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(60);

        // given duration without minutes
        videoDuration = "00:13";

        // when
        durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(13);

        // given duration with minutes and seconds
        videoDuration = "13:56";

        // when
        durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(836);

        //given (seconds >= 60)
        videoDuration = "10:60";

        // when
        durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(-1);

        // given (minutes < 0 || seconds < 0)
        videoDuration = "-5:15";

        // when
        durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(-1);

        // given (minutes * 60 + seconds) > Integer.MAX_VALUE
        videoDuration = "300000000:15";

        // when
        durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(-1);

        // given invalid string
        videoDuration = "16.40";

        // when
        durationToSeconds = Task1.minutesToSeconds(videoDuration);

        // then
        assertThat(durationToSeconds)
            .isEqualTo(-1);
    }
}

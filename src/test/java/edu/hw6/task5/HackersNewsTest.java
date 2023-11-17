package edu.hw6.task5;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HackersNewsTest {

    @Test
    void shouldReturnHackerNewsTopStories() {
        long[] id = HackersNews.hackerNewsTopStories();
        assertThat(id)
            .hasSize(436);
    }

    @Test
    void getNewsTitleWithId() {
        long[] id = HackersNews.hackerNewsTopStories();

        String storyTitle = HackersNews.news(id[0]);
        assertThat(storyTitle)
            .isEqualTo("The real realtime preemption end game");
    }
}

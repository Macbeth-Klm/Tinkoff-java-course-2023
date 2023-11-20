package edu.hw6.task5;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HackersNewsTest {
    @Test
    void shouldReturnHackerNewsTopStories() {
        long[] id = HackersNews.hackerNewsTopStories();
        assertThat(id)
            .hasSize(492);
    }

    @Test
    void getNewsTitleWithId() {
        long[] id = HackersNews.hackerNewsTopStories();

        String storyTitle = HackersNews.news(id[0]);
        assertThat(storyTitle)
            .isEqualTo("After Boeing declines to pay up, ransomware group leaks 45 GB of data");
    }
}

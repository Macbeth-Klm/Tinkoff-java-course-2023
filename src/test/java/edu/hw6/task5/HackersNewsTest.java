package edu.hw6.task5;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class HackersNewsTest {
    @Test
    void shouldReturnHackerNewsTopStories() {
        long[] id = HackersNews.hackerNewsTopStories();
        assertThat(id)
            .hasSize(500);
    }

    @Test
    void getNewsTitleWithId() {
        long[] id = HackersNews.hackerNewsTopStories();

        String storyTitle = HackersNews.news(id[0]);
        assertThat(storyTitle)
            .isEqualTo("Git Branches: Intuition and Reality");
    }
}

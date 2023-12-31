package edu.hw6.task5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HackersNews {

    private HackersNews() {
    }

    @SuppressWarnings("MagicNumber")
    public static long[] hackerNewsTopStories() {
        var httpRequest = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .GET()
            .timeout(Duration.ofSeconds(10L))
            .build();
        try (var httpClient = HttpClient.newHttpClient()) {
            var response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return parseTopStoriesId(response);
        } catch (Exception e) {
            return new long[] {};
        }
    }

    private static long[] parseTopStoriesId(HttpResponse<String> response) {
        String[] bodyArray = response.body().replaceAll("\\[|\\]", "").split(",");
        long[] result = new long[bodyArray.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Long.parseLong(bodyArray[i]);
        }
        return result;
    }

    @SuppressWarnings("MagicNumber")
    public static String news(long id) {
        var httpRequest = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
            .GET()
            .timeout(Duration.ofSeconds(10L))
            .build();
        try (var httpClient = HttpClient.newHttpClient()) {
            var response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return parseNewTitle(response);
        } catch (Exception e) {
            return null;
        }
    }

    private static String parseNewTitle(HttpResponse<String> response) {
        Pattern pattern = Pattern.compile("\"title\":\"(.+)\",\"type\"");
        Matcher matcher = pattern.matcher(response.body());
        return (matcher.find()) ? matcher.group(1) : null;
    }
}

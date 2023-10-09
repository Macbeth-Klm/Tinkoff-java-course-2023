package edu.hw1;

public class Task1 {
    public static int minutesToSeconds(String input) {
        try {
            if (input.length() > 13 || !input.contains(":")) {
                return -1;
            }

            String[] minutesAndSeconds = input.split(":");
            if (minutesAndSeconds[1].length() != 2) {
                return -1;
            }

            int minutes = Integer.parseInt(minutesAndSeconds[0]);
            int seconds = Integer.parseInt(minutesAndSeconds[1]);
            if (minutes < 0 || seconds < 0) {
                return -1;
            }
            if (seconds >= 60 || ((Integer.MAX_VALUE - seconds) / 60) < minutes) {
                return -1;
            }

            return minutes * 60 + seconds;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

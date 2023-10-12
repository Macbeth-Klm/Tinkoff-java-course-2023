package edu.hw1;

public final class Task1 {
    private static final int MAX_SECONDS = 60;

    private Task1() {
    }

    public static int minutesToSeconds(String input) {
        if (input.contains(":")) {
            String[] minutesAndSeconds = input.split(":");
            if (minutesAndSeconds[1].length() == 2) {
                int minutes;
                int seconds;
                try {
                    minutes = Integer.parseInt(minutesAndSeconds[0]);
                    seconds = Integer.parseInt(minutesAndSeconds[1]);
                } catch (NumberFormatException e) {
                    return -1;
                }
                if (minutes >= 0 && seconds >= 0
                    && seconds < MAX_SECONDS && ((Integer.MAX_VALUE - seconds) / MAX_SECONDS) >= minutes) {
                    return minutes * MAX_SECONDS + seconds;
                }
            }
        }
        return -1;
    }
}

package edu.hw1;

public final class Task1 {
    private final static int MAX_SECONDS = 60;

    private Task1() {
    }

    public static int minutesToSeconds(String input) {
        if (input.contains(":")) {
            String[] minutesAndSeconds = input.split(":");
            if (minutesAndSeconds[1].length() == 2) {
                try {
                    int minutes = Integer.parseInt(minutesAndSeconds[0]);
                    int seconds = Integer.parseInt(minutesAndSeconds[1]);
                    if (minutes >= 0 && seconds >= 0
                        && seconds < MAX_SECONDS && ((Integer.MAX_VALUE - seconds) / MAX_SECONDS) >= minutes) {
                        return minutes * MAX_SECONDS + seconds;
                    }
                } catch (NumberFormatException e) {
                    return -1;
                }

            }
        }
        return -1;
    }
}

package edu.hw1;

public class Task1 {
    final static int MAX_SECONDS = 60;

    private Task1() {
        throw new UnsupportedOperationException();
    }

    public static int minutesToSeconds(String input) {
        if (input.contains(":")) {
            String[] minutesAndSeconds = input.split(":");
            if (minutesAndSeconds[1].length() == 2) {
                int minutes, seconds;
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

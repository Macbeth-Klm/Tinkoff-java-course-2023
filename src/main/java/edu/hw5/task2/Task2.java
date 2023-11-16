package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MagicNumber")
public final class Task2 {
    private Task2() {
    }

    public static List<LocalDate> getAllFridayThirteenth(int year) {
        IllegalArgumentException invalidInput =
            new IllegalArgumentException("Входные данные неверного формата!");
        if (year > LocalDate.MAX.getYear() || year < LocalDate.MIN.getYear()) {
            throw invalidInput;
        }
        LocalDate thirteenth = LocalDate.of(year, 1, 13);
        List<LocalDate> fridaysThirteenth = new ArrayList<>();
        while (thirteenth.getYear() == year) {
            if (thirteenth.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridaysThirteenth.add(thirteenth);
            }
            thirteenth = thirteenth.plusMonths(1);
        }
        return fridaysThirteenth;
    }

    public static LocalDate getNextFridayThirteenth(LocalDate date) {
        LocalDate nextFridayThirteenth = date;
        while (nextFridayThirteenth.getDayOfMonth() != 13) {
            nextFridayThirteenth = nextFridayThirteenth.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return nextFridayThirteenth;
    }
}

package edu.hw10.task1.Classes;

import edu.hw10.task1.Annotations.Max;
import edu.hw10.task1.Annotations.Min;
import edu.hw10.task1.Annotations.NotNull;

public record MyRecordWithAnnotations(@NotNull String name, @Min(0) @Max(100) int age, @Min(80) int weight) {
}

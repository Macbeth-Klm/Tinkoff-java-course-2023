package edu.hw6.task3;

import java.nio.file.Files;

public interface SizeFilter extends AbstractFilter {
    static AbstractFilter largerThan(long size) {
        return path -> Files.size(path) > size;
    }
}

package edu.hw6.task3;

import java.nio.file.Files;

public interface AttributeFilter extends AbstractFilter {
    static AbstractFilter readable() {
        return Files::isReadable;
    }

    static AbstractFilter writable() {
        return Files::isWritable;
    }

    static AbstractFilter regular() {
        return Files::isRegularFile;
    }
}

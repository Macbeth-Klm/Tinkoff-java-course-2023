package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class FilesSizeTask extends RecursiveTask<List<Path>> {
    private final Path root;
    private final long minSize;
    private final long maxSize;

    public FilesSizeTask(Path root, long minSize, long maxSize) {
        this.root = root;
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    @Override
    protected List<Path> compute() {
        List<Path> result = new ArrayList<>();
        if (Files.isRegularFile(root)) {
            try {
                if (Files.size(root) >= minSize && Files.size(root) <= maxSize) {
                    result.add(root);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return result;
        }
        List<Path> filesAndDirectories;
        try (var pathsStream = Files.list(root)) {
            filesAndDirectories = pathsStream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Path path : filesAndDirectories) {
            var task = new FilesSizeTask(path, minSize, maxSize);
            task.fork();
            result.addAll(task.join());
        }
        return result;
    }
}

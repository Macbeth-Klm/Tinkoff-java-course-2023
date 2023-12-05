package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class FilesExtensionTask extends RecursiveTask<List<Path>> {
    private final Path root;
    private final String extension;

    public FilesExtensionTask(Path root, String extension) {
        this.root = root;
        this.extension = extension;
    }

    @Override
    protected List<Path> compute() {
        List<Path> result = new ArrayList<>();
        if (Files.isRegularFile(root)) {
            String[] fileNameArray = root.getFileName().toString().split("\\.");
            if (fileNameArray[fileNameArray.length - 1].equals(extension)) {
                result.add(root);
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
            var task = new FilesExtensionTask(path, extension);
            task.fork();
            result.addAll(task.join());
        }
        return result;
    }
}

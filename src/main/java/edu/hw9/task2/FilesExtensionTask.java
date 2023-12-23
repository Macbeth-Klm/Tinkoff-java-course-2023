package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

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
        List<FilesExtensionTask> tasks = new ArrayList<>();
        try (var pathsStream = Files.list(root)) {
            pathsStream.forEach(path -> {
                var task = new FilesExtensionTask(path, extension);
                task.fork();
                tasks.add(task);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (var task : tasks) {
            result.addAll(task.join());
        }
        return result;
    }
}

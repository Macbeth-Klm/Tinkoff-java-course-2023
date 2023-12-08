package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DirectoriesTask extends RecursiveTask<List<Path>> {
    private final Path root;
    private final int minFilesCount;
    private int filesInRoot;

    public DirectoriesTask(Path root, int minFilesCount) {
        this.root = root;
        this.minFilesCount = minFilesCount;
        filesInRoot = 0;
    }

    public int getFilesInRoot() {
        return filesInRoot;
    }

    @Override
    protected List<Path> compute() {
        if (Files.isRegularFile(root)) {
            return new ArrayList<>();
        }
        List<DirectoriesTask> tasks = new ArrayList<>();
        try (var pathStream = Files.list(root)) {
            pathStream.forEach(path -> {
                if (Files.isRegularFile(path)) {
                    filesInRoot++;
                } else {
                    var task = new DirectoriesTask(path, minFilesCount);
                    task.fork();
                    tasks.add(task);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Path> result = new ArrayList<>();
        for (var task : tasks) {
            result.addAll(task.join());
            filesInRoot += task.getFilesInRoot();
        }
        if (filesInRoot > minFilesCount) {
            result.add(root);
        }
        return result;
    }
}

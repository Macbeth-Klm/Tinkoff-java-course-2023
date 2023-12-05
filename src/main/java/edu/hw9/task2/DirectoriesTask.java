package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

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
        List<Path> filesAndDirectories;
        try (var pathsStream = Files.list(root)) {
            filesAndDirectories = pathsStream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<DirectoriesTask> directoriesTasks = new ArrayList<>();
        for (Path path : filesAndDirectories) {
            if (Files.isRegularFile(path)) {
                filesInRoot++;
            } else {
                var task = new DirectoriesTask(path, minFilesCount);
                task.fork();
                directoriesTasks.add(task);
            }
        }

        List<Path> result = new ArrayList<>();
        for (var directoriesTask : directoriesTasks) {
            result.addAll(directoriesTask.join());
            filesInRoot += directoriesTask.getFilesInRoot();
        }
        if (!result.isEmpty() || filesInRoot > minFilesCount) {
            result.add(root);
        }
        return result;
    }
}

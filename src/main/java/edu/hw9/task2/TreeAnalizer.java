package edu.hw9.task2;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public final class TreeAnalizer {
    private TreeAnalizer() {
    }

    public static List<Path> findDirectoriesWhichContainsRequiredFilesCount(Path root, int filesCount) {
        try (var forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(new DirectoriesTask(root, filesCount));
        }
    }

    public static List<Path> findFilesWithRequiredExtension(Path root, String extension) {
        try (var forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(new FilesExtensionTask(root, extension));
        }
    }

    public static List<Path> findFilesWithRequiredSize(Path root, long minSize, long maxSize) {
        try (var forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(new FilesSizeTask(root, minSize, maxSize));
        }
    }
}

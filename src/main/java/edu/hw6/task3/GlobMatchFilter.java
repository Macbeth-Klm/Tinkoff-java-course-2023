package edu.hw6.task3;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;

public interface GlobMatchFilter extends AbstractFilter {
    static AbstractFilter globMatches(String glob) {
        return path -> {
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
            return matcher.matches(path.getFileName());
        };
    }
}

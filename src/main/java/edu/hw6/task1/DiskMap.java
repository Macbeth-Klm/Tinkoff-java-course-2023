package edu.hw6.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DiskMap implements Map<String, String> {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Path filePath;
    private final Map<String, String> delegate;

    public DiskMap(Path path) {
        try {
            delegate = new HashMap<>();
            filePath = path;
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                LOGGER.info("Файл успешно создан!");
            } else {
                loadFromDisk();
                LOGGER.info("Файл уже существует!");
            }
        } catch (IOException io) {
            LOGGER.error("Не удалось создать новый файл!", io);
            throw new RuntimeException(io);
        }
    }

    public Map<String, String> getDelegate() {
        return delegate;
    }

    private void loadFromDisk() {
        try {
            Map<String, String> mapFromFile = Files.readAllLines(filePath, StandardCharsets.UTF_8).stream()
                .map(string -> string.split(":"))
                .collect(Collectors.toMap((String[] s) -> s[0], (String[] s) -> s[1]));
            delegate.putAll(mapFromFile);
        } catch (Exception e) {
            LOGGER.error("Загрузить данные с диска!", e);
            throw new RuntimeException(e);
        }
    }

    private void flushToDisk() {
        String map = toString();
        try (BufferedWriter bw = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            bw.write(map);
        } catch (IOException io) {
            LOGGER.error("Не удалось записать в файл данные!", io);
            throw new RuntimeException(io);
        }
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return delegate.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return delegate.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return delegate.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        var result = delegate.put(key, value);
        flushToDisk();
        return result;
    }

    @Override
    public String remove(Object key) {
        var result = delegate.remove(key);
        flushToDisk();
        return result;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        delegate.putAll(m);
        flushToDisk();
    }

    @Override
    public void clear() {
        delegate.clear();
        flushToDisk();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return delegate.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return delegate.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return delegate.entrySet();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!delegate.isEmpty()) {
            for (String key : delegate.keySet()) {
                sb.append(key)
                    .append(":")
                    .append(delegate.get(key))
                    .append("\n");
            }
            sb.delete(sb.length() - 1, sb.length());
        }
        return sb.toString();
    }
}

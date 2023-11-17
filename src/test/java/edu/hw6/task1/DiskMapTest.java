package edu.hw6.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

class DiskMapTest {
    private static final Logger LOGGER = LogManager.getLogger();
    private Map<String, String> diskMap;
    private static final Path DISK_MAP_PATH = Path.of("src/test/java/edu/hw6/task1/diskMap.txt");

    @BeforeEach
    void createDiskMap() {
        diskMap = new DiskMap(DISK_MAP_PATH);
    }

    @AfterEach
    void deleteDiskMap() {
        try {
            Files.delete(DISK_MAP_PATH);
        } catch (IOException io) {
            LOGGER.error("Не удалось удалить файл!", io);
            throw new RuntimeException(io);
        }
    }

    @Test
    void shouldCreateNewFile() {
        assertThat(diskMap)
            .isEmpty();
    }

    @Test
    void shouldLoadFromFileBecauseItAlreadyExists() {
        Path path = Path.of("src/test/java/edu/hw6/task1/testDiskMap.txt");

        Map<String, String> existingDiskMap = new DiskMap(path);

        assertThat(existingDiskMap.size())
            .isOne();
    }

    @Test
    void shouldPutElementInDiskMap() {
        diskMap.put("My", "Name");

        assertThat(diskMap.size())
            .isOne();
        assertThat(diskMap.containsKey("My"))
            .isTrue();
        assertThat(diskMap.containsValue("Name"))
            .isTrue();
        assertThat(diskMap.containsKey("Hello"))
            .isFalse();
        assertThat(diskMap.containsValue("World"))
            .isFalse();
    }

    @Test
    void shouldReturnCorrectValue() {
        diskMap.put("Hello", "World");
        diskMap.put("Ilia", "Popov");

        assertThat(diskMap.size())
            .isEqualTo(2);
        assertThat(diskMap.get("Hello"))
            .isEqualTo("World");
        assertThat(diskMap.get("NotExistingKey"))
            .isNull();
    }

    @Test
    void shouldPutAllElementsInDiskMapAndReturnCorrectFormatDataWithToString() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Hello", "World");
        map.put("Ilia", "Popov");
        map.put("Key", "Value");
        map.put("Lovely", "Tinkoff");

        diskMap.putAll(map);

        assertThat(diskMap.toString())
            .isEqualTo("""
                Hello:World
                Ilia:Popov
                Key:Value
                Lovely:Tinkoff""");
    }

    @Test
    void shouldRemoveElementFromDiskMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Hello", "World");
        map.put("Ilia", "Popov");
        map.put("Key", "Value");
        map.put("Lovely", "Tinkoff");
        diskMap.putAll(map);

        diskMap.remove("Ilia");
        diskMap.remove("Hello", "World");

        assertThat(diskMap.toString())
            .isEqualTo("""
                Key:Value
                Lovely:Tinkoff""");
    }

    @Test
    void shouldClearDiskMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Hello", "World");
        map.put("Ilia", "Popov");
        map.put("Key", "Value");
        map.put("Lovely", "Tinkoff");
        diskMap.putAll(map);

        diskMap.clear();

        assertThat(diskMap)
            .isEmpty();
    }

    @Test
    void shouldReturnKeySetOfDiskMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Hello", "World");
        map.put("Ilia", "Popov");
        map.put("Key", "Value");
        map.put("Lovely", "Tinkoff");
        diskMap.putAll(map);

        Set<String> keySet = diskMap.keySet();

        assertThat(keySet)
            .containsExactly("Hello", "Ilia", "Key", "Lovely");
    }

    @Test
    void shouldReturnValuesOfDiskMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Hello", "World");
        map.put("Ilia", "Popov");
        map.put("Key", "Value");
        map.put("Lovely", "Tinkoff");
        diskMap.putAll(map);

        Collection<String> values = diskMap.values();

        assertThat(values)
            .containsExactly("World", "Popov", "Value", "Tinkoff");
    }

    @Test
    void shouldReturnEntrySetOfDiskMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Hello", "World");
        map.put("Ilia", "Popov");
        map.put("Key", "Value");
        map.put("Lovely", "Tinkoff");
        diskMap.putAll(map);

        Set<Map.Entry<String, String>> entrySet = diskMap.entrySet();

        assertThat(entrySet.toString())
            .isEqualTo("[Hello=World, Ilia=Popov, Key=Value, Lovely=Tinkoff]");
    }
}

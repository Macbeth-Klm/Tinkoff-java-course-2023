package edu.project3.reports;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultLogReport extends AbstractReport {
    private static final Logger LOGGER = LogManager.getLogger();

    public DefaultLogReport(
        Map<String, String> commonInfo,
        Map<String, Long> resources,
        Map<Integer, Long> codes,
        Map<String, Long> httpRequestMethods,
        Map<String, Long> httpUserAgent
    ) {
        super(commonInfo, resources, codes, httpRequestMethods, httpUserAgent);
    }

    @Override
    @SuppressWarnings("MultipleStringLiterals")
    public void getReport() {
        StringBuilder sb = new StringBuilder();

        sb.append("#### Общая информация\n\n");
        sb.append(String.format("%-30s| %-50s\n", "Метрика", "Значение"));
        for (String key : commonInfo.keySet()) {
            sb.append(String.format("%-30s| %-50s\n", key, commonInfo.get(key)));
        }

        sb.append("\n#### Запрашиваемые ресурсы\n\n");
        sb.append(String.format("%-30s| %-50s\n", "Ресурс", "Количество"));
        for (String key : resources.keySet()) {
            sb.append(String.format("%-30s| %-50d\n", key, resources.get(key)));
        }

        sb.append("\n#### Коды ответа\n\n");
        sb.append(String.format("%-5s| %-25s| %-50s\n", "Код", "Имя", "Количество"));
        for (Integer key : codes.keySet()) {
            sb.append(String.format("%-5d| %-25s| %-50d\n", key, codeToDescription.get(key), codes.get(key)));
        }

        sb.append("\n#### Методы HTTP-запросов\n\n");
        sb.append(String.format("%-30s| %-50s\n", "Метод", "Количество"));
        for (String key : httpRequestMethods.keySet()) {
            sb.append(String.format("%-30s| %-50d\n", key, httpRequestMethods.get(key)));
        }

        sb.append("\n#### HTTP User agents\n\n");
        sb.append(String.format("%-115s| %-50s\n", "User agent", "Количество"));
        for (String key : httpUserAgent.keySet()) {
            sb.append(String.format("%-115s| %-50d\n", key, httpUserAgent.get(key)));
        }

        Path path = Path.of("src/main/java/edu/project3/results/log_report.txt");
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException ex) {
                LOGGER.error("Не удалось создать txt-отчет!", ex);
                throw new RuntimeException(ex);
            }
        }
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(sb.toString());
        } catch (IOException ex) {
            LOGGER.error("Не удалось записать данные в txt-отчет!", ex);
            throw new RuntimeException(ex);
        }
    }
}

package edu.project3;

import edu.project3.parsers.CommandParser;
import edu.project3.parsers.NginxLogParser;
import edu.project3.reports.AbstractReport;
import edu.project3.reports.AdocReport;
import edu.project3.reports.DefaultLogReport;
import edu.project3.reports.MarkDownReport;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class LogAnalyser {

    private LogAnalyser() {
    }

    public static void analiseLogs(String inputCommand) {
        Configuration configuration = CommandParser.parse(inputCommand);
        List<LogRecord> logRecordList = NginxLogParser.parse(configuration);
        Map<String, String> commonInfoMap = commonInfo(logRecordList, configuration);
        Map<String, Long> resourcesMap = resources(logRecordList);
        Map<Integer, Long> codesMap = codes(logRecordList);
        Map<String, Long> httpRequestMethodsMap = httpRequestMethods(logRecordList);
        Map<String, Long> httpUserAgentMap = httpUserAgent(logRecordList);
        AbstractReport report = switch (configuration.format()) {
            case "markdown" -> new MarkDownReport(
                commonInfoMap,
                resourcesMap,
                codesMap,
                httpRequestMethodsMap,
                httpUserAgentMap
            );
            case "adoc" -> new AdocReport(
                commonInfoMap,
                resourcesMap,
                codesMap,
                httpRequestMethodsMap,
                httpUserAgentMap
            );
            default -> new DefaultLogReport(
                commonInfoMap,
                resourcesMap,
                codesMap,
                httpRequestMethodsMap,
                httpUserAgentMap
            );
        };
        report.getReport();
    }

    private static Map<String, String> commonInfo(List<LogRecord> logRecordList, Configuration configuration) {
        OffsetDateTime startDate = configuration.from();
        OffsetDateTime endDate = configuration.to();
        long requestsCount = logRecordList.size();
        long averageByteSent = (long) logRecordList.stream().filter(lr -> {
                var timeLocal = lr.timeLocal();
                var from = configuration.from();
                var to = configuration.to();
                return (from == null || !timeLocal.isBefore(from))
                    && (to == null || !timeLocal.isAfter(to))
                    && (from == null || to == null || (!timeLocal.isBefore(from) && !timeLocal.isAfter(to)));
            }).mapToLong(LogRecord::bodyBytesSent)
            .average().orElse(0);
        Map<String, String> commonInfo = new LinkedHashMap<>();
        commonInfo.put(
            "Файл(-ы)",
            configuration.filesName().toString().substring(1, configuration.filesName().toString().length() - 1)
        );
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        String dateToString = (startDate != null) ? startDate.format(formatter) : "-";
        commonInfo.put("Начальная дата", dateToString);
        dateToString = (endDate != null) ? endDate.format(formatter) : "-";
        commonInfo.put("Конечная дата", dateToString);
        commonInfo.put("Количество запросов", String.valueOf(requestsCount));
        commonInfo.put("Средний размер ответа", averageByteSent + "b");
        return commonInfo;
    }

    private static Map<String, Long> resources(List<LogRecord> logRecordList) {
        Map<String, Long> unsortedMap = logRecordList.stream()
            .collect(Collectors.groupingBy(
                lr -> {
                    String[] requestArray = lr.request().split(" ");
                    return requestArray[1].substring(requestArray[1].lastIndexOf("/"));
                },
                Collectors.counting()
            ));
        List<Long> values = new ArrayList<>();
        for (Map.Entry<String, Long> entry : unsortedMap.entrySet()) {
            values.add(entry.getValue());
        }
        values.sort(Comparator.reverseOrder());
        Map<String, Long> result = new LinkedHashMap<>();
        for (Long value : values) {
            for (Map.Entry<String, Long> entry : unsortedMap.entrySet()) {
                if (Objects.equals(entry.getValue(), value)) {
                    result.put(entry.getKey(), value);
                }
            }
        }
        return result;
    }

    private static Map<Integer, Long> codes(List<LogRecord> logRecordList) {
        Map<Integer, Long> unsortedMap = logRecordList.stream()
            .collect(Collectors.groupingBy(LogRecord::status, Collectors.counting()));
        List<Long> values = new ArrayList<>();
        for (Map.Entry<Integer, Long> entry : unsortedMap.entrySet()) {
            values.add(entry.getValue());
        }
        values.sort(Comparator.reverseOrder());
        Map<Integer, Long> result = new LinkedHashMap<>();
        for (Long value : values) {
            for (Map.Entry<Integer, Long> entry : unsortedMap.entrySet()) {
                if (Objects.equals(entry.getValue(), value)) {
                    result.put(entry.getKey(), value);
                }
            }
        }
        return result;
    }

    // Доп. характеристика
    private static Map<String, Long> httpRequestMethods(List<LogRecord> logRecordList) {
        Map<String, Long> unsortedMap = logRecordList.stream()
            .collect(Collectors.groupingBy(
                lr -> lr.request().substring(0, lr.request().indexOf(" ") + 1),
                Collectors.counting()
            ));
        List<Long> values = new ArrayList<>();
        for (Map.Entry<String, Long> entry : unsortedMap.entrySet()) {
            values.add(entry.getValue());
        }
        values.sort(Comparator.reverseOrder());
        Map<String, Long> result = new LinkedHashMap<>();
        for (Long value : values) {
            for (Map.Entry<String, Long> entry : unsortedMap.entrySet()) {
                if (Objects.equals(entry.getValue(), value)) {
                    result.put(entry.getKey(), value);
                }
            }
        }
        return result;
    }

    // Доп. характеристика
    private static Map<String, Long> httpUserAgent(List<LogRecord> logRecordList) {
        Map<String, Long> unsortedMap = logRecordList.stream()
            .collect(Collectors.groupingBy(LogRecord::httpUserAgent, Collectors.counting()));
        List<Long> values = new ArrayList<>();
        for (Map.Entry<String, Long> entry : unsortedMap.entrySet()) {
            values.add(entry.getValue());
        }
        values.sort(Comparator.reverseOrder());
        Map<String, Long> result = new LinkedHashMap<>();
        for (Long value : values) {
            for (Map.Entry<String, Long> entry : unsortedMap.entrySet()) {
                if (Objects.equals(entry.getValue(), value)) {
                    result.put(entry.getKey(), value);
                }
            }
        }
        return result;
    }
}

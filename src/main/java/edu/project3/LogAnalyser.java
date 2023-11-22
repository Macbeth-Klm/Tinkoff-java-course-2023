package edu.project3;

import edu.project3.parsers.CommandParser;
import edu.project3.parsers.NginxLogParser;
import edu.project3.reports.AbstractReport;
import edu.project3.reports.AdocReport;
import edu.project3.reports.DefaultLogReport;
import edu.project3.reports.MarkDownReport;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class LogAnalyser {

    private LogAnalyser() {
    }

    public static void analiseLogs(String inputCommand) {
        Configuration configuration = CommandParser.parse(inputCommand);

        List<LogRecord> logRecordList = NginxLogParser.parse(configuration);
        OffsetDateTime startDate = configuration.from();
        OffsetDateTime endDate = configuration.to();
        List<LogRecord> logRecordFilteredByDateList = logRecordList.stream()
            .filter(lr -> lr.checkDate(startDate, endDate)).toList();

        Map<String, String> commonInfoMap = commonInfo(logRecordFilteredByDateList, configuration);
        Map<String, Long> resourcesMap = resources(logRecordFilteredByDateList);
        Map<Integer, Long> codesMap = codes(logRecordFilteredByDateList);
        Map<String, Long> httpRequestMethodsMap = httpRequestMethods(logRecordFilteredByDateList);
        Map<String, Long> httpUserAgentMap = httpUserAgent(logRecordFilteredByDateList);

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

    private static Map<String, String> commonInfo(List<LogRecord> filteredLogRecordList, Configuration configuration) {
        long requestsCount = filteredLogRecordList.size();
        long averageByteSent = (long) filteredLogRecordList.stream().mapToLong(LogRecord::bodyBytesSent)
            .average().orElse(0);
        Map<String, String> commonInfo = new LinkedHashMap<>();
        commonInfo.put(
            "Файл(-ы)",
            configuration.filesName().toString().substring(1, configuration.filesName().toString().length() - 1)
        );
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        OffsetDateTime startDate = configuration.from();
        OffsetDateTime endDate = configuration.to();
        String dateToString = (startDate != null) ? startDate.format(formatter) : "-";
        commonInfo.put("Начальная дата", dateToString);
        dateToString = (endDate != null) ? endDate.format(formatter) : "-";
        commonInfo.put("Конечная дата", dateToString);
        commonInfo.put("Количество запросов", String.valueOf(requestsCount));
        commonInfo.put("Средний размер ответа", averageByteSent + "b");
        return commonInfo;
    }

    private static Map<String, Long> resources(List<LogRecord> filteredLogRecordList) {
        Map<String, Long> unsortedMap = filteredLogRecordList.stream()
            .collect(Collectors.groupingBy(
                lr -> {
                    String[] requestArray = lr.request().split(" ");
                    return requestArray[1].substring(requestArray[1].lastIndexOf("/"));
                },
                Collectors.counting()
            ));
        return unsortedMap.entrySet().stream()
            .sorted(Comparator.comparingLong((Map.Entry<String, Long> e) -> e.getValue()).reversed())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private static Map<Integer, Long> codes(List<LogRecord> logRecordList) {
        Map<Integer, Long> unsortedMap = logRecordList.stream()
            .collect(Collectors.groupingBy(LogRecord::status, Collectors.counting()));
        return unsortedMap.entrySet().stream()
            .sorted(Comparator.comparingLong((Map.Entry<Integer, Long> e) -> e.getValue()).reversed())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    // Доп. характеристика
    private static Map<String, Long> httpRequestMethods(List<LogRecord> logRecordList) {
        Map<String, Long> unsortedMap = logRecordList.stream()
            .collect(Collectors.groupingBy(
                lr -> lr.request().substring(0, lr.request().indexOf(" ") + 1),
                Collectors.counting()
            ));
        return unsortedMap.entrySet().stream()
            .sorted(Comparator.comparingLong((Map.Entry<String, Long> e) -> e.getValue()).reversed())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    // Доп. характеристика
    private static Map<String, Long> httpUserAgent(List<LogRecord> logRecordList) {
        Map<String, Long> unsortedMap = logRecordList.stream()
            .collect(Collectors.groupingBy(LogRecord::httpUserAgent, Collectors.counting()));
        return unsortedMap.entrySet().stream()
            .sorted(Comparator.comparingLong((Map.Entry<String, Long> e) -> e.getValue()).reversed())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}

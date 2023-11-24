package edu.project3.reports;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractReport {
    protected final Map<String, String> commonInfo = new LinkedHashMap<>();
    protected final Map<String, Long> resources = new LinkedHashMap<>();
    protected final Map<Integer, Long> codes = new LinkedHashMap<>();
    protected final Map<String, Long> httpRequestMethods = new LinkedHashMap<>();
    protected final Map<String, Long> httpUserAgent = new LinkedHashMap<>();
    protected final Map<Integer, String> codeToDescription = Map.of(
        200, "OK",
        206, "Partial Content",
        304, "Not Modified",
        403, "Forbidden",
        404, "Not Found",
        416, "Range Not Satisfiable",
        500, "Internal Server Error"
    );

    public AbstractReport(
        Map<String, String> commonInfo,
        Map<String, Long> resources,
        Map<Integer, Long> codes,
        Map<String, Long> httpRequestMethods,
        Map<String, Long> httpUserAgent
    ) {
        this.commonInfo.putAll(commonInfo);
        this.resources.putAll(resources);
        this.codes.putAll(codes);
        this.httpRequestMethods.putAll(httpRequestMethods);
        this.httpUserAgent.putAll(httpUserAgent);
    }

    public abstract void getReport();
}

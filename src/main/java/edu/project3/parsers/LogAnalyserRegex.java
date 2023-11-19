package edu.project3.parsers;

public final class LogAnalyserRegex {
    public static final String HTTP_REGEX =
        "(https?:/+([0-9A-z-_%]+(\\.[0-9a-z]+)?)+\\.[0-9a-z]+/([0-9A-z-_%]+/*)+(\\.[0-9a-z]+)?)";
    public static final String URI_REGEX = "(--path " + HTTP_REGEX + ")";
    public static final String LOCAL_REGEX =
        "(--path (([^<>:\"/\\\\|]+[/\\\\])+([^<>:\"/\\\\|])+((\\.[0-9A-z]+)|(\\*))))";
    public static final String ISO_8601_REGEX =
        "(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}[+-]\\d{2})";
    public static final String COMMAND_REGEX =
        "^java -jar nginx-log-stats\\.jar (" + URI_REGEX + "|" + LOCAL_REGEX
            + ")( --from " + ISO_8601_REGEX + ")?"
            + "( --to " + ISO_8601_REGEX + ")?"
            + "( --format (adoc|markdown))?$";
    public static final String REMOTE_ADDR_REGEX = "((\\d{1,3}\\.){3}\\d{1,3})";
    public static final String REMOTE_USER_REGEX = "([^\\s]+|-)";
    public static final String LOG_TIME_REGEX = "(\\d{2}/[A-z]{3}/\\d{4}:(\\d{2}:){2}\\d{2} [+-]\\d{4})";
    public static final String REQUEST_REGEX =
        "((GET|HEAD|PUT|DELETE|POST|OPTIONS|TRACE|CONNECT) (/+[^\\s<>:\"/\\\\|]+)+ HTTP/(0\\.9|1\\.0|1\\.1|2|3))";
    public static final String STATUS_REGEX = "\\d{3}";
    public static final String BODY_BYTES_SENT_REGEX = "\\d+";
    public static final String HTTP_REFERER_REGEX = HTTP_REGEX + "|" + "-";
    public static final String HTTP_USER_AGENT_REGEX = "[^\"]+";
    public static final String NGINX_LOG_REGEX = "^" + REMOTE_ADDR_REGEX + " - " + REMOTE_USER_REGEX
        + " \\[" + LOG_TIME_REGEX + "] \"" + REQUEST_REGEX + "\" (" + STATUS_REGEX + ") (" + BODY_BYTES_SENT_REGEX
        + ") \"(" + HTTP_REFERER_REGEX + ")\" \"(" + HTTP_USER_AGENT_REGEX + ")\"$";

    private LogAnalyserRegex() {
    }
}

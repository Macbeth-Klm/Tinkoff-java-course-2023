package edu.project3;

import edu.project3.parsers.CommandParser;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogAnalyser {
    private static final Logger LOGGER = LogManager.getLogger();
    private String format;

    public LogAnalyser() {
    }

    public void analiseLogs(Scanner scanner) {
        LOGGER.info("Введите команду:");
        String inputCommand = scanner.nextLine();
        Configuration configuration = CommandParser.parse(inputCommand);
        if (configuration != null) {
            System.out.println(configuration);
        }
    }
}

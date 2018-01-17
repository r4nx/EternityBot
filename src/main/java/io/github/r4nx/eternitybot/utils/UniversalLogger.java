package io.github.r4nx.eternitybot.utils;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class UniversalLogger {
    private static final UniversalLogger ourInstance = new UniversalLogger();

    public static UniversalLogger getInstance() {
        return ourInstance;
    }

    private final Logger log = Logger.getLogger("EternityBot");
    private FileHandler fileHandler;

    public Logger getLogger() {
        return log;
    }

    private UniversalLogger() {
        try {
            fileHandler = new FileHandler("EternityBot.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConsoleHandler consoleHandler = new ConsoleHandler();
        LogFormatter logFormatter = new LogFormatter();
        fileHandler.setFormatter(logFormatter);
        consoleHandler.setFormatter(logFormatter);
        log.addHandler(fileHandler);
        log.addHandler(consoleHandler);
        log.setUseParentHandlers(false);
    }
}

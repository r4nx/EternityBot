package io.github.r4nx.eternitybot.console.executors;

import io.github.r4nx.eternitybot.telegram.Telegram;
import io.github.r4nx.eternitybot.utils.UniversalLogger;

import java.util.logging.Logger;

public class TestCommandExecutor {
    private static final Logger log = UniversalLogger.getInstance().getLogger();

    public void execute() {
        Telegram telegram = Telegram.getInstance();
        log.info(telegram.testConnection() ? "Connection established!" : "Connection failed!");
    }
}

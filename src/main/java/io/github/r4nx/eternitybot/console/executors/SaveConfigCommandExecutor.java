package io.github.r4nx.eternitybot.console.executors;

import io.github.r4nx.eternitybot.telegram.Telegram;
import io.github.r4nx.eternitybot.utils.Config;
import io.github.r4nx.eternitybot.utils.UniversalLogger;

import java.util.logging.Logger;

public class SaveConfigCommandExecutor {
    private static final Logger log = UniversalLogger.getInstance().getLogger();

    public void execute() {
        Config config = Config.getInstance();
        Telegram telegram = Telegram.getInstance();

        config.setLastUpdateId(telegram.getLastUpdateId());
        config.setLastMessageId(telegram.getLastMessageId());
        Config.saveConfig();
        log.info("Configuration saved!");
    }
}

package io.github.r4nx.eternitybot.console.handlers;

import io.github.r4nx.eternitybot.console.ConsoleCommand;
import io.github.r4nx.eternitybot.console.executors.SaveConfigCommandExecutor;
import io.github.r4nx.eternitybot.console.executors.StopCommandExecutor;
import io.github.r4nx.eternitybot.console.executors.TestCommandExecutor;
import io.github.r4nx.eternitybot.utils.UniversalLogger;

import java.util.logging.Logger;

public class CommandHandler {
    private static final Logger log = UniversalLogger.getInstance().getLogger();

    public void handle(ConsoleCommand cmd) {
        switch (cmd.getName().toLowerCase()) {
            case "stop":
                new StopCommandExecutor().execute();
                break;
            case "saveconfig":
                new SaveConfigCommandExecutor().execute();
                break;
            case "test":
                new TestCommandExecutor().execute();
                break;
            default:
                log.info("Unknown command!");
        }
    }
}

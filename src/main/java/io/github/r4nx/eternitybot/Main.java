package io.github.r4nx.eternitybot;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import io.github.r4nx.eternitybot.console.ConsoleCommand;
import io.github.r4nx.eternitybot.console.handlers.CommandHandler;
import io.github.r4nx.eternitybot.telegram.Telegram;
import io.github.r4nx.eternitybot.telegram.handlers.UpdateHandler;
import io.github.r4nx.eternitybot.utils.Config;
import io.github.r4nx.eternitybot.utils.UniversalLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Logger log = UniversalLogger.getInstance().getLogger();

    private static boolean working = true;

    public static void stop() {
        working = false;
    }

    public static void main(String[] args) {
        Config config = Config.getInstance();
        Config.loadConfig();
        Telegram telegram = Telegram.getInstance();
        telegram.init(config.getBotToken());
        telegram.setLastUpdateId(config.getLastUpdateId());
        telegram.setLastMessageId(config.getLastMessageId());
        log.setLevel(config.isVerboseLog() ? Level.ALL : Level.CONFIG);

        log.fine("Trying to connect to Telegram...");
        if (telegram.testConnection())
            log.info("Connection established!");
        else {
            log.severe("Connection failed!");
            Arrays.asList(log.getHandlers()).forEach(Handler::close);
            return;
        }

        UpdateHandler updateHandler = new UpdateHandler();
        Runnable getUpdates = () -> {
            while (working) {
                JsonElement updates = telegram.getUpdates();
                if (updates != JsonNull.INSTANCE)
                    updateHandler.handle(updates.getAsJsonObject());

                try {
                    Thread.sleep(config.getUpdateCheckInterval());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread getUpdatesThread = new Thread(getUpdates);
        getUpdatesThread.start();

        Scanner scanner = new Scanner(System.in);
        CommandHandler commandHandler = new CommandHandler();
        String input;
        while (working) {
            input = scanner.next();
            log.config("Command: " + input);

            List<String> cmdText = new ArrayList<>();
            Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(input);
            while (m.find())
                cmdText.add(m.group(1).replace("\"", ""));

            ConsoleCommand cmd = new ConsoleCommand(
                    cmdText.get(0),
                    Arrays.copyOfRange(cmdText.toArray(new String[cmdText.size()]), 1, cmdText.size())
            );
            commandHandler.handle(cmd);
        }

        while (getUpdatesThread.isAlive()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        config.setLastUpdateId(telegram.getLastUpdateId());
        config.setLastMessageId(telegram.getLastMessageId());
        Config.saveConfig();
        Arrays.asList(log.getHandlers()).forEach(Handler::close);
    }
}

package io.github.r4nx.eternitybot.telegram.handlers;

import io.github.r4nx.eternitybot.telegram.Telegram;
import io.github.r4nx.eternitybot.telegram.TelegramCommand;
import io.github.r4nx.eternitybot.telegram.executors.*;

class CommandHandler {
    public void handle(TelegramCommand cmd) {
        String message;
        switch (cmd.getName().toLowerCase()) {
            case "/start":
                message = "Hi! I am EternityBot. Your can get my code and list of standard commands on [Github](https://github.com/r4nx/eternitybot).";
                break;
            case "/ping":
                message = "Pong!";
                break;
            case "/b64":
                message = new B64CommandExecutor().execute(cmd.getArgs());
                break;
            case "/json":
                message = new JsonCommandExecutor().execute(cmd.getArgs());
                break;
            case "/math":
                message = new MathCommandExecutor().execute(cmd.getArgs());
                break;
            case "/random":
                message = new RandomCommandExecutor().execute(cmd.getArgs());
                break;
            default:
                message = "Unknown command!";
        }

        Telegram.getInstance().sendMessage(cmd.getSender(), message, true);
    }
}

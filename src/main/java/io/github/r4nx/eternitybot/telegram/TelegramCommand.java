package io.github.r4nx.eternitybot.telegram;

import java.util.HashMap;
import java.util.List;

public class TelegramCommand {
    private final String name;
    private final String[] args;
    private final int sender;
    private final List<HashMap<String, Object>> entities;

    public String getName() {
        return name;
    }

    public String[] getArgs() {
        return args;
    }

    public int getSender() {
        return sender;
    }

    public List<HashMap<String, Object>> getEntities() {
        return entities;
    }

    public TelegramCommand(String name, String[] args, int sender, List<HashMap<String, Object>> entities) {
        this.name = name;
        this.args = args;
        this.sender = sender;
        this.entities = entities;
    }
}

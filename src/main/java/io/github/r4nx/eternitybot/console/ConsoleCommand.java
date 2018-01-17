package io.github.r4nx.eternitybot.console;

public class ConsoleCommand {
    private final String name;
    private final String[] args;

    public String getName() {
        return name;
    }

    public String[] getArgs() {
        return args;
    }

    public ConsoleCommand(String name, String[] args) {
        this.name = name;
        this.args = args;
    }
}

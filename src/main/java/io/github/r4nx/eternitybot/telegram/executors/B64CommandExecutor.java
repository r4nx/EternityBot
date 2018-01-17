package io.github.r4nx.eternitybot.telegram.executors;

import java.util.Base64;

public class B64CommandExecutor {
    public String execute(String[] args) {
        if (args.length < 2)
            return "Not enough arguments!";

        if (args[0].toLowerCase().startsWith("e"))
            return Base64.getEncoder().encodeToString(args[1].getBytes());
        else if (args[0].toLowerCase().startsWith("d"))
            return new String(Base64.getDecoder().decode(args[1]));
        else
            return "Unknown subcommand!";
    }
}

package io.github.r4nx.eternitybot.telegram.executors;

import com.google.gson.*;

public class JsonCommandExecutor {
    public String execute(String[] args) {
        if (args.length < 2)
            return "Not enough arguments!";

        if (args[0].equalsIgnoreCase("pretty")) {
            JsonParser jsonParser = new JsonParser();
            JsonObject json;
            try {
                json = jsonParser.parse(args[1]).getAsJsonObject();
            } catch (JsonSyntaxException e) {
                return "Invalid syntax!";
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return "```json\n" + gson.toJson(json) + "\n```";
        } else
            return "Unknown subcommand!";
    }
}

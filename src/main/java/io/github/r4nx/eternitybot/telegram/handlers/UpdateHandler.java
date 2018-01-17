package io.github.r4nx.eternitybot.telegram.handlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.r4nx.eternitybot.telegram.InlineQuery;
import io.github.r4nx.eternitybot.telegram.Telegram;
import io.github.r4nx.eternitybot.telegram.TelegramCommand;
import io.github.r4nx.eternitybot.utils.Config;
import io.github.r4nx.eternitybot.utils.UniversalLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateHandler {
    private static final Logger log = UniversalLogger.getInstance().getLogger();

    public void handle(JsonObject updates) {
        JsonArray result = updates.getAsJsonArray("result");
        if (result.size() == 0) return;

        Config config = Config.getInstance();
        Telegram telegram = Telegram.getInstance();

        for (JsonElement update : result) {
            JsonObject updateObject = update.getAsJsonObject();

            telegram.setLastUpdateId(updateObject.get("update_id").getAsLong());

            if (updateObject.has("message")) {
                JsonObject message = updateObject.getAsJsonObject("message");

                if (message.get("message_id").getAsLong() <= telegram.getLastMessageId()) continue;
                telegram.setLastMessageId(message.get("message_id").getAsLong());

                int from = message.getAsJsonObject("from").get("id").getAsInt();
                if (config.isUseOwnerSystem() && !config.getOwners().contains(from)) continue;

                if (message.has("entities") && message.getAsJsonArray("entities")
                        .get(0).getAsJsonObject().get("type").getAsString().equals("bot_command")) {
                    List<String> cmdText = new ArrayList<>();

                    Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(message.get("text").getAsString().replace("\\\"", "\\%"));
                    while (m.find())
                        cmdText.add(m.group(1).replace("\"", "").replace("\\%", "\""));

                    List<HashMap<String, Object>> entities = new ArrayList<>();
                    for (JsonElement messageEntity : message.getAsJsonArray("entities")) {
                        JsonObject messageEntityObject = messageEntity.getAsJsonObject();
                        HashMap<String, Object> entity = new HashMap<>();
                        entity.put("type", messageEntityObject.get("type").getAsString());
                        entity.put("offset", messageEntityObject.get("offset").getAsInt());
                        entity.put("length", messageEntityObject.get("length").getAsInt());
                        entities.add(entity);
                    }

                    TelegramCommand cmd = new TelegramCommand(
                            cmdText.get(0),
                            Arrays.copyOfRange(cmdText.toArray(new String[cmdText.size()]), 1, cmdText.size()),
                            from,
                            entities
                    );
                    new CommandHandler().handle(cmd);
                    log.info(String.format("Command from %d: %s", from, message.get("text").getAsString()));
                } else
                    telegram.sendMessage(from, "Unsupported content type.", false);
            } else if (updateObject.has("inline_query")) {
                JsonObject inlineQuery = updateObject.getAsJsonObject("inline_query");

                if (telegram.getLastInlineQueryId().equals(inlineQuery.get("id").getAsString())) continue;
                telegram.setLastInlineQueryId(inlineQuery.get("id").getAsString());

                int from = inlineQuery.getAsJsonObject("from").get("id").getAsInt();
                if (config.isUseOwnerSystem() && !config.getOwners().contains(from)) continue;
                if (inlineQuery.get("query").getAsString().trim().isEmpty()) continue;

                InlineQuery query = new InlineQuery(inlineQuery.get("id").getAsString(), from, inlineQuery.get("query").getAsString());
                new InlineQueryHandler().handle(query);
                log.info(String.format("Inline query from %d: %s", from, query.getQuery()));
            }
        }
    }
}

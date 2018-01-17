package io.github.r4nx.eternitybot.telegram;

import com.google.gson.*;
import io.github.r4nx.eternitybot.utils.HttpUtil;

import java.util.HashMap;

public class Telegram {
    private static final Telegram ourInstance = new Telegram();

    public static Telegram getInstance() {
        return ourInstance;
    }

    private String telegramApiUrl = "https://api.telegram.org/bot";
    private long lastUpdateId = 0L;
    private long lastMessageId = 0L;
    private String lastInlineQueryId = "";

    public long getLastUpdateId() {
        return lastUpdateId;
    }

    public void setLastUpdateId(long lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }

    public long getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    public String getLastInlineQueryId() {
        return lastInlineQueryId;
    }

    public void setLastInlineQueryId(String lastInlineQueryId) {
        this.lastInlineQueryId = lastInlineQueryId;
    }

    public void init(String botToken) {
        telegramApiUrl = String.format("https://api.telegram.org/bot%s/", botToken);
    }

    public JsonElement getUpdates() {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("offset", Long.toString(getLastUpdateId()));
        parameters.put("allowed_updates", "[\"message\", \"inline_query\"]");
        String response = HttpUtil.sendPostRequest(telegramApiUrl + "getUpdates", parameters);
        Gson gson = new Gson();
        JsonObject updates = gson.fromJson(response, JsonObject.class);

        return isRequestOk(updates) ? updates : JsonNull.INSTANCE;
    }

    public void sendMessage(int chatId, String text, boolean markdown) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("chat_id", Integer.toString(chatId));
        parameters.put("text", text);
        if (markdown) parameters.put("parse_mode", "markdown");
        HttpUtil.sendPostRequest(telegramApiUrl + "sendMessage", parameters);
    }

    public void answerInlineQuery(String inlineQueryId, JsonArray results) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("inline_query_id", inlineQueryId);
        parameters.put("results", new Gson().toJson(results));
        HttpUtil.sendPostRequest(telegramApiUrl + "answerInlineQuery", parameters);
    }

    public boolean testConnection() {
        String response = HttpUtil.sendPostRequest(telegramApiUrl + "getMe", new HashMap<>());
        Gson gson = new Gson();
        JsonObject result = gson.fromJson(response, JsonObject.class);
        return isRequestOk(result);
    }

    private boolean isRequestOk(JsonObject response) {
        return response != null && response.has("ok") && response.get("ok").getAsBoolean();
    }
}

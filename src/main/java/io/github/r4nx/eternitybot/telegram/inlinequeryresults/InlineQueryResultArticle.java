package io.github.r4nx.eternitybot.telegram.inlinequeryresults;

import com.google.gson.JsonObject;

public class InlineQueryResultArticle {
    private final String id;
    private final String title;
    private final String text;
    private final boolean markdown;

    public InlineQueryResultArticle(String id, String title, String text, boolean markdown) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.markdown = markdown;
    }

    public JsonObject getResult() {
        JsonObject result = new JsonObject();
        result.addProperty("type", "article");
        result.addProperty("id", id);
        result.addProperty("title", title);
        JsonObject inputMessageContent = new JsonObject();
        inputMessageContent.addProperty("message_text", text);
        if (markdown) inputMessageContent.addProperty("parse_mode", "markdown");
        result.add("input_message_content", inputMessageContent);
        return result;
    }
}

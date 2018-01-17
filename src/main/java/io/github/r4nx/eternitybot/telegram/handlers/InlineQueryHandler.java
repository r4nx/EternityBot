package io.github.r4nx.eternitybot.telegram.handlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.r4nx.eternitybot.telegram.InlineQuery;
import io.github.r4nx.eternitybot.telegram.Telegram;
import io.github.r4nx.eternitybot.telegram.executors.MathCommandExecutor;
import io.github.r4nx.eternitybot.telegram.executors.RandomCommandExecutor;
import io.github.r4nx.eternitybot.telegram.inlinequeryresults.InlineQueryResultArticle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InlineQueryHandler {
    @SuppressWarnings("UnusedAssignment")
    public void handle(InlineQuery inlineQuery) {
        int resultId = 0;
        JsonArray results = new JsonArray();

        List<String> args = new ArrayList<>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(inlineQuery.getQuery().replace("\\\"", "\\%"));
        while (m.find())
            args.add(m.group(1).replace("\"", "").replace("\\%", "\""));
        switch (args.get(0).toLowerCase()) {
            case "math": {
                JsonObject result = new InlineQueryResultArticle(
                        String.valueOf(resultId++),
                        "Result",
                        new MathCommandExecutor().execute(Arrays.copyOfRange(args.toArray(new String[args.size()]), 1, args.size())),
                        true
                ).getResult();
                results.add(result);
                break;
            }
            case "random": {
                JsonObject result = new InlineQueryResultArticle(
                        String.valueOf(resultId++),
                        "Result",
                        new RandomCommandExecutor().execute(Arrays.copyOfRange(args.toArray(new String[args.size()]), 1, args.size())),
                        true
                ).getResult();
                results.add(result);
                break;
            }
        }

        Telegram.getInstance().answerInlineQuery(inlineQuery.getId(), results);
    }
}

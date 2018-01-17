package io.github.r4nx.eternitybot.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class ConfigConverter implements JsonSerializer<Config>, JsonDeserializer<Config> {
    @Override
    public JsonElement serialize(Config config, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("botToken", config.getBotToken());
        result.addProperty("verboseLog", config.isVerboseLog());
        result.addProperty("useOwnerSystem", config.isUseOwnerSystem());
        JsonArray owners = new JsonArray();
        config.getOwners().forEach(owners::add);
        result.add("owners", owners);
        result.addProperty("updateCheckInterval", config.getUpdateCheckInterval());
        result.addProperty("lastUpdateId", config.getLastUpdateId());
        result.addProperty("lastMessageId", config.getLastMessageId());
        return result;
    }

    @Override
    public Config deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Config config = Config.getInstance();
        config.setBotToken(jsonObject.get("botToken").getAsString());
        config.setVerboseLog(jsonObject.get("verboseLog").getAsBoolean());
        config.setUseOwnerSystem(jsonObject.get("useOwnerSystem").getAsBoolean());
        List<Integer> owners = new ArrayList<>();
        jsonObject.getAsJsonArray("owners").forEach(owner -> owners.add(owner.getAsInt()));
        config.setOwners(owners);
        config.setUpdateCheckInterval(jsonObject.get("updateCheckInterval").getAsInt());
        config.setLastUpdateId(jsonObject.get("lastUpdateId").getAsLong());
        config.setLastMessageId(jsonObject.get("lastMessageId").getAsLong());
        return config;
    }
}

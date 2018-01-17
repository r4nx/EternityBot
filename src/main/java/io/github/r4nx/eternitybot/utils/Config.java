package io.github.r4nx.eternitybot.utils;

import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Config {
    private static final Config ourInstance = new Config();

    public static Config getInstance() {
        return ourInstance;
    }

    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public boolean isVerboseLog() {
        return verboseLog;
    }

    public void setVerboseLog(boolean verboseLog) {
        this.verboseLog = verboseLog;
    }

    public boolean isUseOwnerSystem() {
        return useOwnerSystem;
    }

    public void setUseOwnerSystem(boolean useOwnerSystem) {
        this.useOwnerSystem = useOwnerSystem;
    }

    public List<Integer> getOwners() {
        return owners;
    }

    public void setOwners(List<Integer> owners) {
        this.owners = owners;
    }

    public int getUpdateCheckInterval() {
        return updateCheckInterval;
    }

    public void setUpdateCheckInterval(int updateCheckInterval) {
        this.updateCheckInterval = updateCheckInterval;
    }

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

    private String botToken = "";
    private boolean verboseLog = false;
    private boolean useOwnerSystem = true;
    private List<Integer> owners = new ArrayList<>();
    private int updateCheckInterval = 1000;
    private long lastUpdateId = 0L;
    private long lastMessageId = 0L;

    public static void loadConfig() {
        if (!new File("settings.json").exists()) {
            saveConfig();
            return;
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        try (Reader reader = new FileReader("settings.json")) {
            gsonBuilder.registerTypeAdapter(Config.class, new ConfigConverter()).create().fromJson(reader, Config.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveConfig() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting().registerTypeAdapter(Config.class, new ConfigConverter());
        try (Writer writer = new FileWriter("settings.json")) {
            gsonBuilder.create().toJson(ourInstance, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

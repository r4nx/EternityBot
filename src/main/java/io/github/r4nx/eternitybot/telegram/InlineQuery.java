package io.github.r4nx.eternitybot.telegram;

public class InlineQuery {
    private final String id;
    private final int sender;
    private final String query;

    public String getId() {
        return id;
    }

    public int getSender() {
        return sender;
    }

    public String getQuery() {
        return query;
    }

    public InlineQuery(String id, int sender, String query) {
        this.id = id;
        this.sender = sender;
        this.query = query;
    }
}

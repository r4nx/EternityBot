package io.github.r4nx.eternitybot.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

class LogFormatter extends Formatter {
    private static final DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss.SSS");
    private final Config config = Config.getInstance();

    @Override
    public String format(LogRecord record) {
        StringBuilder builder = new StringBuilder();
        builder.append(df.format(new Date(record.getMillis()))).append(" - ");
        if (config.isVerboseLog()) {
            builder.append("[").append(record.getSourceClassName()).append(".");
            builder.append(record.getSourceMethodName()).append("] - ");
        }
        builder.append("[").append(record.getLevel()).append("] - ");
        builder.append(formatMessage(record));
        builder.append("\n");
        return builder.toString();
    }
}

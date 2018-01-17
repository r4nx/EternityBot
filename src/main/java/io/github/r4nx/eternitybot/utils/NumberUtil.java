package io.github.r4nx.eternitybot.utils;

public class NumberUtil {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean tryParseDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

package io.github.r4nx.eternitybot.telegram.executors;

import io.github.r4nx.eternitybot.utils.NumberUtil;

import java.util.Random;

public class RandomCommandExecutor {
    public String execute(String[] args) {
        Random random = new Random();

        switch (args.length) {
            case 0:
                return String.valueOf(random.nextInt(2));
            case 1: {
                if (!NumberUtil.tryParseInt(args[0]))
                    return "Invalid number!";
                int max = Integer.parseInt(args[0]);
                if (max < 0 || max >= Integer.MAX_VALUE)
                    return "Value(s) must be positive and less than 2147483647!";

                return String.valueOf(random.nextInt(max + 1));
            }
            default: {
                if (!NumberUtil.tryParseInt(args[0]) || !NumberUtil.tryParseInt(args[1]))
                    return "Invalid number!";
                int min = Integer.parseInt(args[0]);
                int max = Integer.parseInt(args[1]);
                if (min < 0 || min >= Integer.MAX_VALUE || max < 0 || max >= Integer.MAX_VALUE)
                    return "Value(s) must be positive and less than 2147483647!";

                return String.valueOf(random.nextInt(max - min + 1) + min);
            }
        }
    }
}

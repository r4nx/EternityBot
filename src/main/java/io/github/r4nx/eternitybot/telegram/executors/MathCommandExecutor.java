package io.github.r4nx.eternitybot.telegram.executors;

import io.github.r4nx.eternitybot.utils.NumberUtil;

public class MathCommandExecutor {
    public String execute(String[] args) {
        if (args.length < 2)
            return "Not enough arguments!";

        switch (args[0].toLowerCase()) {
            case "sin": {
                if (!NumberUtil.tryParseDouble(args[1]))
                    return "Invalid number!";

                Double value = Double.parseDouble(args[1]);
                return String.format("Sin from %.2f: *%.3f*", value, Math.sin(Math.toRadians(value)));
            }
            case "cos": {
                if (!NumberUtil.tryParseDouble(args[1]))
                    return "Invalid number!";

                Double value = Double.parseDouble(args[1]);
                return String.format("Cos from %.2f: *%.3f*", value, Math.cos(Math.toRadians(value)));
            }
            case "tan": {
                if (!NumberUtil.tryParseDouble(args[1]))
                    return "Invalid number!";

                Double value = Double.parseDouble(args[1]);
                return String.format("Tan from %.2f: *%.3f*", value, Math.tan(Math.toRadians(value)));
            }
            case "ctg": {
                if (!NumberUtil.tryParseDouble(args[1]))
                    return "Invalid number!";

                Double value = Double.parseDouble(args[1]);
                return String.format("Ctg from %.2f: *%.3f*", value, 1.0 / Math.tan(Math.toRadians(value)));
            }
            case "pow": {
                if (args.length < 3)
                    return "Not enough arguments!";
                if (!NumberUtil.tryParseDouble(args[1]) || !NumberUtil.tryParseDouble(args[2]))
                    return "Invalid number!";

                double value1 = Double.parseDouble(args[1]);
                double value2 = Double.parseDouble(args[2]);
                return String.format("%.2f raised to %.2f power is *%.2f*", value1, value2, Math.pow(value1, value2));
            }
            default:
                return "Unknown subcommand!";
        }
    }
}

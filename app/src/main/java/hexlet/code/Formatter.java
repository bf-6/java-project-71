package hexlet.code;

import hexlet.code.formatters.Stylish;

import java.util.TreeMap;

public class Formatter {

    public static String formatSelection(TreeMap<String, String> resultDiffMap, String format) {

        if (format.equals("stylish")) {
            return Stylish.stylishReturn(resultDiffMap);
        }
        return null;

    }

}

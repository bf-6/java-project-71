package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String formatSelection(List<Map<String, Object>> resultDiffList, String format) {

        if (format.equals("plain")) {
            return Plain.plainReturn(resultDiffList);
        } else {
            return Stylish.stylishReturn(resultDiffList);
        }

    }

}

package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String formatSelection(List<Map<String, Object>> resultDiffList, String format)
            throws JsonProcessingException {

        return switch (format) {
            case "plain" -> Plain.plainReturn(resultDiffList);
            case "json" -> Json.jsonReturn(resultDiffList);
            case "stylish" -> Stylish.stylishReturn(resultDiffList);
            default -> throw new RuntimeException("Unknown type: '" + format + "'");
        };

    }

}

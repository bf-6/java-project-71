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

        if (format.equals("plain")) {
            return Plain.plainReturn(resultDiffList);
        } else {
            if (format.equals("json")) {
                return Json.jsonReturn(resultDiffList);
            } else {
                return Stylish.stylishReturn(resultDiffList);
            }
        }

    }

}

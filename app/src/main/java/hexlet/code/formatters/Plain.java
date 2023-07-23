package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Plain {

    public static String plainReturn(List<Map<String, Object>> resultDiffMap) {

        List<String> diffList = new ArrayList<>();

        for (Map<String, Object> map: resultDiffMap) {

            switch (map.get("status").toString()) {
                case "unchanged" ->
                        diffList.add("");
                case "changed" -> {
                    diffList.add("Property " + "'" + map.get("key") + "'" + " was updated. From "
                            + typeValueCheck(map.get("value1")) + " to " + typeValueCheck(map.get("value2")) + "\n");
                }
                case "deleted" ->
                        diffList.add("Property " + "'" + map.get("key") + "'" + " was removed" + "\n");
                case "added" ->
                        diffList.add("Property " + "'" + map.get("key") + "'"
                                + " was added with value: " + typeValueCheck(map.get("value2")) + "\n");
                default -> throw new RuntimeException("Unknown type!" + map.get("status"));
            }
        }

        String str = String.join("", diffList).trim();

        return str;
    }

    public static String typeValueCheck(Object value) {

        if (value == null) {
            return null;
        }
        if (value.getClass() == ArrayList.class || value.getClass() == LinkedHashMap.class) {
            return "[complex value]";
        }
        if (value.getClass() == String.class) {
            return "'" + value + "'";
        }
        return value.toString();
    }
}

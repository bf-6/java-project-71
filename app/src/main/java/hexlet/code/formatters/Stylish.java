package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Stylish {

    public static String stylishReturn(List<Map<String, Object>> resultDiffMap) {

        List<String> diffList = new ArrayList<>();

        for (Map<String, Object> map: resultDiffMap) {
            switch (map.get("status").toString()) {
                case "unchanged" ->
                        diffList.add(" " + " " + map.get("key") + ": " + map.get("value"));
                case "changed" -> {
                    diffList.add("-" + " " + map.get("key") + ": " + map.get("value1"));
                    diffList.add("+" + " " + map.get("key") + ": " + map.get("value2"));
                }
                case "deleted" ->
                        diffList.add("-" + " " + map.get("key") + ": " + map.get("value1"));
                case "added" -> diffList.add("+" + " " + map.get("key") + ": " + map.get("value2"));
                default -> throw new RuntimeException("Unknown type!" + map.get("status"));
            }
        }

        String str = "{\n  " + String.join("\n  ", diffList) + "\n}";

        return str;

    }
}

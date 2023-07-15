package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
//import java.util.Comparator;
//import java.util.regex.Pattern;

public class Stylish {

    public static String stylishReturn(Map<String, String> resultDiffMap) {

        List<String> diffList = new ArrayList<>();

        for (Map.Entry<String, String> entry : resultDiffMap.entrySet()) {
            if (entry.getValue().equals("unchanged")) {
                diffList.add(" " + " " + entry.getKey());
            } else {
                if (entry.getValue().equals("deleteOldValue")) {
                    diffList.add("-" + " " + entry.getKey());
                } else {
                    if (entry.getValue().equals("addNewValue")) {
                        diffList.add("+" + " " + entry.getKey());
                    } else {
                        if (entry.getValue().equals("deleted")) {
                            diffList.add("-" + " " + entry.getKey());
                        } else {
                            diffList.add(("+" + " " + entry.getKey()));
                        }
                    }
                }
            }
        }

        String str = "{\n " + String.join("\n ", diffList) + "\n}";

        return str;

    }
}

package hexlet.code;

import java.util.Map;
import java.util.TreeMap;

public class Differ {

    public static String differ(String pathToFile1, String pathToFile2, String format) throws Exception {

        // Формируем из получившихся строк мапы
        Map<String, Object> map1 = ContentFromFile.getContentFromFile(pathToFile1);
        Map<String, Object> map2 = ContentFromFile.getContentFromFile(pathToFile2);

        // Создаем мапу для результата сравнения двух файлов
        TreeMap<String, String> resultDiffMap = new TreeMap<>();

        for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
            if (map2.containsKey(entry1.getKey())) {
                for (Map.Entry<String, Object> entry2 : map2.entrySet()) {
                    if (entry1.getKey().equals(entry2.getKey())) {
                        if (entry1.toString().equals(entry2.toString())) {
                            resultDiffMap.put(entry1.toString(), "unchanged");
                        } else {
                            resultDiffMap.put(entry1.toString(), "deleteOldValue");
                            resultDiffMap.put(entry2.toString(), "addNewValue");
                        }
                    }
                }
            } else {
                resultDiffMap.put(entry1.toString(), "deleted");
            }
        }

        for (Map.Entry<String, Object> entry : map2.entrySet()) {
            if (!map1.containsKey((entry.getKey()))) {
                resultDiffMap.put(entry.toString(), "added");
            }
        }

        // Приводим список к строке в отформатированном виде
        String str = Formatter.formatSelection(resultDiffMap, format);

        return str.replace("=", ": ");

    }

    public static String differ(String pathFile1, String pathFile2) throws Exception {
        return differ(pathFile1, pathFile2, "stylish");
    }
}

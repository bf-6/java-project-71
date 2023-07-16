package hexlet.code;

import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String pathToFile1, String pathToFile2, String format) throws Exception {

        // Формируем из получившихся строк мапы
        Map<String, Object> map1 = ContentFromFile.getContentFromFile(pathToFile1);
        Map<String, Object> map2 = ContentFromFile.getContentFromFile(pathToFile2);

        // Создаем список и записываем в него результат сравнения наших мап
        List<Map<String, Object>> resultDiffList = CompareFiles.diff(map1, map2);

        // Приводим список к строке в отформатированном виде
        String str = Formatter.formatSelection(resultDiffList, format);

        return str;

    }

    public static String generate(String pathFile1, String pathFile2) throws Exception {
        return generate(pathFile1, pathFile2, "stylish");
    }
}

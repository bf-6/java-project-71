package hexlet.code;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;
import java.util.TreeMap;

public class Differ {

    public static String differ(String pathToFile1, String pathToFile2, String format) throws Exception {

        // Чтение файла:
        // Получаем путь к нужному файлу
        Path readFilePath1 = Paths.get(pathToFile1);
        Path readFilePath2 = Paths.get(pathToFile2);

        // Формируем абсолютный путь,
        // если filePath будет содержать относительный путь,
        // то мы всегда будет работать с абсолютным
        Path path1 = readFilePath1.toAbsolutePath().normalize();
        Path path2 = readFilePath2.toAbsolutePath().normalize();

        // Проверяем существование файла
        if (!Files.exists(path1)) {
            throw new Exception("File '" + path1 + "' does not exist");
        }
        if (!Files.exists(path2)) {
            throw new Exception("File '" + path2 + "' does not exist");
        }

        // Читаем файл и записываем содержимое в переменные
        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        // Определяем формат файла, который нужно распарсить,
        // с помощью метода getExtension класса FilenameUtils из библиотеки Apache Commons IO
        String dataFormat1 = FilenameUtils.getExtension(path1.toString());
        String dataFormat2 = FilenameUtils.getExtension(path2.toString());

        // Формируем из получившихся строк мапы
        Map<String, Object> map1 = Parser.parser(content1, dataFormat1);
        Map<String, Object> map2 = Parser.parser(content2, dataFormat2);

        // Объявляем список, в котором будем хранить результат сравнения
//        List<String> diffList = new ArrayList<>();

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

//        List<String> diffList = new ArrayList<>(resultDiffMap.values());

        // Приводим список к строке в отформатированном виде
        String str = "{\n " + String.join("\n ", Stylish.stylishReturn(resultDiffMap)) + "\n}";

        return str.replace("=", ": ");

    }
}

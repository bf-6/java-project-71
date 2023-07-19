package hexlet.code;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String pathToFile1, String pathToFile2, String format) throws Exception {

        // Формируем из получившихся строк мапы
        Map<String, Object> map1 = getData(pathToFile1);
        Map<String, Object> map2 = getData(pathToFile2);

        // Создаем список и записываем в него результат сравнения наших мап
        List<Map<String, Object>> resultDiffList = Comparator.diff(map1, map2);

        // Приводим список к строке в отформатированном виде
        String str = Formatter.formatSelection(resultDiffList, format);

        return str;

    }

    public static String generate(String pathFile1, String pathFile2) throws Exception {
        return generate(pathFile1, pathFile2, "stylish");
    }

    private static Map getData(String pathToFile) throws Exception {

        // Чтение файла:
        // Получаем путь к нужному файлу
        Path readFilePath = Paths.get(pathToFile);

        // Формируем абсолютный путь,
        // если filePath будет содержать относительный путь,
        // то мы всегда будет работать с абсолютным
        Path path = readFilePath.toAbsolutePath().normalize();

        // Проверяем существование файла
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        // Читаем файл и записываем содержимое в переменные
        String content = Files.readString(path);

        // Определяем формат файла, который нужно распарсить,
        // с помощью метода getExtension класса FilenameUtils из библиотеки Apache Commons IO
        String dataFormat = FilenameUtils.getExtension(path.toString());

        // Парсинг выделяем в отдельный модуль
        return Parser.parsing(content, dataFormat);
    }
}

package hexlet.code;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class ContentFromFile {

    public static Map<String, Object> getContentFromFile(String pathToFile) throws Exception {
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

        // Формируем из получившихся строк мапы
        Map<String, Object> mapContent = Parser.parsingFile(content, dataFormat);

        return mapContent;
    }
}

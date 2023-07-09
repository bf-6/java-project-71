package hexlet.code;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Differ {

    public static String differ(String pathToFile1, String pathToFile2) throws Exception {

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
        List<String> diffList = new ArrayList<>();

//         * Начинаем перебор элементов первой мапы, если ключ элемента первой мапы содержится во второй мапе,
//         * то тогда начинаем перебор элементов второй мапы, если ключ элемента первой мапы равен ключу элемента
//         * второй мапы, то тогда сравниваем значение элемента первой мапы со значением элемента второй мапы,
//         * если они равны, то тогда добавляем элемент первой мапы в список без каких-либо знаков, если элементы
//         * мап не равны, то тогда добавляем в список элемент первый мапы со знаком минус и элемент второй мапы
//         * со знаком плюс если ключ элемента первой мапы не содержится во второй мапе, то тогда добавляем элемент
//         * первой мапы в список со знаком минус
        for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
            if (map2.containsKey(entry1.getKey())) {
                for (Map.Entry<String, Object> entry2 : map2.entrySet()) {
                    if (entry1.getKey().equals(entry2.getKey())) {
                        if (entry1.toString().equals(entry2.toString())) {
                            diffList.add(" " + " " + entry1);
                        } else {
                            diffList.add("-" + " " + entry1);
                            diffList.add("+" + " " + entry2);
                        }
                    }
                }
            } else {
                diffList.add("-" + " " + entry1);
            }
        }
        // Начинаем перебор элементов второй мапы
        // если ключ элемента второй мапы не содержится в первой мапе,
        // то тогда добавляем элемент второй мапы в список со знаком плюс
        for (Map.Entry<String, Object> entry : map2.entrySet()) {
            if (!map1.containsKey((entry.getKey()))) {
                diffList.add(("+" + " " + entry));
            }
        }

        // Сортируем получившийся список игнорируя пробелы и специальные символы
        Pattern p = Pattern.compile("[^\\p{L}\\p{N}]+");
        diffList.sort(Comparator.comparing(s -> p.matcher(s).replaceAll("")));

        // Приводим список к строке в отформатированном виде
        String str = "{\n " + String.join("\n ", diffList) + "\n}";

        // Выводим результат на экран заменив символ "=" на ": "
        System.out.println(str.replace("=", ": "));

        return str.replace("=", ": ");

    }
}

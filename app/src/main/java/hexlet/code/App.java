package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.TreeMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import java.util.concurrent.Callable;
import java.util.regex.Pattern;


@Command(
        name = "gen-diff",
        mixinStandardHelpOptions = true, // - атрибут добавляет к справке опции -h, --help и -V, --version
        description = "Compares two configuration files and shows a difference.",
        version = "gen-diff 1.0" // - указываем версию для опции -V, --version
)
class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, description = "Soutput format [default: stylish]")
    static String format;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    static String filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    static String filepath2;

    public static void main(String... args) {

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

    }

    @Override
    public Integer call() throws Exception {

        // Чтение файла:
        // Получаем путь к нужному файлу
        Path readFilePath1 = Paths.get(filepath1);
        Path readFilePath2 = Paths.get(filepath2);

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

        // Формируем из получившихся строк мапы
        ObjectMapper objectMapper = new ObjectMapper();
        TreeMap<String, Object> map1 =
                objectMapper.readValue(content1, new TypeReference<>() { });
        TreeMap<String, Object> map2 =
                objectMapper.readValue(content2, new TypeReference<>() { });

        // Объявляем список, в котором будем хранить результат сравнения
        List<String> diffList = new ArrayList<>();

        // Начинаем перебор элементов первой мапы,
        // если ключ элемента первой мапы содержится во второй мапе,
        // то тогда начинаем перебор элементов второй мапы,
        // если ключ элемента первой мапы равен ключу элемента второй мапы,
        // то тогда сравниваем значение элемента первой мапы со значением элемента второй мапы,
        // если они равны, то тогда добавляем элемент первой мапы в список без каких-либо знаков,
        // если элементы мап не равны, то тогда добавляем в список элемент первый мапы со знаком минус
        // и элемент второй мапы со знаком плюс
        // если ключ элемента первой мапы не содержится во второй мапе, то тогда
        // добавляем элемент первой мапы в список со знаком минус
        for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
            if (map2.containsKey(entry1.getKey())) {
                for (Map.Entry<String, Object> entry2 : map2.entrySet()) {
                    if (entry1.getKey().equals(entry2.getKey())) {
                        if (entry1.getValue().equals(entry2.getValue())) {
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

        return null;
    }
}

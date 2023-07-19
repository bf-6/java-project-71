package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppTest {

    private static String resultStylish;
    private static String resultPlain;
    private static String resultJson;

    private static Path pathToFile(String fileName) {
        Path path = Paths.get("src", "test", "resources", fileName);
        return path;
    }

    private static String readFile(String fileName) throws Exception {
        Path resourceDirectory = pathToFile(fileName);
        return Files.readString(resourceDirectory);
    }

    @BeforeAll
    public static void beforeAll() throws Exception {

        resultStylish = readFile("result_stylish.txt");
        resultPlain = readFile("result_plain.txt");
        resultJson = readFile("result_json.json");

    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void testDiff(String format) throws Exception {

        String filePath1 = pathToFile("file1." + format).toString();
        String filePath2 = pathToFile("file2." + format).toString();

        assertEquals(resultStylish, Differ.generate(filePath1, filePath2));
        assertEquals(resultStylish, Differ.generate(filePath1, filePath2, "stylish"));
        assertEquals(resultPlain, Differ.generate(filePath1, filePath2, "plain"));
        assertEquals(resultJson, Differ.generate(filePath1, filePath2, "json"));


    }
}

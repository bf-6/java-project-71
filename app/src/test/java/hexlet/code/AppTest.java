package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

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

        assertThat(Differ.generate(filePath1, filePath2)).isEqualTo(resultStylish);
        assertThat(Differ.generate(filePath1, filePath2, "stylish"))
                .isEqualTo(resultStylish);
        assertThat(Differ.generate(filePath1, filePath2, "plain"))
                .isEqualTo(resultPlain);
        assertThat(Differ.generate(filePath1, filePath2, "json"))
                .isEqualTo(resultJson);

    }
}

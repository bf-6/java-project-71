package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppTest {

    private static String resultStylish;
    private static String resultPlain;

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

    }

    @Test
    public void testDiff() throws Exception {

        String filePath1 = pathToFile("file1.json").toString();
        String filePath2 = pathToFile("file2.json").toString();
        String filePath3 = pathToFile("file3.yml").toString();
        String filePath4 = pathToFile("file4.yml").toString();

        assertEquals(resultStylish, Differ.differ(filePath1, filePath2));
        assertEquals(resultStylish, Differ.differ(filePath1, filePath2, "stylish"));
        assertEquals(resultPlain, Differ.differ(filePath3, filePath4));

    }
}

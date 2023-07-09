package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AppTest {

    static String result;

    @BeforeAll
    public static void beforeAll() {

        result = "{\n" + " + apt: install\n" + " - follow: false\n"
            + "   host: hexlet.io\n" + " - proxy: 123.234.53.22\n"
            + " + timeout: 20\n" + " - timeout: 50\n" + " + verbose: true\n" + "}";

    }

//    Нужно добавить КодКлиман и покрытие тестами

    @Test
    public void testDiff() throws Exception {

        String fileName1 = "file1.json";
        String fileName2 = "file2.json";
        String fileName3 = "file3.yml";
        String fileName4 = "file4.yml";

        Path resourceDirectory1 = Paths.get("src", "test", "resources", fileName1);
        Path resourceDirectory2 = Paths.get("src", "test", "resources", fileName2);
        Path resourceDirectory3 = Paths.get("src", "test", "resources", fileName3);
        Path resourceDirectory4 = Paths.get("src", "test", "resources", fileName4);

        String absolutePath1 = resourceDirectory1.toFile().getAbsolutePath();
        String absolutePath2 = resourceDirectory2.toFile().getAbsolutePath();
        String absolutePath3 = resourceDirectory3.toFile().getAbsolutePath();
        String absolutePath4 = resourceDirectory4.toFile().getAbsolutePath();

        assertEquals(result, Differ.differ(absolutePath1, absolutePath2));
        assertEquals(result, Differ.differ(absolutePath3, absolutePath4));

    }
}

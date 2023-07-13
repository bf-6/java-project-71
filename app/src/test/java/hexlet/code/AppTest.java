package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppTest {

    static String result;
    static String stylish = "stylish";

    @BeforeAll
    public static void beforeAll() throws IOException {

        Path resourceDirectory = Paths.get("src", "test", "resources", "result.txt");
//        Path absolutePath = resourceDirectory.toAbsolutePath().normalize();
        result = Files.readString(resourceDirectory);

    }

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

        assertEquals(result, Differ.differ(absolutePath1, absolutePath2, stylish));
        assertEquals(result, Differ.differ(absolutePath3, absolutePath4, stylish));

    }
}

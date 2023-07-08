package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    static String result;

    @BeforeAll
    public static void beforeAll() {

        result = "{\n" + " + apt: install\n" + " - follow: false\n"
            + "   host: hexlet.io\n" + " - proxy: 123.234.53.22\n"
            + " + timeout: 20\n" + " - timeout: 50\n" + " + verbose: true\n" + "}";

    }

//    Path resourceDirectory = Paths.get("src","test","resources");
//    String absolutePath = resourceDirectory.toFile().getAbsolutePath();
//    System.out.println(absolutePath);
//    Assert.assertTrue(absolutePath.endsWith("src/test/resources"));

//    Нудно добавить КодКлиман и покрытие тестами

    @Test
    public void testDiff() throws Exception {

        assertEquals(result, App.differ());

    }
}

package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map parsing(String fileContent, String dataFormat) throws Exception {

        // Проверяем формат, если "json", то передаём файл в parsingJson
        // иначе передаем файл в parsingYml, если формат окажется неподходящим -
        // выведем исключение
        if (dataFormat.equals("json")) {
            return parsingJson(fileContent);
        } else if (dataFormat.equals("yml") || dataFormat.equals("yaml")) {
            return parsingYml(fileContent);
        }
        throw new Exception("Unknown format: '" + dataFormat + "'");

    }

    // Парсит из json в словарь map
    public static Map parsingJson(String json) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map
                = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() { });
        return map;
    }

    // Парсит из yml в словарь map
    public static Map parsingYml(String yml) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        Map<String, Object> map
                = objectMapper.readValue(yml, new TypeReference<Map<String, Object>>() { });
        return map;
    }

}

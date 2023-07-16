package hexlet.code;

import java.util.List;
import java.util.Map;

import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Stream;

public class CompareFiles {

    public static final String UNCHANGED = "unchanged";
    public static final String ADDED = "added";
    public static final String DELETED = "deleted";
    public static final String CHANGED = "changed";
    private static Map<String, Object> map1 = new TreeMap<>();
    private static Map<String, Object> map2 = new TreeMap<>();

    public static List<Map<String, Object>> diff(Map<String, Object> firstMap,
                                                 Map<String, Object> secondMap) {

        map1 = firstMap;
        map2 = secondMap;

        // С помощью метода concat() создаем новый поток из слияния двух map1.stream() и map2.stream()
        return Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                // сортируем получившийся поток по ключу comparingByKey() с помощью метода sorted()
                .sorted(Map.Entry.comparingByKey())
                // передаем элемент потока в метод
                .map(CompareFiles::compareResult)
                .distinct()
                // с помощью потока toList() преобразуем поток в список.
                .toList();
    }

    public static Map<String, Object> compareResult(Map.Entry<String, Object> mapForCompare) {

        String key = mapForCompare.getKey();
        Object value = mapForCompare.getValue();

        // объявляем мапу для записи результата
        TreeMap<String, Object> map = new TreeMap<>();

        // Если ключ содержится в обеих мапах и значения по этим ключам в мапах совпадают,
        // то тогда в map добавляем ключ, значение mapForCompare и статус для него "без изменений"
        // и возвращаем map как элемент списка
        if (checkingForContentInMaps(key) && checkingForEqualityValues(key)) {
            map.put("key", key);
            map.put("value", value);
            map.put("status", UNCHANGED);
            return map;
            // Если ключи совпадают, а значени нет, то добавляем ключ и значения первой мапы и второй,
            // только статус будет "изменён"
        } else if (checkingForContentInMaps(key) && !checkingForEqualityValues(key)) {
            map.put("key", key);
            map.put("value1", map1.get(key));
            map.put("value2", map2.get(key));
            map.put("status", CHANGED);
            return map;
        }
        // Если первые две проверки не прошли, то тогда проверяем содержится ли ключ в первой мапе,
        // если содержится, то тогда добавляем его в мапу со статусом "удалён"
        if (map1.containsKey(key)) {
            map.put("key", key);
            map.put("value1", map1.get(key));
            map.put("status", DELETED);
            return map;
        }
        // Если предыдущие проверки не прошли, то это значит что такого элемента нет в первой мапе,
        // добавляем элемент второй мапы со статусом "добавлен"
        map.put("key", key);
        map.put("value2", map2.get(key));
        map.put("status", ADDED);
        return map;
    }

    // Метод проверяет, есть ли ключ в обоих объектах и возвращает true, если да
    public static boolean checkingForContentInMaps(String key) {
        return map1.containsKey(key) && map2.containsKey(key);
    }

    // Метод сравнивает значения ключей в обоих объектах с помощью метода Objects.equals(),
    // если значения ключей равны, метод возвращает true
    public static boolean checkingForEqualityValues(String key) {
        return Objects.equals(map1.get(key), map2.get(key));
    }
}


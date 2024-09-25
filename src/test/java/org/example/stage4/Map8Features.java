package org.example.stage4;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Map8Features {

    @Test
    void example() {

        Map<String, Integer> map = new HashMap<>();

        map.entrySet();//возврат пар ключ\значение
        map.keySet(); //возврат ключей
        map.values(); //возврат значений


        assertEquals(false, map.containsKey("key"));
        assertEquals(null, map.get("key"));

        map.putIfAbsent("key", 1);

        assertEquals(true, map.containsKey("key"));
        assertEquals(1, map.get("key"));

        assertEquals(false, map.containsKey("notExist"));
        //выполняет функцию если ключ отсуствует или по ключу хранится нулл,
        //результат функции записывается по этому ключу
        assertEquals(2, map.computeIfAbsent("notExist", (k) -> 2));

        assertEquals(true, map.containsKey("notExist"));
        assertEquals(2, map.get("notExist"));

        //если ключ присутствует, то функция не вызывается и хранимое значение не изменяется
        assertEquals(2, map.computeIfAbsent("notExist", (k) -> 3));

        //выполняет функцию если ключ присутствует и сохраняет новое значение
        assertEquals(11, map.computeIfPresent("key", (k, v) -> v + 10));
        assertEquals(11, map.get("key"));
        assertEquals(12, map.computeIfPresent("notExist", (k, v) -> v + 10));
        assertEquals(12, map.get("notExist"));

        //oбъединяет хранимое значение и переданное. Если значение отсутствует, то эквивалент put
        assertEquals(3, map.merge("notExistTwo", 3, (old, fresh) -> old + fresh));
        //если значение присутствует, то будет вызываться функция для объединения хранимого и нового значения
        assertEquals(6, map.merge("notExistTwo", 3, (old, fresh) -> old + fresh));

        //получает хранимое значение или возвращает значение по умолчанию
        assertEquals(6, map.getOrDefault("notExistTwo", 4));
        assertEquals(4, map.getOrDefault("notExistThree", 4));
        assertFalse(map.containsKey("notExistThree"));
    }
}

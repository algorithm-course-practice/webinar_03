package org.example.stage2;

import org.example.stage1.MyMap;
import org.example.stage3.LinkedHashMyMap;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class HashMyMapTest {

    HashMyMap<String, Integer> map = new HashMyMap<>();

    @Test
    void put() {
        assertFalse(map.containsValue(5));
        assertEquals(0, map.size());
        assertFalse(map.containsKey("key"));
        assertFalse(map.containsKey("key2"));
        assertFalse(map.containsKey("key3"));
        assertTrue(map.put("key", 1));
        assertTrue(map.containsKey("key"));
        assertTrue(map.put("key", 1));
        assertTrue(map.put("key", 2));
        assertTrue(map.put("key2", 3));
        assertTrue(map.put("key3", 4));
        assertTrue(map.containsKey("key2"));
        assertTrue(map.containsKey("key3"));

        assertTrue(map.containsValue(2));
        assertTrue(map.containsValue(3));
        assertTrue(map.containsValue(4));
        assertFalse(map.containsValue(5));

        assertNotEquals(asList("key", "key2", "key3"), map.keySet());

        assertEquals(3, map.size());

        assertFalse(map.remove("notExist"));
        assertEquals(3, map.size());
        assertTrue(map.remove("key"));
        assertEquals(2, map.size());
        assertTrue(map.remove("key2"));
        assertEquals(1, map.size());
        assertTrue(map.remove("key3"));
        assertEquals(0, map.size());

        HashMyMap<String, Integer> linked = new LinkedHashMyMap<>();
        linked.put("key", 1);
        linked.put("key2", 2);
        linked.put("key3", 3);
        assertEquals(asList("key", "key2", "key3"), linked.keySet());

        linked.remove("key2");
        assertEquals(asList("key", "key3"), linked.keySet());

    }
}
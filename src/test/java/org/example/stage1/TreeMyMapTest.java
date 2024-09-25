package org.example.stage1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeMyMapTest {

    MyMap<String, Integer> map = new TreeMyMap<>();

    @Test
    void put() {

        assertFalse(map.remove("undefined"));

        assertFalse(map.containsValue(1));
        assertFalse(map.containsKey("key"));
        assertFalse(map.containsKey("key2"));
        assertFalse(map.containsKey("key3"));
        assertTrue(map.put("key", 1));
        assertTrue(map.containsValue(1));
        assertTrue(map.containsKey("key"));
        assertTrue(map.put("key", 1));
        assertTrue(map.put("key", 2));
        assertFalse(map.containsValue(1));
        assertTrue(map.containsValue(2));
        assertTrue(map.put("key2", 3));
        assertTrue(map.put("key3", 4));
        assertTrue(map.containsValue(3));
        assertTrue(map.containsValue(4));

        assertTrue(map.remove("key"));
        assertTrue(map.remove("key2"));
        assertTrue(map.remove("key3"));
        assertEquals(0, map.size());
    }
}
package com.collections.junit.listmanager;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class ListManagerTest {

    ListManager manager = new ListManager();
    List<Integer> list = new ArrayList<>();

    @Test
    void testAdd() {
        manager.addElement(list, 10);
        assertTrue(list.contains(10));
    }

    @Test
    void testRemove() {
        list.add(20);
        manager.removeElement(list, 20);
        assertFalse(list.contains(20));
    }

    @Test
    void testSize() {
        manager.addElement(list, 1);
        manager.addElement(list, 2);
        assertEquals(2, manager.getSize(list));
    }
}
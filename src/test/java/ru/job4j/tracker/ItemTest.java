package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class ItemTest {

    @Test
    public void comparableSort() {
        List<Item> items = Arrays.asList(
                new Item(1, "A"),
                new Item(8, "B"),
                new Item(2, "C")
        );
        Collections.sort(items);
        List<Item> expected = Arrays.asList(
                new Item(1, "A"),
                new Item(2, "C"),
                new Item(8, "B")
        );
        assertEquals(expected, items);
    }

    @Test
    public void itemsAscByName() {
        List<Item> items = Arrays.asList(
                new Item(2, "A"),
                new Item(6, "B"),
                new Item(4, "C")
        );
        items.sort(new ItemAscByName());
        List<Item> expected = Arrays.asList(
                new Item(2, "A"),
                new Item(6, "B"),
                new Item(4, "C")
        );
        assertEquals(items, expected);
    }

    @Test
    public void itemsDescByName() {
        List<Item> items = Arrays.asList(
                new Item(1, "A"),
                new Item(3, "B"),
                new Item(2, "C")
        );
        items.sort(new ItemDescByName());
        List<Item> expected = Arrays.asList(
                new Item(2, "C"),
                new Item(3, "B"),
                new Item(1, "A")
        );
        assertEquals(items, expected);
    }
}
package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("ivan@gmail.com", "Ivan Svyatobog");
        map.put("alex@gmail.com", "Alex Ivanov");
        map.put("jake@gmail.com", "Jake Black");
        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println(key + " = " + value);
        }
        System.out.println();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " = " + value);
        }
    }
}
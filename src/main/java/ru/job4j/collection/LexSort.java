package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] leftSrt = left.split("\\.");
        int leftChar = Integer.parseInt(leftSrt[0]);
        String[] rightStr = right.split("\\.");
        int rightChar = Integer.parseInt(rightStr[0]);
        return Integer.compare(leftChar, rightChar);
    }
}
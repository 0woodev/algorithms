package org.owoodev.book.jbhyunikim.make_strange_word;

public class Solution {
    public String solution(String s) {
        StringBuilder builder = new StringBuilder();

        char[] chars = s.toCharArray();
        boolean isEvenIndex = true;
        for (char c: chars) {
            if (!Character.isAlphabetic(c)) {
                builder.append(c);
                isEvenIndex = true;
            } else {
                if (isEvenIndex) {
                    builder.append(Character.toUpperCase(c));
                } else {
                    builder.append(Character.toLowerCase(c));
                }
                isEvenIndex = !isEvenIndex;
            }
        }

        return builder.toString();
    }
}

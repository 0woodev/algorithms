package org.owoodev.book.jbhyunikim.convert_str_to_num;


import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static Map<String, String> words;

    static {
        words = new HashMap<>();
        words.put("zero", "0");
        words.put("one", "1");
        words.put("two", "2");
        words.put("three", "3");
        words.put("four", "4");
        words.put("five", "5");
        words.put("six", "6");
        words.put("seven", "7");
        words.put("eight", "8");
        words.put("nine", "9");
    }

    public int solution(String s) {
        char[] chars = s.toCharArray();

        StringBuilder builder = new StringBuilder();
        StringBuilder tokenBuilder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (Character.isDigit(c)) {
                if (tokenBuilder.length() > 0) {
                    String token = tokenBuilder.toString();
                    String convertNumber = words.get(token);
                    builder.append(convertNumber);
                    tokenBuilder = new StringBuilder();
                }

                builder.append(c);
            } else {
                String token = tokenBuilder.toString();
                String convertNumber = words.get(token);

                if (convertNumber != null) {
                    builder.append(convertNumber);
                    tokenBuilder = new StringBuilder();
                }

                tokenBuilder.append(c);
            }
        }

        if (tokenBuilder.length() > 0) {
            String token = tokenBuilder.toString();
            String convertNumber = words.get(token);
            builder.append(convertNumber);
        }

        int answer = Integer.parseInt(builder.toString());
        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        solution.solution("one4seveneight");
    }
}

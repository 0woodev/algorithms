package org.owoodev.book.jbhyunikim.password;

public class Solution {
    public static final int alphabetCycleLength = 'z' - 'a' + 1;
    public String solution(String s, int n) {

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = push(n, chars[i]);
        }

        String answer = String.valueOf(chars);
        return answer;
    }

    private static char push(int n, char c) {
        if (Character.isLowerCase(c)) {
            return (char) ('a' + (c + n - 'a') % alphabetCycleLength);
        } else if (Character.isUpperCase(c)) {
            return (char) ('A' + (c + n - 'A') % alphabetCycleLength);
        } else {
            return c;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        solution.solution("AB", 1);
        solution.solution("z", 1);
        solution.solution("a B z", 4);
    }
}

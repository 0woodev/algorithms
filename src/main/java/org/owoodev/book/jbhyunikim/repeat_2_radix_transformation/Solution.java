package org.owoodev.book.jbhyunikim.repeat_2_radix_transformation;

public class Solution {
    public int[] solution(String s) {
        int transformationCount = 0;
        int removedZeroCount = 0;

        while(!s.equals("1")) {
            String removeZeroStr = s.replace("0", "");
            removedZeroCount += s.length() - removeZeroStr.length();

            s = Integer.toBinaryString(removeZeroStr.length());
            transformationCount++;
        }

        return new int[]{transformationCount, removedZeroCount};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution("110010101001");
    }
}

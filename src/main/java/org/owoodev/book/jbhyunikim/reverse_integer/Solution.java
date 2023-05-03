package org.owoodev.book.jbhyunikim.reverse_integer;

public class Solution {
    public int[] solution(long n) {
        String str = String.valueOf(n);
        String reversed = new StringBuilder(str).reverse().toString();

        char[] arr =reversed.toCharArray();

        int[] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i] - '0';
        }

        return result;
    }
}
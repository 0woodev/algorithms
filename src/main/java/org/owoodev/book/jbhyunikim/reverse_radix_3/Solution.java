package org.owoodev.book.jbhyunikim.reverse_radix_3;

/**
 * 3진법 뒤집이 (68935) <br>
 * Level 1
 */
public class Solution {
    public int solution(int n) {
        String nRadix3 = Integer.toString(n, 3);
        System.out.println("n = " + n);
        System.out.println("nRadix3 = " + nRadix3);
        StringBuilder builder = new StringBuilder(nRadix3);
        String reversed = builder.reverse().toString();

        System.out.println("reversed = " + reversed);
        int answer = Integer.parseInt(reversed, 3);
        System.out.println("answer = " + answer);
        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(45);
    }
}

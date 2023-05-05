package org.owoodev.book.jbhyunikim.book_compression;

public class Solution {


    public int solution(String s) {
        if (s.length() == 1) {
            return 1;
        }
        int minimumLength = Integer.MAX_VALUE;

        for (int n = 1; n <= s.length() / 2; n++) {
            StringBuilder builder = new StringBuilder();
            String token = s.substring(0, n);
            int repeatCount = 1;
            int i;
            for (i = n; i <= s.length() - s.length() % n - n; i += n) {
                String nextToken = s.substring(i, i + n);

                if (token.equals(nextToken)) {
                    repeatCount++;
                } else {
                    if (repeatCount > 1) builder.append(repeatCount);
                    builder.append(token);

                    token = nextToken;
                    repeatCount = 1;
                }
            }

            if (repeatCount > 1) builder.append(repeatCount);
            builder.append(token);

            if (s.length() % n != 0) {
                String remainStr = s.substring(i);
                builder.append(remainStr);
            }

            minimumLength = Math.min(builder.length(), minimumLength);
            if (minimumLength == builder.length()) {
                System.out.printf("n: %3d, min: %4d, builder[%4d]: %s <-\n", n, minimumLength, builder.length(), builder.toString());
            } else {
                System.out.printf("n: %3d, min: %4d, builder[%4d]: %s\n", n, minimumLength, builder.length(), builder.toString());
            }
        }
        System.out.println("------------");
        return minimumLength;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution("aabbaccc");
        solution.solution("ababcdcdababcdcd");
        solution.solution("abcabcdede");
        solution.solution("abcabcabcabcdededededede");
        solution.solution("xababcdcdababcdcd");
        System.out.println();
    }
}

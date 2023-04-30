package org.owoodev.check_is_prefix;

public class Solution {
    public int solution(String my_string, String is_prefix) {
        return my_string.startsWith(is_prefix) ? 1 : 0;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");

        Solution solution = new Solution();

        String[][] inputs = new String[][]{
                {"banana", "ban"},
                {"banana", "nan"},
                {"banana", "abcd"},
                {"banana", "bananan"}
        };

        int[] outputs = new int[]{1, 0, 0, 0};

        for (int i = 0; i < inputs.length;  i++) {
            String my_string = inputs[i][0];
            String prefix = inputs[i][1];
            int result = solution.solution(my_string, prefix);

            String str_result = result == outputs[i] ? "성공": "실패";

            System.out.printf("%d 번째 테스트 케이스 [%s]\n", i, str_result);
        }
    }
}

package org.owoodev.minimum_rectangle;

import java.util.Arrays;

public class Solution {
    public static final int WIDTH = 0;
    public static final int HEIGHT = 1;

    public int solution(int[][] sizes) {
        int answer = 0;
        int maxHeightValue = Integer.MIN_VALUE;
        int maxWidthValue = Integer.MIN_VALUE;

        for (int[] card : sizes) {
            Arrays.sort(card);
            maxHeightValue = Math.max(maxHeightValue, card[HEIGHT]);
            maxWidthValue = Math.max(maxWidthValue, card[WIDTH]);
        }

        return maxWidthValue * maxHeightValue;
    }
}

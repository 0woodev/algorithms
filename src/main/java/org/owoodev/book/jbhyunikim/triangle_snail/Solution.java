package org.owoodev.book.jbhyunikim.triangle_snail;

import java.util.Arrays;

/**
 * 삼각 달팽이 - Level 2 <br>
 * url - https://school.programmers.co.kr/learn/courses/30/lessons/68645
 */
class Solution {
    public static final int NOT_INIT = 0;
    public static final int X = 0;
    public static final int Y = 1;

    private boolean isInBound(int x, int y, int n) {
        return (0 <= y && y < n) && (0 <= x && x <= y);
    }

    public int[] solution(int n) {
        int[][] DIRS = {{0, 1}, {1, 0}, {-1, -1}}; // South, East

        int[][] triangle = new int[n + 1][n + 1];
        for (int[] row : triangle) {
            Arrays.fill(row, NOT_INIT);
        }

        printTriangle(triangle, n);

        int posX = 0, posY = 0;
        int num = 1;

        int d = 0;
        while (true) {
            if (isInBound(posX, posY, n) && triangle[posY][posX] == NOT_INIT) {
                triangle[posY][posX] = num++;
            } else {
                break;
            }

            printTriangle(triangle, n);

            int[] dir = DIRS[d];
            while (true) {
                int nextY = posY + dir[Y];
                int nextX = posX + dir[X];

                if (isInBound(nextX, nextY, n) && triangle[nextY][nextX] == NOT_INIT) {
                    posY = nextY;
                    posX = nextX;
                    triangle[posY][posX] = num++;
                    printTriangle(triangle, n);
                } else {
                    d = (d + 1) % 3;
                    posY += DIRS[d][Y];
                    posX += DIRS[d][X];
                    break;
                }
            }
        }

        int[] answer = new int[n * (n + 1) / 2];
        // TODO 2 채워진 triangle 의 절반을 순서대로 flatten 해준 값을 리턴한다
        int idx = 0;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x <= y; x++) {
                answer[idx++] = triangle[y][x];
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");

        Solution solution = new Solution();

        int[] inputs = new int[]{9};

        for (int i = 0; i < inputs.length; i++) {
            int n = inputs[i];
            int[] result = solution.solution(n);

            System.out.printf("%d 번째 테스트 케이스 결과: " + Arrays.toString(result) + "\n", i);
        }
    }

    public void printTriangle(int[][] triangle, int n) {
        for (int y = 0; y < n; y++) {
            for (int x = 0; x <= y; x++) {
                System.out.printf("%3d", triangle[y][x]);
            }
            System.out.println();
        }
    }
}

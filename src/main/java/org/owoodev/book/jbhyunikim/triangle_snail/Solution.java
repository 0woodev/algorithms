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
        int[][] DIRS = {{0, 1}, {1, 0}}; // South, East

        int[][] triangle = new int[n + 1][n + 1];
        for (int[] row : triangle) {
            Arrays.fill(row, NOT_INIT);
        }

        printTriangle(triangle, n);

        // TODO 1 triangle 을 달팽이 방향으로 채운다
        int posX = 0, posY = 0;
        int num = 1;


        while (true) {
            // 1. 채운다
            triangle[posY][posX] = num++;
            printTriangle(triangle, n);
            boolean move = false;
            // 2-1. 아래와 오른쪽방향으로 이동한다
            for (int d = 0; d < DIRS.length; d++) {
                int[] dir = DIRS[d];
                int nextY = posY + dir[Y];
                int nextX = posX + dir[X];

                if (isInBound(nextX, nextY, n) && triangle[nextY][nextX] == NOT_INIT) {
                    posX = nextX;
                    posY = nextY;
                    move = true;
                    break;
                }

            }

            // 2-2. 대각선 방향으로 이동해야하는지 확인
            if (!move) {
                // Move NorthWest
                int[] dir = new int[]{-1, -1};

                while (true) {
                    int nextY = posY + dir[Y];
                    int nextX = posX + dir[X];

                    if (!isInBound(nextX, nextY, n)) break;

                    if (triangle[nextY][nextX] == NOT_INIT) {
                        posY = nextY;
                        posX = nextX;
                        triangle[posY][posX] = num++;
                        printTriangle(triangle, n);
                    } else {
                        // 대각선 방향으로 쭉 이동 중 더이상 이동하지 못하는 경우
                        // 아래로 내려갈 수 있는지 체크
                        nextY = posY + 1;
                        nextX = posX;
                        if (isInBound(nextX, nextY, n) && triangle[nextY][nextX] == NOT_INIT) {
                            posY = nextY;
                            posX = nextX;
                            move = true;
                        }
                        break;
                    }
                }
            }

            // 3. 아래, 오른쪽, 대각선 모든 방향으로 못 움직이면 종료
            if (!move) {
                break;
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

        int[] inputs = new int[]{1, 2};

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

package org.owoodev.book.jbhyunikim.manhattan_distance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 거리두기 확인하기<br>
 * Level 2<br>
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/81302">link...</a><br>
 * algorithms: DFS<br>
 */
public class Solution {
    // for persons
    public static final int X = 0;
    public static final int Y = 1;

    // for stack
    public static final int PERSON_NO = 2;
    public static final int DISTANCE = 3;

    public static final int MINIMUM_DISTANCE = 3;

    private boolean isInBoundary(int x, int y) {
        return (0 <= x && x < 5) && (0 <= y && y < 5);
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[]{1, 1, 1, 1, 1};

        final int[][] DIRS = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        for (int place_no = 0; place_no < places.length; place_no++) {
            char[][] place = new char[5][5];
            for (int y = 0; y < 5; y++) {
                place[y] = places[place_no][y].toCharArray();
            }

            // place 분석 및 dfs 를 위한 값 설정
            List<int[]> persons = new ArrayList<>();
            Stack<int[]> status_stack = new Stack<>();


            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (place[y][x] == 'P') {
                        int person_no = persons.size();
                        int[] person_coord = new int[]{x, y};
                        int[] coord_status = new int[]{x, y, person_no, 0};

                        persons.add(person_coord);
                        status_stack.push(coord_status);
                    }
                }
            }

            boolean[][][] visited = new boolean[persons.size()][5][5];
            for (int i = 0; i < persons.size(); i++) {
                int[] person = persons.get(i);
                int x = person[X];
                int y = person[Y];
                visited[i][y][x] = true;
            }


            printMap(place, visited, -1);

            // dfs 작업
            // manhattan_distance 를 위반하는 경우 바로 종료
            boolean is_manhattan_distance_ok = true;
            while (is_manhattan_distance_ok && !status_stack.isEmpty()) {
                int[] now_status = status_stack.pop();
                int[] person = persons.get(now_status[PERSON_NO]);
                System.out.printf("(%d, %d), person_coord: (%d, %d), distance: %d\n",
                        now_status[Y], now_status[X], person[Y] , person[X] ,now_status[DISTANCE]);
                printMap(place, visited, now_status[PERSON_NO]);
                if (now_status[DISTANCE] >= MINIMUM_DISTANCE - 1) continue;

                for (int[] dir : DIRS) {
                    int next_coord_x = now_status[X] + dir[X];
                    int next_coord_y = now_status[Y] + dir[Y];
                    int next_distance = now_status[DISTANCE] + 1;
                    int person_no = now_status[PERSON_NO];
                    int[] next_coord_status = new int[]{next_coord_x, next_coord_y, now_status[PERSON_NO], next_distance};

                    if (isInBoundary(next_coord_x, next_coord_y) && !visited[person_no][next_coord_y][next_coord_x]) {
                        char status = place[next_coord_y][next_coord_x];
                        switch (status) {
                            case 'P':
                                is_manhattan_distance_ok = false;
                                break;
                            case 'O':
                                visited[person_no][next_coord_y][next_coord_x] = true;
                                status_stack.push(next_coord_status);
                                break;
                            case 'X':
                            default:
                                break;
                        }


                    }
                }
            }

            answer[place_no] = is_manhattan_distance_ok ? 1: 0;

            if (is_manhattan_distance_ok)
                System.out.println("안전거리 오케이");
            else
                System.out.println("맨해튼 거리 안지켜짐");
        }

        return answer;
    }

    public static void main(String[] args) {
        String[][] places = new String[][]{{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};

        Solution solution = new Solution();
        int[] result = solution.solution(places);

        System.out.println(Arrays.toString(result));
    }

    private void printMap(char[][] place, boolean[][][] visited, int person_no) {
        System.out.printf("%15s\t\t%15s\n", "-----place-----", "----visited----");
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                System.out.printf("%3c", place[y][x]);
            }
            System.out.print("\t\t");
            if (person_no != -1) {
                for (int x = 0; x < 5; x++) {
                    char do_visit = visited[person_no][y][x] ? 'O' : 'X';
                    System.out.printf("%3c", do_visit);
                }
            }
            System.out.println();
        }
    }
}
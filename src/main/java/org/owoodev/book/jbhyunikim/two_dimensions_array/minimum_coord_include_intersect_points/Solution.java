package org.owoodev.book.jbhyunikim.two_dimensions_array.minimum_coord_include_intersect_points;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 교점에 별 만들기 - Level 2
 * url : https://school.programmers.co.kr/learn/courses/30/lessons/87377
 * <p>
 * 제한사항
 * line의 세로(행) 길이는 2 이상 1,000 이하인 자연수입니다.
 * line의 가로(열) 길이는 3입니다.
 * line의 각 원소는 [A, B, C] 형태입니다.
 * A, B, C는 -100,000 이상 100,000 이하인 정수입니다.
 * 무수히 많은 교점이 생기는 직선 쌍은 주어지지 않습니다.
 * A = 0이면서 B = 0인 경우는 주어지지 않습니다.
 * 정답은 1,000 * 1,000 크기 이내에서 표현됩니다.
 * 별이 한 개 이상 그려지는 입력만 주어집니다.
 */
public class Solution {
    private static class Point {
        public final long x, y;

        private Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Line {
        public long a;
        public long b;
        public long c;

        public Line(int[] input) {
            this.a = input[0];
            this.b = input[1];
            this.c = input[2];
        }
    }

    private Point intersection(Line l1, Line l2) {
        double y = (double) (l2.c * l1.a - l1.c * l2.a) / (l1.b * l2.a - l2.b * l1.a);
        double x = (double) (l1.c * l2.b - l2.c * l1.b) / (l1.b * l2.a - l2.b * l1.a);

        if (x % 1 != 0 || y % 1 != 0) return null;

        return new Point((long) x, (long) y);
    }

    private long getMinIntegerCoordY(List<Point> points) {
        return points.stream().mapToLong(p ->  p.y).min().getAsLong();
    }

    private long getMaxIntegerCoordY(List<Point> points) {
        return points.stream().mapToLong(p ->  p.y).max().getAsLong();
    }

    private long getMinIntegerCoordX(List<Point> points) {
        return points.stream().mapToLong(p ->  p.x).min().getAsLong();
    }

    private long getMaxIntegerCoordX(List<Point> points) {
        return points.stream().mapToLong(p ->  p.x).max().getAsLong();
    }

    public String[] solution(int[][] line) {
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < line.length; i++) {
            Line line1 = new Line(line[i]);
            for (int j = i + 1; j < line.length; j++) {
                Line line2 = new Line(line[j]);

                Point intersectionPoint = intersection(line1, line2);
                if (intersectionPoint != null) points.add(intersectionPoint);
            }
        }

        long maxIntegerCoordY = getMaxIntegerCoordY(points);
        long minIntegerCoordY = getMinIntegerCoordY(points);
        long minIntegerCoordX = getMinIntegerCoordX(points);
        long maxIntegerCoordX = getMaxIntegerCoordX(points);

        int height = (int) (maxIntegerCoordY - minIntegerCoordY + 1);
        int width = (int) (maxIntegerCoordX - minIntegerCoordX + 1);

        char[][] map = new char[height][width];
        for (char[] row: map) {
            Arrays.fill(row, '.');
        }

        for (Point point : points) {
            int x = (int) (point.x - minIntegerCoordX);
            int y = (int) (maxIntegerCoordY - point.y);

            map[y][x] = '*';
        }

        String[] answer = new String[height];

        for (int i = 0; i < map.length; i++) {
            answer[i] = new String(map[i]);
        }

        return answer;
    }
}

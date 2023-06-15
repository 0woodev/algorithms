package org.owoodev.cse3.defense_boom;

import java.util.*;

class Solution {
    public String[] solution(String[] commands) {
        Sheet sheet = new Sheet();

        return Arrays.stream(commands)
                .map(Command::new)
                .map(command -> command.run(sheet))
                .filter(Objects::nonNull)
                .toArray(String[]::new);
    }

    public static void main(String[] args) {
        String[] inputs = new String[]{"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"};
        Solution solution = new Solution();
        String[] outputs = solution.solution(inputs);

        for (String output : outputs) {
            System.out.printf ("%s ", output);
        }
    }

    private static class Sheet {
        Cell[][] page;

        Map<String, Set<Cell>> cellByValue;

        public Sheet() {
            page = new Cell[50][50];
            cellByValue = new HashMap<>();

            for (int r = 0; r < 50; r++) {
                for (int c = 0; c < 50; c++) {
                    page[r][c] = new Cell(r, c);
                }
            }
        }
    }

    private static class Cell {
        // Cell 의 위치값
        int r;
        int c;
        Cell parent;    // Cell 의 부모


        String value;
        // Unmerge 할때 병합그룹내에 있는 Cell 전부 해제해주기 위한 Set
        Set<Cell> mergedCells;

        public Cell(int rv, int cv) {
            r = rv;
            c = cv;
            value = null;
            mergedCells = new HashSet<>();

            parent = this;
        }

        public void merge(Cell cell2, Sheet sheet) {
            Cell root = this.getRoot();
            Cell root2 = cell2.getRoot();

            Cell newRoot;
            Cell merged;
            if (root.value == null && root2.value == null) {
                // 둘다 null 인 경우
                newRoot = root;
                merged = root2;

            } else if (root.value == null) {
                // root2.value 만 != null 인경우
                newRoot = root2;
                merged = root;
            } else if (root2.value == null) {
                // root.value 만 != null 인경우
                newRoot = root;
                merged = root2;
            } else {
                // 둘다 null 이 아닌 경우
                sheet.cellByValue.get(root2.value).remove(root2);

                root2.value = null;

                newRoot = root;
                merged = root2;
            }

            newRoot.mergedCells.addAll(merged.mergedCells);

            merged.mergedCells.forEach(c -> c.parent = root);
            merged.mergedCells.clear();
        }

        public void unmerge(Sheet sheet) {
            Cell root = this.getRoot();

            if (root.value != null) {
                sheet.cellByValue.get(root.value).remove(root);
                sheet.cellByValue.get(root.value).add(this);
            }

            this.value = root.value;
            root.value = null;

            root.mergedCells.forEach(cell -> {
                cell.parent = cell;
                cell.value = null;
            });
        }

        public Cell getRoot() {
            if (this.equals(parent)) {
                return this;
            }

            this.parent = parent.getRoot();
            return parent;
        }

        @Override
        public int hashCode() {
            return String.format("%d,%d", r, c).hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Cell
                    && this.r == ((Cell) obj).r
                    && this.c == ((Cell) obj).c;
        }
    }

    private static class Command {
        ACTION action;
        int p1_r, p1_c, p2_r, p2_c;
        String value;

        String value2;

        public Command(String command) {
            String[] token = command.split(" ");
            this.action = ACTION.valueOf(token[0]);


            switch (action) {
                case UPDATE:
                    if (token.length == 4) {
                        this.action = ACTION.UPDATE_ONE;

                        p1_r = Integer.parseInt(token[1]);
                        p1_c = Integer.parseInt(token[2]);

                        value = token[3];
                    } else {
                        this.action = ACTION.UPDATE_ALL;

                        value = token[1];
                        value2 = token[2];
                    }
                    break;
                case MERGE:
                    p1_r = Integer.parseInt(token[1]);
                    p1_c = Integer.parseInt(token[2]);

                    p2_r = Integer.parseInt(token[3]);
                    p2_c = Integer.parseInt(token[4]);
                    break;
                case UNMERGE:
                case PRINT:
                    p1_r = Integer.parseInt(token[1]);
                    p1_c = Integer.parseInt(token[2]);

                    break;
            }
        }

        public String run(Sheet sheet) {
            Set<Cell> cells;
            Cell root;
            Cell cell;
            Cell cell2;
            switch (action) {
                case UPDATE_ONE:
                    cell = sheet.page[p1_r][p1_c];
                    root = cell.getRoot();

                    sheet.cellByValue.computeIfAbsent(this.value, k -> new HashSet<>()).add(root);

                    if (root.value != null) {
                        sheet.cellByValue.computeIfAbsent(root.value, k -> new HashSet<>()).remove(root);
                    }

                    root.value = this.value;
                    return null;
                case UPDATE_ALL:
                    cells = sheet.cellByValue.computeIfAbsent(this.value, k -> new HashSet<>());
                    sheet.cellByValue.computeIfAbsent(this.value2, k -> new HashSet<>()).addAll(cells);

                    cells.forEach(c -> c.value = value2);
                    cells.clear();
                    return null;
                case MERGE:
                    cell = sheet.page[p1_r][p1_c];
                    cell2 = sheet.page[p2_r][p2_c];

                    cell.merge(cell2, sheet);
                    return null;
                case UNMERGE:
                    cell = sheet.page[p1_r][p1_c];
                    root = cell.getRoot();

                    root.unmerge(sheet);
                    return null;
                case PRINT:
                default:
                    cell = sheet.page[p1_r][p1_c];
                    root = cell.getRoot();

                    return root.value != null ? root.value : "EMPTY";
            }
        }

    }

    private enum ACTION {
        UPDATE, UPDATE_ONE, UPDATE_ALL, MERGE, UNMERGE, PRINT
    }
}
package com.pentago;

import java.util.ArrayList;
import java.util.List;

public class Const {

    public static final long[] FIVE_IN_ROW;
    public static final long[] FIVE_IN_COLUMN;
    public static final long[] FIVE_IN_DIAGONAL;

    static {
        FIVE_IN_ROW = buildRowMasks();
        FIVE_IN_COLUMN = buildColumnMasks();
        FIVE_IN_DIAGONAL = buildDiagonalMasks();
    }

    // ---------------- ROWS ----------------
    private static long[] buildRowMasks() {
        List<Long> masks = new ArrayList<>();

        for (int row = 0; row < 6; row++) {
            for (int startCol = 0; startCol <= 1; startCol++) {
                long mask = 0L;

                for (int k = 0; k < 5; k++) {
                    int index = row * 6 + (startCol + k);
                    mask |= (1L << index);
                }

                masks.add(mask);
            }
        }
        return toArray(masks);
    }

    // ---------------- COLUMNS ----------------
    private static long[] buildColumnMasks() {
        List<Long> masks = new ArrayList<>();

        for (int col = 0; col < 6; col++) {
            for (int startRow = 0; startRow <= 1; startRow++) {
                long mask = 0L;

                for (int k = 0; k < 5; k++) {
                    int index = (startRow + k) * 6 + col;
                    mask |= (1L << index);
                }

                masks.add(mask);
            }
        }
        return toArray(masks);
    }

    // ---------------- DIAGONALS ----------------
    private static long[] buildDiagonalMasks() {
        List<Long> masks = new ArrayList<>();

        // ↘
        for (int row = 0; row <= 1; row++) {
            for (int col = 0; col <= 1; col++) {
                long mask = 0L;

                for (int k = 0; k < 5; k++) {
                    int index = (row + k) * 6 + (col + k);
                    mask |= (1L << index);
                }

                masks.add(mask);
            }
        }

        // ↙
        for (int row = 0; row <= 1; row++) {
            for (int col = 4; col <= 5; col++) {
                long mask = 0L;

                for (int k = 0; k < 5; k++) {
                    int index = (row + k) * 6 + (col - k);
                    mask |= (1L << index);
                }

                masks.add(mask);
            }
        }

        return toArray(masks);
    }

    private static long[] toArray(List<Long> list) {
        long[] arr = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}

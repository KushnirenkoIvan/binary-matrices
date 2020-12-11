package ua.ikushnirenko.binary_matrices.model;

import java.util.Arrays;

/**
 * Encapsulates binary matrix as 2D array of booleans.
 * Ensures that all rows have same size, provides pretty string representation.
 */
public class BinaryMatrix {

    private final boolean[][] matrix;

    public BinaryMatrix(int rows, int cols) {
        if (rows < 0 || cols < 0) {
            throw new IllegalArgumentException("BinaryMatrix can't be instantiated with negative size");
        }

        this.matrix = new boolean[rows][cols];
    }

    public int rows() {
        return matrix.length;
    }

    public int cols() {
        return matrix[0].length;
    }

    public boolean get(int i, int j) {
        checkIdxValidity(i, matrix.length, "rows");
        checkIdxValidity(j, matrix[0].length, "cols");

        return matrix[i][j];
    }

    public void set(int i, int j, boolean val) {
        checkIdxValidity(i, matrix.length, "rows");
        checkIdxValidity(j, matrix[0].length, "cols");

        matrix[i][j] = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BinaryMatrix matrix1 = (BinaryMatrix) o;
        return Arrays.equals(matrix, matrix1.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(matrix);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(matrix[i][j] ? " 1" : " 0");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    private void checkIdxValidity(int idx, int size, String name) {
        if (idx < 0 || idx >= size) {
            throw new IllegalArgumentException(idx + " must be in range [0, " + (size - 1) + "] " + name);
        }
    }
}

package ua.ikushnirenko.binary_matrices.generator;

import ua.ikushnirenko.binary_matrices.model.BinaryMatrix;

import java.util.Random;

public class MatrixGenerator {

    private final Random randomGenerator;

    public MatrixGenerator(Random randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    /**
     * Generates matrix with random boolean values with specified size.
     *
     * @param rows - number of matrix Rows
     * @param cols - number of matrix Cols
     *
     * @return bollean[][] matrix
     */
    public BinaryMatrix generateBinary(int rows, int cols) {
        checkIsGreaterThan1(rows, "rows");
        checkIsGreaterThan1(cols, "cols");

        BinaryMatrix result = new BinaryMatrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(i, j, randomGenerator.nextBoolean());
            }
        }

        return result;
    }

    private void checkIsGreaterThan1(int param, String name) {
        if (param < 1) {
            throw new IllegalArgumentException("Number of matrix " + name + " must be greater, than 1:" + param);
        }
    }
}

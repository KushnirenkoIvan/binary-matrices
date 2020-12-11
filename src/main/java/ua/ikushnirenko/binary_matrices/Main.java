package ua.ikushnirenko.binary_matrices;

import ua.ikushnirenko.binary_matrices.generator.MatrixGenerator;
import ua.ikushnirenko.binary_matrices.model.BinaryMatrix;
import ua.ikushnirenko.binary_matrices.service.MatrixService;
import ua.ikushnirenko.binary_matrices.service.ParallelMatrixServiceImpl;
import ua.ikushnirenko.binary_matrices.service.SequentialMatrixServiceImpl;
import ua.ikushnirenko.binary_matrices.service.concurrency.CachedExecutorServiceFacade;
import ua.ikushnirenko.binary_matrices.service.task.TaskProvider;

import java.util.Random;

public class Main {

    public static final int DEFAULT_MATRIX_SIZE = 2000;

    private static final MatrixGenerator matrixGenerator = new MatrixGenerator(new Random());
    private static final TaskProvider taskProvider = new TaskProvider();

    public static void main(String[] args) {
        int matrixSize = getMatrixSize(args);

        BinaryMatrix a = matrixGenerator.generateBinary(matrixSize, matrixSize);
        BinaryMatrix b = matrixGenerator.generateBinary(matrixSize, matrixSize);

        multiplyBinaryMatrices(new SequentialMatrixServiceImpl(taskProvider), a, b);

        try (CachedExecutorServiceFacade executorService = new CachedExecutorServiceFacade(matrixSize)) {
            multiplyBinaryMatrices(new ParallelMatrixServiceImpl(taskProvider, executorService), a, b);
        }
    }

    /**
     * Would be better to use smth like Picocli for convenient CLI arguments handling.
     */
    private static int getMatrixSize(String[] args) {
        int matrixSize = DEFAULT_MATRIX_SIZE;

        if (args.length > 0) {
            matrixSize = Integer.parseInt(args[0]);
        }

        System.out.println("Binary matrices with size: " + matrixSize + " will be generated and multiplied");

        return matrixSize;
    }

    private static void multiplyBinaryMatrices(MatrixService matrixService, BinaryMatrix a, BinaryMatrix b) {
        long startMs = System.currentTimeMillis();

        matrixService.multiplyBinary(a, b);

        System.out.println("Finished multiplication in " + (System.currentTimeMillis() - startMs) + " millis");
    }
}

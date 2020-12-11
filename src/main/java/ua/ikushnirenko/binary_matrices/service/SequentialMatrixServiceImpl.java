package ua.ikushnirenko.binary_matrices.service;

import ua.ikushnirenko.binary_matrices.model.BinaryMatrix;
import ua.ikushnirenko.binary_matrices.service.task.EvaluateAndSetMultiplicationResultValueTask;
import ua.ikushnirenko.binary_matrices.service.task.TaskProvider;

public class SequentialMatrixServiceImpl extends AbstractMatrixService {

    private final TaskProvider taskProvider;

    public SequentialMatrixServiceImpl(TaskProvider taskProvider) {
        this.taskProvider = taskProvider;
    }

    @Override
    protected BinaryMatrix multiply(BinaryMatrix a, BinaryMatrix b) {
        System.out.println("Processing sequential matrix multiplication...");

        BinaryMatrix result = new BinaryMatrix(a.rows(), b.cols());

        for (int i = 0; i < result.rows(); i++) {
            for (int j = 0; j < result.cols(); j++) {
                taskProvider.get(
                        new EvaluateAndSetMultiplicationResultValueTask(i, j, a, b, result)
                ).run();
            }
        }

        return result;
    }
}

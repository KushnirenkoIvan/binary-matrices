package ua.ikushnirenko.binary_matrices.service;

import ua.ikushnirenko.binary_matrices.model.BinaryMatrix;
import ua.ikushnirenko.binary_matrices.service.task.EvaluateAndSetMultiplicationResultValueTask;
import ua.ikushnirenko.binary_matrices.service.task.TaskProvider;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class ParallelMatrixServiceImpl extends AbstractMatrixService {

    private final TaskProvider taskProvider;
    private final ExecutorService executorService;

    public ParallelMatrixServiceImpl(TaskProvider taskProvider,
                                     ExecutorService executorService) {
        this.taskProvider = taskProvider;
        this.executorService = executorService;
    }

    @Override
    protected BinaryMatrix multiply(BinaryMatrix a, BinaryMatrix b) {
        System.out.println("Processing parallel matrix multiplication...");

        CountDownLatch countDownLatch = new CountDownLatch(a.rows() * b.cols());

        BinaryMatrix result = new BinaryMatrix(a.rows(), b.cols());
        for (int i = 0; i < result.rows(); i++) {
            for (int j = 0; j < result.cols(); j++) {
                executorService.submit(
                        taskProvider.get(new EvaluateAndSetMultiplicationResultValueTask(i, j, a, b, result, countDownLatch))
                );
            }
        }

        awaitAllTasksComplete(countDownLatch);

        return result;
    }

    private void awaitAllTasksComplete(CountDownLatch countDownLatch) {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException("Can't await when all computations finished", e);
        }
    }
}

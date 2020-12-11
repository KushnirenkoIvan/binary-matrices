package ua.ikushnirenko.binary_matrices.service;

import ua.ikushnirenko.binary_matrices.model.BinaryMatrix;
import ua.ikushnirenko.binary_matrices.service.task.EvaluateAndSetMultiplicationResultValueTask;
import ua.ikushnirenko.binary_matrices.service.task.TaskProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

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

        BinaryMatrix result = new BinaryMatrix(a.rows(), b.cols());

        List<Future<?>> parallelTasks = new ArrayList<>();
        for (int i = 0; i < result.rows(); i++) {
            for (int j = 0; j < result.cols(); j++) {
                parallelTasks.add(
                        executorService.submit(
                                taskProvider.get(new EvaluateAndSetMultiplicationResultValueTask(i, j, a, b, result))
                        )
                );
            }
        }

        ensureAllTasksCompleted(parallelTasks);

        return result;
    }

    /**
     * Iterates over provided list of Future tasks until all of them are done.
     */
    private void ensureAllTasksCompleted(List<Future<?>> parallelTasks) {
        boolean isIncompleteTaskPresent = true;
        while (isIncompleteTaskPresent) {
            for (Future<?> f : parallelTasks) {
                isIncompleteTaskPresent = !f.isDone();
            }
        }
    }
}

package ua.ikushnirenko.binary_matrices.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.ikushnirenko.binary_matrices.model.BinaryMatrix;
import ua.ikushnirenko.binary_matrices.service.task.Task;
import ua.ikushnirenko.binary_matrices.service.task.TaskProvider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParallelMatrixServiceImplTest {

    private final TaskProvider taskProviderMock = mock(TaskProvider.class);
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private final ParallelMatrixServiceImpl processor = new ParallelMatrixServiceImpl(taskProviderMock, executorService);

    @BeforeEach
    void setUp() {
        when(taskProviderMock.get(any(Task.class))).thenCallRealMethod();
    }

    @Test
    void multiplyBinary() {
        BinaryMatrix a = new BinaryMatrix(2, 3);
        a.set(0, 0, true);
        a.set(0, 1, true);
        a.set(0, 2, false);
        a.set(1, 0, false);
        a.set(1, 1, false);
        a.set(1, 2, true);

        BinaryMatrix b = new BinaryMatrix(3, 2);
        b.set(0, 0, false);
        b.set(0, 1, false);
        b.set(1, 0, true);
        b.set(1, 1, false);
        b.set(2, 0, false);
        b.set(2, 1, true);

        BinaryMatrix result = processor.multiplyBinary(a, b);

        assertEquals(2, result.rows());
        assertEquals(2, result.cols());

        assertTrue(result.get(0, 0));
        assertFalse(result.get(0, 1));
        assertFalse(result.get(1, 0));
        assertTrue(result.get(1, 1));
    }
}

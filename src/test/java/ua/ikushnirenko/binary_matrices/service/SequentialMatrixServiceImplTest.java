package ua.ikushnirenko.binary_matrices.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.ikushnirenko.binary_matrices.model.BinaryMatrix;
import ua.ikushnirenko.binary_matrices.service.task.Task;
import ua.ikushnirenko.binary_matrices.service.task.TaskProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SequentialMatrixServiceImplTest {

    private final TaskProvider taskProviderMock = mock(TaskProvider.class);
    private final SequentialMatrixServiceImpl processor = new SequentialMatrixServiceImpl(taskProviderMock);

    @BeforeEach
    void setUp() {
        when(taskProviderMock.get(any(Task.class))).thenCallRealMethod();
    }

    @Test
    void multiplyNotCompliantMatrices() {
        BinaryMatrix a = new BinaryMatrix(2, 2);
        a.set(0, 0, true);
        a.set(0, 1, true);
        a.set(1, 0, true);
        a.set(1, 1, true);

        BinaryMatrix b = new BinaryMatrix(1, 1);
        b.set(0, 0, true);

        assertThrows(IllegalArgumentException.class, () -> processor.multiplyBinary(a, b));
    }

    @Test
    void multiplyBinary() {
        BinaryMatrix a = new BinaryMatrix(2, 3);
        a.set(0, 0, true);
        a.set(0, 1, false);
        a.set(0, 2, true);
        a.set(1, 0, false);
        a.set(1, 1, true);
        a.set(1, 2, true);

        BinaryMatrix b = new BinaryMatrix(3, 2);
        b.set(0, 0, true);
        b.set(0, 1, false);
        b.set(1, 0, false);
        b.set(1, 1, true);
        b.set(2, 0, true);
        b.set(2, 1, false);

        BinaryMatrix result = processor.multiplyBinary(a, b);

        assertEquals(2, result.rows());
        assertEquals(2, result.cols());

        assertTrue(result.get(0, 0));
        assertFalse(result.get(0, 1));
        assertTrue(result.get(1, 0));
        assertTrue(result.get(1, 1));
    }
}

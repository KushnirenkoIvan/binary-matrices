package ua.ikushnirenko.binary_matrices.generator;

import org.junit.jupiter.api.Test;
import ua.ikushnirenko.binary_matrices.model.BinaryMatrix;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MatrixGeneratorTest {

    private final Random randomMock = mock(Random.class);
    private final MatrixGenerator matrixGenerator = new MatrixGenerator(randomMock);

    @Test
    void generateWithInvalidRowsNumber() {
        assertThrows(IllegalArgumentException.class, () -> matrixGenerator.generateBinary(0, 1));
    }

    @Test
    void generateWithInvalidColsNumber() {
        assertThrows(IllegalArgumentException.class, () -> matrixGenerator.generateBinary(1, 0));
    }

    @Test
    void generateBinary2x2() {
        when(randomMock.nextBoolean()).thenReturn(true, false, true, false);

        BinaryMatrix result = matrixGenerator.generateBinary(2, 2);

        assertTrue(result.get(0, 0));
        assertFalse(result.get(0, 1));
        assertTrue(result.get(1, 0));
        assertFalse(result.get(1, 1));
    }
}

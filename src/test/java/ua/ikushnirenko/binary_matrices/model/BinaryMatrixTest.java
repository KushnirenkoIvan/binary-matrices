package ua.ikushnirenko.binary_matrices.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BinaryMatrixTest {

    @Test
    void getWithWrongIdx() {
        assertThrows(IllegalArgumentException.class, () -> new BinaryMatrix(1, 1).get(-1, 1));
    }

    @Test
    void setWithWrongIdx() {
        assertThrows(IllegalArgumentException.class, () -> new BinaryMatrix(1, 1).get(1, 2));
    }
}

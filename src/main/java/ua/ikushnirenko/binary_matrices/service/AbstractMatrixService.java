package ua.ikushnirenko.binary_matrices.service;

import ua.ikushnirenko.binary_matrices.model.BinaryMatrix;

public abstract class AbstractMatrixService implements MatrixService {

    @Override
    public BinaryMatrix multiplyBinary(BinaryMatrix a, BinaryMatrix b) {
        if (a.cols() != b.rows()) {
            throw new IllegalArgumentException(
                    "Matrices are not compliant: a.rows=" + a.rows() + " isn't equal to b.cols=" + b.cols()
            );
        }

        return multiply(a, b);
    }

    protected abstract BinaryMatrix multiply(BinaryMatrix a, BinaryMatrix b);

}

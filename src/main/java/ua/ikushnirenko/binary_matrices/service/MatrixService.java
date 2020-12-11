package ua.ikushnirenko.binary_matrices.service;

import ua.ikushnirenko.binary_matrices.model.BinaryMatrix;

public interface MatrixService {

    BinaryMatrix multiplyBinary(BinaryMatrix a, BinaryMatrix b);
}

package ua.ikushnirenko.binary_matrices.service.task;

import ua.ikushnirenko.binary_matrices.model.BinaryMatrix;

/**
 * Base unit of computation - evaluates 1 element at specified indexes for result matrix
 * using 'i' row from matrix 'a' and 'j' column at matrix 'b'.
 */
public class EvaluateAndSetMultiplicationResultValueTask implements Task {

    private final int resultRowIdx;
    private final int resultColIdx;

    private final BinaryMatrix a;
    private final BinaryMatrix b;

    private final BinaryMatrix result;

    public EvaluateAndSetMultiplicationResultValueTask(int resultRowIdx,
                                                       int resultColIdx,
                                                       BinaryMatrix a,
                                                       BinaryMatrix b,
                                                       BinaryMatrix result) {
        //todo: implement checks for input params

        this.resultRowIdx = resultRowIdx;
        this.resultColIdx = resultColIdx;
        this.a = a;
        this.b = b;
        this.result = result;
    }

    @Override
    public void run() {
        boolean c_ = false;

        int idx = 0;
        while (idx < a.cols()) {
            boolean a_ = a.get(resultRowIdx, idx);
            boolean b_ = b.get(idx, resultColIdx);

            c_ = c_ | (a_ & b_);
            idx++;
        }

        result.set(resultRowIdx, resultColIdx, c_);
    }
}

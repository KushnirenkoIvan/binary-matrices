package ua.ikushnirenko.binary_matrices.service.task;

import ua.ikushnirenko.binary_matrices.model.BinaryMatrix;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

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

    private final CountDownLatch countDownLatch;

    public EvaluateAndSetMultiplicationResultValueTask(int resultRowIdx,
                                                       int resultColIdx,
                                                       BinaryMatrix a,
                                                       BinaryMatrix b,
                                                       BinaryMatrix result) {
        this(resultRowIdx, resultColIdx, a, b, result, null);
    }

    public EvaluateAndSetMultiplicationResultValueTask(int resultRowIdx,
                                                       int resultColIdx,
                                                       BinaryMatrix a,
                                                       BinaryMatrix b,
                                                       BinaryMatrix result,
                                                       CountDownLatch countDownLatch) {
        //todo: implement checks for input params
        this.resultRowIdx = resultRowIdx;
        this.resultColIdx = resultColIdx;
        this.a = a;
        this.b = b;
        this.result = result;
        this.countDownLatch = countDownLatch;
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

        Optional.ofNullable(countDownLatch).ifPresent(CountDownLatch::countDown);
    }
}

package com.pentago.LineEvaluator;

import com.pentago.PentagoBoard;

public class DiagnalEvaluator extends MasksEvaluator implements LineEvalutor{

    public int evaluate(PentagoBoard board, boolean isBlack){
        long[] diag_masks = {0b100000010000001000000100000010L, 0b100000010000001000000100000010000000L, 0b10000001000000100000010000001000000L,
            0b10000001000000100000010000001L, 0b10000100001000010000100000L, 0b1000010000100001000010000000000L,
            0b10000100001000010000100000000000L, 0b1000010000100001000010000L};
        return super.evaluate(board, isBlack, diag_masks);
    }
}

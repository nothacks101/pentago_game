package com.pentago.LineEvaluator;

import com.pentago.PentagoBoard;

public class ColumnEvaluator extends MasksEvaluator implements LineEvalutor {

    public int evaluate(PentagoBoard board, boolean isBlack){
        long[] col_masks = {0b1000001000001000001000001L, 0b10000010000010000010000010L, 0b100000100000100000100000100L,
            0b1000001000001000001000001000L, 0b10000010000010000010000010000L, 0b1000001000001000001000001000000L,
            0b10000010000010000010000010000000L, 0b100000100000100000100000100000000L, 0b1000001000001000001000001000000000L, 
            0b10000010000010000010000010000000000L};
        return super.evaluate(board, isBlack, col_masks);
    }
}

package com.pentago.LineEvaluator;

import com.pentago.PentagoBoard;

public class RowEvaluator extends MasksEvaluator implements LineEvalutor{

    public int evaluate(PentagoBoard board, boolean isBlack){
        long[] row_masks = {0b111110L, 0b111110000000L, 0b111110000000000000L, 0b111110000000000000000000L,
            0b111110000000000000000000000000L, 0b111110000000000000000000000000000000L,
            0b11111L, 0b11111000000L, 0b11111000000000000L, 0b11111000000000000000000L,
            0b11111000000000000000000000000L, 0b11111000000000000000000000000000000L};
        return super.evaluate(board, isBlack, row_masks);
    }

}

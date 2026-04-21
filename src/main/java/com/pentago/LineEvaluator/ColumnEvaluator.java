package com.pentago.LineEvaluator;

import com.pentago.Const;
import com.pentago.PentagoBoard;

public class ColumnEvaluator extends MasksEvaluator implements LineEvalutor {

    public int evaluate(PentagoBoard board, boolean isBlack){
        return super.evaluate(board, isBlack, Const.FIVE_IN_COLUMN);
    }
}

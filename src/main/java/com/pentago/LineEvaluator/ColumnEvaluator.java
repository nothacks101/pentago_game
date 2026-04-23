package com.pentago.LineEvaluator;

import com.pentago.Const;
import com.pentago.PentagoBoard;

public class ColumnEvaluator extends MasksEvaluator implements LineEvalutor {

    public int evaluate(PentagoBoard board, boolean isPlayerBlack){
        return super.evaluate(board, isPlayerBlack, Const.FIVE_IN_COLUMN);
    }
}

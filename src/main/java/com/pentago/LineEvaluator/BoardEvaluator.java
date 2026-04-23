package com.pentago.LineEvaluator;

import com.pentago.PentagoBoard;

public class BoardEvaluator implements LineEvalutor{

    private LineEvalutor[] evalutors = {
            new ColumnEvaluator(),
            new RowEvaluator(),
            new DiagonalEvaluator()
    };

    @Override
    public int evaluate(PentagoBoard board, boolean isPlayerBlack) {
        int score = 0;
        for (LineEvalutor evalutor: evalutors){
            score += evalutor.evaluate(board, isPlayerBlack);
        }
        return score;
    }
}

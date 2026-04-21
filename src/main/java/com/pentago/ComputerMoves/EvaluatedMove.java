package com.pentago.ComputerMoves;

import com.pentago.LineEvaluator.BoardEvaluator;
import com.pentago.PentagoBoard;

public class EvaluatedMove implements ComputerMoves{
    private BoardEvaluator evaluator = new BoardEvaluator();

    @Override
    public int getMovement(PentagoBoard board, boolean isBlack) {
        int best_score = 0;
        int best_move = 0;
        for (int index = 0; index < 36; index++){
            if (board.checkLegal(index)){
                PentagoBoard temp_board = board.copyBoard();
                temp_board.updateBoard(index, !isBlack);
                int score = evaluator.evaluate(temp_board, isBlack);
                if (score > best_score){
                    best_score = score;
                    best_move = index;
                }
            }
        }
        return best_move;
    }
}

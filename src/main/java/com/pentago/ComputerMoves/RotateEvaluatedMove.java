package com.pentago.ComputerMoves;

import com.pentago.LineEvaluator.BoardEvaluator;
import com.pentago.PentagoBoard;

public class RotateEvaluatedMove  implements ComputerMoves{
    private BoardEvaluator evaluator = new BoardEvaluator();
    private EvaluatedMove evaluatedMove = new EvaluatedMove();

    @Override
    public int getMovement(PentagoBoard board, boolean isBlack) {
        int chosen_move = 0;
        int best_score = 0;
        for (int quadrant = 1; quadrant <= 4; quadrant++) {
            for (int rotation = 1; rotation <= 2; rotation++) {
                PentagoBoard tempBoard = board.copyBoard();
                tempBoard.updateRotaion(quadrant, rotation);
                int best_move = evaluatedMove.getMovement(tempBoard, isBlack);
                tempBoard.updateBoard(best_move, !isBlack);
                int score = evaluator.evaluate(tempBoard, isBlack);
                if (score > best_score){
                    best_score = score;
                    chosen_move = best_move;
                }
            }
        }
        return chosen_move;
    }
}


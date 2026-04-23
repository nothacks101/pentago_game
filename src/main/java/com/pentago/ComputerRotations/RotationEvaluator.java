package com.pentago.ComputerRotations;

import com.pentago.ComputerMoves.RotateLoseMove;
import com.pentago.LineEvaluator.BoardEvaluator;
import com.pentago.PentagoBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RotationEvaluator implements ComputerRotations {
    private static final Logger logger = LoggerFactory.getLogger(RotateLoseMove.class);
    private final BoardEvaluator evaluator = new BoardEvaluator();

    @Override
    public Rotation getRotation(PentagoBoard board, boolean isPlayerBlack, int moveIndex) {
        Rotation bestRotation = null;
        int bestScore = Integer.MIN_VALUE;
        for (int quadrant = 1; quadrant <= 4; quadrant++) {
            for (int clockwise = 0; clockwise <= 1; clockwise++) {
                PentagoBoard temp = board.copyBoard();

                // 1. הנחה
                temp.updateBoard(moveIndex, isPlayerBlack);

                // 2. סיבוב
                temp.updateRotation(quadrant, clockwise == 1);

                int score = evaluator.evaluate(temp, isPlayerBlack);

                if (score > bestScore) {
                    bestScore = score;
                    bestRotation = new Rotation(quadrant, clockwise == 1);
                }
            }
        }
        return bestRotation;
    }
}

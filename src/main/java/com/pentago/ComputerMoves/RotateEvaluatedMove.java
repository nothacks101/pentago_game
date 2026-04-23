package com.pentago.ComputerMoves;

import com.pentago.LineEvaluator.BoardEvaluator;
import com.pentago.PentagoBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RotateEvaluatedMove  implements ComputerMoves{
    private static final Logger logger = LoggerFactory.getLogger(RotateEvaluatedMove.class);
    private BoardEvaluator evaluator = new BoardEvaluator();

    @Override
    public Move getMovement(PentagoBoard board, boolean isPlayerBlack) {

        Move bestMove = null;
        int bestScore = Integer.MIN_VALUE;

        for (int pos = 0; pos < 36; pos++) {
            if (!board.checkLegal(pos)) continue;

            for (int q = 1; q <= 4; q++) {
                for (int cw = 0; cw <= 1; cw++) {

                    PentagoBoard temp = board.copyBoard();
                    temp.updateBoard(pos, isPlayerBlack);
                    temp.updateRotation(q, cw == 1);

                    int score = evaluator.evaluate(temp, isPlayerBlack);

                    if (score > bestScore) {
                        logger.debug("found new best score with values : \n score: {}, pos: {}, q: {}, clockwise: {}",
                                score, pos, q, cw == 1);
                        bestScore = score;
                        bestMove = new Move(pos, q, cw == 1);
                    }
                }
            }
        }
       return bestMove;
    }
}


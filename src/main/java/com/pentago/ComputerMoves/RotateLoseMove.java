package com.pentago.ComputerMoves;

import com.pentago.Enums.BoardStatus;
import com.pentago.LineEvaluator.BoardEvaluator;
import com.pentago.PentagoBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RotateLoseMove implements ComputerMoves {
    private static final Logger logger = LoggerFactory.getLogger(RotateLoseMove.class);

    private final BoardEvaluator evaluator = new BoardEvaluator();

    public Move getMovement(PentagoBoard board, boolean isPlayerBlack) {
        BoardStatus opponentWin = isPlayerBlack ? BoardStatus.BLACK_WIN : BoardStatus.WHITE_WIN;

        Move bestMove = null;
        int bestScore = Integer.MIN_VALUE;
        boolean isUnderTreat = false;

        for (int pos = 0; pos < 36; pos++) {
            if (!board.checkLegal(pos)) continue;

            for (int q = 1; q <= 4; q++) {
                for (int cw = 0; cw <= 1; cw++) {

                    PentagoBoard temp = board.copyBoard();
                    temp.updateBoard(pos, isPlayerBlack);
                    temp.updateRotation(q, cw == 1);

                    boolean opponentCanWin = false;

                    for (int oppPos = 0; oppPos < 36 && !opponentCanWin; oppPos++) {
                        if (!temp.checkLegal(oppPos)) continue;

                        for (int oq = 1; oq <= 4 && !opponentCanWin; oq++) {
                            for (int ocw = 0; ocw <= 1; ocw++) {

                                PentagoBoard opp = temp.copyBoard();
                                opp.updateBoard(oppPos, !isPlayerBlack);
                                opp.updateRotation(oq, ocw == 1);

                                if (opp.checkWin() == opponentWin) {
                                    opponentCanWin = true;
                                    isUnderTreat = true;
                                }
                            }
                        }
                    }

                    if (!opponentCanWin) {
                        int score = evaluator.evaluate(temp, isPlayerBlack);

                        if (score > bestScore) {
                            bestScore = score;
                            bestMove = new Move(pos, q, cw == 1);
                        }
                    }
                }
            }
        }
        if (isUnderTreat){
            return bestMove;
        } else {
            return null;
        }
    }
}

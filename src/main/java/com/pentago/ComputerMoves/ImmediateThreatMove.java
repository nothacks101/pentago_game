package com.pentago.ComputerMoves;

import com.pentago.PentagoBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImmediateThreatMove implements ComputerMoves {
    private static final Logger logger = LoggerFactory.getLogger(ImmediateThreatMove.class);
    public int getMovement(PentagoBoard board, boolean isBlack){
        logger.debug("in ImmediateThreatMove");
        long opponentBoard = isBlack ? board.getWhiteBoard() : board.getBlackBoard();
        long playerBoard = isBlack ? board.getBlackBoard() : board.getWhiteBoard();
        long newOpponentBoard;
        for (int i = 0; i < 36; i++) {
            if (board.checkLegal(i)) {
                newOpponentBoard = opponentBoard | (1L << i);
                int winCheck = board.checkWin(playerBoard, newOpponentBoard);
                if ((isBlack && winCheck == 1) || (!isBlack && winCheck == 2)) {
                    return i;
                }
            }
        }
        return -1;
    }
}

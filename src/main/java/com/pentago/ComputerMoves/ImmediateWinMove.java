package com.pentago.ComputerMoves;

import com.pentago.Main;
import com.pentago.PentagoBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImmediateWinMove implements ComputerMoves {
    private static final Logger logger = LoggerFactory.getLogger(ImmediateWinMove.class);

    public int getMovement(PentagoBoard board, boolean isBlack){
        logger.debug("in ImmediateWinMove");
        long cboard = isBlack ? board.getBlackBoard() : board.getWhiteBoard();
        long opponentBoard = isBlack ? board.getWhiteBoard() : board.getBlackBoard();
        for (int i = 0; i < 36; i++) {
            if (board.checkLegal(i)) {
                long newBoardState = cboard | (1L << i);
                int winCheck = board.checkWin(newBoardState, opponentBoard);

                if ((isBlack && winCheck == 2) || (!isBlack && winCheck == 1)) {
                    return i;
                }
            }
        }
        return -1;
    }
}

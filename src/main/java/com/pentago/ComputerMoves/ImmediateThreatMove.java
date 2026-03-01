package com.pentago.ComputerMoves;

import com.pentago.PentagoBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImmediateThreatMove implements ComputerMoves {
    private static final Logger logger = LoggerFactory.getLogger(ImmediateThreatMove.class);
    public int getMovement(PentagoBoard board, boolean isBlack){
        logger.debug("in ImmediateThreatMove");
        for (int i = 0; i < 36; i++) {
            if (board.checkLegal(i)) {
                PentagoBoard check = new PentagoBoard();
                if (isBlack){
                    check.setWhiteBoard(board.getWhiteBoard() | (1L << i));
                    check.setBlackBoard(board.getBlackBoard());
                } else {
                    check.setWhiteBoard(board.getWhiteBoard());
                    check.setBlackBoard(board.getBlackBoard() | (1L << i));
                }
                check.setOccupiedBoard(check.getBlackBoard() | check.getWhiteBoard());
                int winCheck = check.checkWin();

                if (winCheck != 0){
                    logger.debug("lose winstate is {} for index {}", winCheck, i);
                }
                if ((!isBlack && winCheck == 1) || (isBlack && winCheck == 2)) {
                    return i;
                }
            }
        }
        return -1;
    }
}

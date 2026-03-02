package com.pentago.ComputerMoves;

import com.pentago.Main;
import com.pentago.PentagoBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class 
ImmediateWinMove implements ComputerMoves {
    private static final Logger logger = LoggerFactory.getLogger(ImmediateWinMove.class);

    public int getMovement(PentagoBoard board, boolean isBlack){
        logger.debug("in ImmediateWinMove");
        for (int i = 0; i < 36; i++) {
            if (board.checkLegal(i)) {
                PentagoBoard check = new PentagoBoard();
                if (isBlack){
                    check.setWhiteBoard(board.getWhiteBoard());
                    check.setBlackBoard(board.getBlackBoard() | (1L << i));
                } else {
                    check.setWhiteBoard(board.getWhiteBoard() | (1L << i));
                    check.setBlackBoard(board.getBlackBoard());
                }
                check.setOccupiedBoard(check.getBlackBoard() | check.getWhiteBoard());
                int winCheck = check.checkWin();
                if (winCheck != 0){
                    logger.debug("win winstate is {} for index {}, isblack is {}", winCheck, i, isBlack);
                }
                if ((!isBlack && winCheck == 2) || (isBlack && winCheck == 1)) {
                    return i;
                }
            }
        }
        return -1;
    }
}

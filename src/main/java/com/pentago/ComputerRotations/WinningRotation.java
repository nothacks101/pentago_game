package com.pentago.ComputerRotations;

import com.pentago.PentagoBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WinningRotation implements ComputerRotations {
    private static final Logger logger = LoggerFactory.getLogger(WinningRotation.class);
    public int getRotation(PentagoBoard board, boolean isBlack){
        logger.debug("in WinningRotation");
        Byte winstate =0;
        for (int quadrant = 1; quadrant <= 4; quadrant++) {
            for (int rotation = 1; rotation <= 2; rotation++) {
                PentagoBoard tempBoard = new PentagoBoard();
                tempBoard.setBlackBoard(board.getBlackBoard());
                tempBoard.setWhiteBoard(board.getWhiteBoard());
                tempBoard.setOccupiedBoard(board.getOccupiedBoard());
                tempBoard.updateRotaion(quadrant, rotation);
                winstate = tempBoard.checkWin(tempBoard.getBlackBoard(), tempBoard.getWhiteBoard());
                if ((isBlack && winstate == 1)) //TODO check
                {
                    return (quadrant - 1) * 2 + rotation;
                } else if (!isBlack && winstate == 2) {
                    return (quadrant - 1) * 2 + rotation;
                }
            }
        }
        return -1;
    }
}

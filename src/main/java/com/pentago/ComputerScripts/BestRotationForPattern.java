package com.pentago.ComputerScripts;

import com.pentago.PentagoBoard;

public class BestRotationForPattern {
    public int findBestRotation(PentagoBoard board, boolean isBlack, long pattern) {
        long cboard = isBlack ? board.getWhiteBoard() : board.getBlackBoard(); 
        for (int quadrant = 1; quadrant <= 4; quadrant++) {
            for (int rotation = 1; rotation <= 2; rotation++) {
                PentagoBoard tempBoard = board.copyBoard();
                tempBoard.updateRotaion(quadrant, rotation);

                long newCboard = isBlack ? tempBoard.getBlackBoard() : tempBoard.getWhiteBoard();
                if ((newCboard & pattern) > (cboard & pattern)) {
                    return (quadrant - 1) * 2 + rotation;
                }
            }
        }
        return -1;
    }
}

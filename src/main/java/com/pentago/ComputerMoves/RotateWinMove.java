package com.pentago.ComputerMoves;

import com.pentago.Main;
import com.pentago.PentagoBoard;
import com.pentago.ComputerScripts.FindQuadrant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RotateWinMove implements ComputerMoves {
    private static final Logger logger = LoggerFactory.getLogger(RotateWinMove.class);
    private ImmediateWinMove immediateWinMove = new ImmediateWinMove();
    private FindQuadrant findQuadrant = new FindQuadrant();

    public int getMovement(PentagoBoard board, boolean isBlack){
        logger.debug("in RotateWinMove");
        int[] right = {12,  6,  0, 15,  9,  3, 13,  7,  1, 16, 10,  4, 14,  8,  2, 17, 11,  5, 30, 24, 18, 33, 27, 21, 31, 25, 19, 34, 28, 22, 32, 26, 20, 35, 29, 23};
        int[] left ={ 2,  8, 14,  5, 11, 17, 1,  7, 13,  4, 10, 16, 0,  6, 12,  3,  9, 15, 20, 26, 32, 23, 29, 35, 19, 25, 31, 22, 28, 34, 18, 24, 30, 21, 27, 33};
        int win_index =0;
        int index;
        for (int quadrant = 1; quadrant <= 4; quadrant++) {
            for (int rotation = 1; rotation <= 2; rotation++) {
                PentagoBoard tempBoard = board.copyBoard();
                tempBoard.updateRotaion(quadrant, rotation);
                win_index = immediateWinMove.getMovement(tempBoard, isBlack);
                
                if (win_index != -1) {
                    if (rotation == 1) {
                        index = right[win_index];
                    } else {
                        index = left[win_index];
                    }
                    if (findQuadrant.find(index) == quadrant) {
                        return index;
                    }
                }
            }
        }
        return -1;
    }
}

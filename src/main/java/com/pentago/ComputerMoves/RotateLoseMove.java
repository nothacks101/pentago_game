package com.pentago.ComputerMoves;

import com.pentago.PentagoBoard;
import com.pentago.ComputerScripts.FindQuadrant;

public class RotateLoseMove implements ComputerMoves{
    private ImmediateThreatMove immediateThreatMove = new ImmediateThreatMove();
    private FindQuadrant findQuadrant = new FindQuadrant();

    public int getMovement(PentagoBoard board, boolean isBlack) {
        int[] right = {12,  6,  0, 15,  9,  3, 13,  7,  1, 16, 10,  4, 14,  8,  2, 17, 11,  5, 30, 24, 18, 33, 27, 21, 31, 25, 19, 34, 28, 22, 32, 26, 20, 35, 29, 23};
        int[] left ={ 2,  8, 14,  5, 11, 17, 1,  7, 13,  4, 10, 16, 0,  6, 12,  3,  9, 15, 20, 26, 32, 23, 29, 35, 19, 25, 31, 22, 28, 34, 18, 24, 30, 21, 27, 33};
        int lose_index = 0;
        int index;
        for (int quadrant = 1; quadrant <= 4; quadrant++) {
            for (int rotation = 1; rotation <= 2; rotation++) {
                PentagoBoard tempBoard = new PentagoBoard();
                tempBoard.setBlackBoard(board.getBlackBoard());
                tempBoard.setWhiteBoard(board.getWhiteBoard());
                tempBoard.setOccupiedBoard(board.getOccupiedBoard());
                tempBoard.updateRotaion(quadrant, rotation);

                lose_index = immediateThreatMove.getMovement(tempBoard, isBlack);
                if (lose_index != -1) {
                    if (rotation == 1) {
                        index = right[lose_index];
                    } else {
                        index = left[lose_index];
                    }
                    if (findQuadrant.find(index) != quadrant) {
                        return lose_index;
                    }
                    return index;
                }
            }
        }
        return -1;
    }
}

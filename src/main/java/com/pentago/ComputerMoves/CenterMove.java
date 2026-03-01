package com.pentago.ComputerMoves;

import com.pentago.PentagoBoard;

public class CenterMove implements ComputerMoves {
    public int getMovement(PentagoBoard board, boolean isBlack){
        int[] centers = {7, 10, 25, 28};
        for (int center : centers) {
            if (board.checkLegal(center)) {
                return center;
            }
        }
        return -1;
    }
    
}

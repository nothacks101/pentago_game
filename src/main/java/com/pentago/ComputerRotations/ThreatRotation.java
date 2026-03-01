package com.pentago.ComputerRotations;

import com.pentago.PentagoBoard;

public class ThreatRotation implements ComputerRotations {
    public int getRotation(PentagoBoard board, boolean isBlack){
        for (int quadrant = 1; quadrant <= 4; quadrant++) {
            for (int rotation = 1; rotation <= 2; rotation++) {
                PentagoBoard tempBoard = new PentagoBoard();
                tempBoard.setBlackBoard(board.getBlackBoard());
                tempBoard.setWhiteBoard(board.getWhiteBoard());
                tempBoard.setOccupiedBoard(board.getOccupiedBoard());
                tempBoard.updateRotaion(quadrant, rotation);
                if (isBlack && tempBoard.checkWin(tempBoard.getWhiteBoard(), tempBoard.getBlackBoard()) == 2)
                {
                    if(rotation == 1)
                    {
                        return (quadrant - 1) * 2 + 2;
                    }
                    return (quadrant - 1) * 2 + 1;
                } else if (!isBlack && tempBoard.checkWin(tempBoard.getBlackBoard(), tempBoard.getWhiteBoard()) == 1)
                {
                    if(rotation == 1)
                    {
                        return (quadrant - 1) * 2 + 2;
                    }
                    return (quadrant - 1) * 2 + 1;
                }
            }
        }
        return -1;
    }
}

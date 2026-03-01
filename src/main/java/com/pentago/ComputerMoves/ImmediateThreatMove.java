package com.pentago.ComputerMoves;

import com.pentago.PentagoBoard;

public class ImmediateThreatMove implements ComputerMoves {
    public int getMovement(PentagoBoard board, boolean isBlack){
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

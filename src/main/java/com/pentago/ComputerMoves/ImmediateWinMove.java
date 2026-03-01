package com.pentago.ComputerMoves;

import com.pentago.PentagoBoard;

public class ImmediateWinMove implements ComputerMoves {
    public int getMovement(PentagoBoard board, boolean isBlack){
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

package com.pentago.ComputerMoves;

import com.pentago.PentagoBoard;

public interface ComputerMoves {
    public int getMovement(PentagoBoard board, boolean isBlack);
}

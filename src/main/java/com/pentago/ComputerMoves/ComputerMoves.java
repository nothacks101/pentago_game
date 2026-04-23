package com.pentago.ComputerMoves;

import com.pentago.PentagoBoard;

public interface ComputerMoves {
    public Move getMovement(PentagoBoard board, boolean isBlack);
}

package com.pentago.PlayerMove;

import com.pentago.ComputerMoves.Move;
import com.pentago.PentagoBoard;

public interface MoveMaker {
    public Move makeMovement(PentagoBoard board, boolean isBlack);
}

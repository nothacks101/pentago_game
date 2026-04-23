package com.pentago.ComputerRotations;

import com.pentago.PentagoBoard;

public interface ComputerRotations {
    public Rotation getRotation(PentagoBoard board, boolean isPlayerBlack, int moveIndex);
}

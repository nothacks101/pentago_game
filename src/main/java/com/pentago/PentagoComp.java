package com.pentago;

import com.pentago.ComputerMoves.ComputerMoves;
import com.pentago.ComputerRotations.ComputerRotations;

public class PentagoComp
{
    private PentagoBoard board;
    private ComputerMoves[] orderedComputerMoves;
    private ComputerRotations[] orderedComputerRotations;

    public PentagoComp(PentagoBoard board, ComputerRotations[] orderedComputerRotations, ComputerMoves[] orderedComputerMoves)
    {
        this.board = board;
        this.orderedComputerMoves = orderedComputerMoves;
        this.orderedComputerRotations = orderedComputerRotations;
    }

    public int makeMove(boolean isBlack) {
        int movement_index = -1;
        for (ComputerMoves move: orderedComputerMoves){
            movement_index = move.getMovement(board, isBlack);
            if (movement_index != -1){
                return movement_index;
            }
        }
        return -1;
    }


    public int makeRotation(boolean isBlack)
    {
        int rotation_key = -1;
        for (ComputerRotations rotate: orderedComputerRotations){
            rotation_key = rotate.getRotation(board, isBlack);
            if (rotation_key != -1){
                return rotation_key;
            }
        }
        return -1;
    }
}
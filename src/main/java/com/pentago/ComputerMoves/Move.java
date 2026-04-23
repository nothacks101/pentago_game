package com.pentago.ComputerMoves;

import com.pentago.ComputerRotations.Rotation;

public class Move {
    public int position;
    public Rotation rotation;

    public Move(int position, int quadrant, boolean clockwise){
        this.position = position;
        this.rotation = new Rotation(quadrant, clockwise);
    }
    public Move(int position, Rotation rotation){
        this.position = position;
        this.rotation = rotation;
    }
}

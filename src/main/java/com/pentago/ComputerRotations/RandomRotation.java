package com.pentago.ComputerRotations;

import java.util.Random;

import com.pentago.PentagoBoard;

public class RandomRotation implements ComputerRotations {
    public int getRotation(PentagoBoard board, boolean isBlack){
        Random random = new Random();
        int quadrant = random.nextInt(4) + 1; // Random quadrant (1 to 4)
        int rotation = random.nextInt(2) + 1; // Random rotation (1 or 2)
        return (quadrant - 1) * 2 + rotation;
    }
}

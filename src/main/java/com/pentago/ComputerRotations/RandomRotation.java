package com.pentago.ComputerRotations;

import java.util.Random;

import com.pentago.PentagoBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomRotation implements ComputerRotations {
    private static final Logger logger = LoggerFactory.getLogger(RandomRotation.class);
    public int getRotation(PentagoBoard board, boolean isBlack){
        logger.debug("in RandomRotation");
        Random random = new Random();
        int quadrant = random.nextInt(4) + 1; // Random quadrant (1 to 4)
        int rotation = random.nextInt(2) + 1; // Random rotation (1 or 2)
        return (quadrant - 1) * 2 + rotation;
    }
}

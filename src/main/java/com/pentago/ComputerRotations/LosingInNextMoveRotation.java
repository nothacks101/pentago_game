package com.pentago.ComputerRotations;

import com.pentago.PentagoBoard;
import com.pentago.ComputerMoves.ImmediateThreatMove;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LosingInNextMoveRotation implements ComputerRotations {
    private static final Logger logger = LoggerFactory.getLogger(ThreatRotation.class);
    private ImmediateThreatMove immediateThreatMove = new ImmediateThreatMove();

    public int getRotation(PentagoBoard board, boolean isBlack){
        logger.debug("in LosingInNextMoveRotation");
        for (int quadrant = 1; quadrant <= 4; quadrant++) {
            for (int rotation = 1; rotation <= 2; rotation++) {
                PentagoBoard tempBoard = board.copyBoard();
                tempBoard.updateRotaion(quadrant, rotation);
                if (this.immediateThreatMove.getMovement(tempBoard, isBlack) != -1)
                {
                    if(rotation == 1)
                    {
                        return (quadrant - 1) * 2 + 2;
                    }
                    return (quadrant - 1) * 2 + 1;
                }
            }
        }
        return -1;
    }   
}

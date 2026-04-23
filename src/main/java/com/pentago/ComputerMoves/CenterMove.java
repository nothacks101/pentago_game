package com.pentago.ComputerMoves;

import com.pentago.ComputerRotations.Rotation;
import com.pentago.ComputerRotations.RotationEvaluator;
import com.pentago.PentagoBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CenterMove implements ComputerMoves {
    private static final Logger logger = LoggerFactory.getLogger(CenterMove.class);
    private RotationEvaluator rotationEvaluator = new RotationEvaluator();
    public Move getMovement(PentagoBoard board, boolean isBlack){
        logger.debug("in CenterMove");
        int[] centers = {7, 10, 25, 28};
        for (int center : centers) {
            if (board.checkLegal(center)) {
                Rotation rotation = this.rotationEvaluator.getRotation(board, isBlack, center);
                return new Move(center, rotation);
            }
        }
        return null;
    }
    
}

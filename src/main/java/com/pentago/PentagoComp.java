package com.pentago;

import com.pentago.ComputerMoves.ComputerMoves;
import com.pentago.ComputerMoves.Move;
import com.pentago.ComputerRotations.ComputerRotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PentagoComp
{
    private static final Logger logger = LoggerFactory.getLogger(PentagoComp.class);
    private ComputerMoves[] orderedComputerMoves;

    public PentagoComp(ComputerMoves[] orderedComputerMoves)
    {
        this.orderedComputerMoves = orderedComputerMoves;
    }

    public Move makeMove(PentagoBoard board, boolean isPlayerBlack) {
        Move computerMovement = null;
        logger.info("in make move");
        logger.debug("board is : \n{}", board.getBoardInStr());
        logger.debug("is player black : {}", isPlayerBlack);
        for (ComputerMoves move: orderedComputerMoves){
            logger.debug("class {} in loop", move.getClass().getSimpleName());
            computerMovement = move.getMovement(board, isPlayerBlack);
            if (computerMovement != null){
                logger.info("movement index: {}, quar: {}, is clockwise: {}",
                        computerMovement.position, computerMovement.rotation.quadrant,
                        computerMovement.rotation.clockwise);
                return computerMovement;
            }
        }
        logger.info("no move found in make move");
        return null;
    }
}
package com.pentago;

import com.pentago.ComputerMoves.ComputerMoves;
import com.pentago.ComputerRotations.ComputerRotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PentagoComp
{
    private static final Logger logger = LoggerFactory.getLogger(PentagoComp.class);
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
        logger.info("in make move");
        logger.debug("board is : \n{}", this.board.getBoardInStr());
        logger.debug("isblack is : {}", isBlack);
        for (ComputerMoves move: orderedComputerMoves){
            logger.debug("class {} in loop", move.getClass().getSimpleName());
            movement_index = move.getMovement(board, isBlack);
            if (movement_index != -1){
                logger.info("movement index is : {}", movement_index);
                return movement_index;
            }
        }
        logger.info("no move found in make move");
        return -1;
    }


    public int makeRotation(boolean isBlack)
    {
        logger.info("in make rotation");
        logger.debug("board is : \n{}", this.board.getBoardInStr());
        int rotation_key = -1;
        for (ComputerRotations rotate: orderedComputerRotations){
            rotation_key = rotate.getRotation(board, isBlack);
            if (rotation_key != -1){
                logger.info("rotation key is : {}", rotation_key);
                return rotation_key;
            }
        }
        logger.info("no rotation found in make rotation");
        return -1;
    }
}
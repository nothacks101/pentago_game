package com.pentago.ComputerRotations;

import com.pentago.PentagoBoard;
import com.pentago.ComputerScripts.BestRotationForPattern;
import com.pentago.RandomScripts.ShuffleArr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeneficialDiagnaRotation implements ComputerRotations {
    private static final Logger logger = LoggerFactory.getLogger(BeneficialDiagnaRotation.class);
    private BestRotationForPattern bestRotationForPattern = new BestRotationForPattern();
    private ShuffleArr shuffleArr = new ShuffleArr();
    
    public int getRotation(PentagoBoard board, boolean isBlack){
        logger.debug("in BeneficialDiagnaRotation");
        long cboard = isBlack ? board.getWhiteBoard() : board.getBlackBoard();
        long diag1 = 0b100000010000001000000100000010L; //0,4
        long diag2 = 0b100000010000001000000100000010000001L; //0,5
        long diag3 = 0b10000001000000100000010000001000000L;//1,5
        long diag4 = 0b100001000010000100001000001000000L; //0,0
        long diag5 = 0b10000100001000010000100000000000L; //1,0
        long diag6 = 0b1000010000100001000010000L;// 0,1
        long[] masks = { diag1, diag2, diag3, diag4, diag5, diag6};
        long diagonal;
        int[] indexes = new int[masks.length];
        for (int i = 0; i < masks.length; i++) {
            indexes[i] = i;
        }
        int[] shuffled_indexes = this.shuffleArr.shuffle(indexes);
        for (int index : shuffled_indexes)
        {
            diagonal = masks[index];
            if ((cboard & diagonal) > 0 && (board.getOccupiedBoard() & diagonal) != diagonal)
            {
                int rotation = this.bestRotationForPattern.findBestRotation(board, isBlack, diagonal);
                if (rotation != -1) {
                    return rotation;
                }
            }
        }
        return -1;
    }
}

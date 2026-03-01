package com.pentago.ComputerRotations;

import java.util.Random;

import com.pentago.PentagoBoard;
import com.pentago.ComputerMoves.RotateWinMove;
import com.pentago.ComputerScripts.BestRotationForPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeneficialColumnRotation implements ComputerRotations {
    private static final Logger logger = LoggerFactory.getLogger(BeneficialColumnRotation.class);
    private BestRotationForPattern bestRotationForPattern = new BestRotationForPattern();
    
    public int getRotation(PentagoBoard board, boolean isBlack){
        long cboard = isBlack ? board.getWhiteBoard() : board.getBlackBoard(); 
        long colMask = 0b1000001000001000001000001000001L;
        int[] beneficial_cols = new int[6];
        int fitted_col_count = 0;
        int chosen_col_index;
        Random rand = new Random();
        for (int col = 0; col <= 5; col++) {
            long colPattern = colMask << col;
            if ((cboard & colPattern) > 0 && (board.getOccupiedBoard() & colPattern) != colPattern)
            {
                int rotation = this.bestRotationForPattern.findBestRotation(board, isBlack, colPattern);
                if (rotation != -1) {
                    beneficial_cols[fitted_col_count]= rotation;
                    fitted_col_count++;
                }
            }
        }
        if(fitted_col_count == 0)
        {
            return -1;
        }
        chosen_col_index = rand.nextInt(fitted_col_count);
        return beneficial_cols[chosen_col_index];
    }
}

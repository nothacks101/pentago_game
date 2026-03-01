package com.pentago.ComputerRotations;

import java.util.Random;

import com.pentago.PentagoBoard;
import com.pentago.ComputerScripts.BestRotationForPattern;

public class BeneficialRowRotation implements ComputerRotations {
    private BestRotationForPattern bestRotationForPattern = new BestRotationForPattern();
    
    public int getRotation(PentagoBoard board, boolean isBlack){
        long cboard = isBlack ? board.getWhiteBoard() : board.getBlackBoard(); 
        long rowMask = 0b111111;
        int[] beneficial_rows = new int[6];
        int fitted_row_count = 0;
        int chosen_row_index;
        Random rand = new Random();
        for (int row = 0; row <= 30; row+=6) {
            long rowPattern = rowMask << row;
            if ((cboard & rowPattern) > 0 && (board.getOccupiedBoard() & rowPattern) != rowPattern)
            {
                int rotation = this.bestRotationForPattern.findBestRotation(board, isBlack, rowPattern);
                if (rotation != -1) {
                    beneficial_rows[fitted_row_count]= rotation;
                    fitted_row_count++;
                }
            }
        }
        if(fitted_row_count == 0)
        {
            return -1;
        }
        chosen_row_index = rand.nextInt(fitted_row_count);
        return beneficial_rows[chosen_row_index];
    }
}

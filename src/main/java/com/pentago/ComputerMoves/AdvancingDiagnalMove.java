package com.pentago.ComputerMoves;

import java.util.Random;

import com.pentago.PentagoBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdvancingDiagnalMove implements ComputerMoves{
    private static final Logger logger = LoggerFactory.getLogger(AdvancingDiagnalMove.class);
    public int getMovement(PentagoBoard board, boolean isBlack){
        logger.debug("in AdvancingDiagnalMove");
        long cboard = isBlack ? board.getWhiteBoard() : board.getBlackBoard(); 
        int[] diags = new int[6];
        int fitted_diags_count = 0;
        int chosen_diag_index, index;
        Random random = new Random();
        long diag1 = 0b100000010000001000000100000010L; //0,4
        long diag2 = 0b100000010000001000000100000010000001L; //0,5
        long diag3 = 0b10000001000000100000010000001000000L;//1,5
        long diag4 = 0b100001000010000100001000001000000L; //0,0
        long diag5 = 0b10000100001000010000100000000000L; //1,0
        long diag6 = 0b1000010000100001000010000L;// 0,1
        long[] masks = { diag1, diag2, diag3, diag4, diag5, diag6};
        for (int i =0; i<=5; i++)
        {
            long diagState = cboard & masks[i];
            long fullState = board.getOccupiedBoard() & masks[i];
            if (diagState > 0 && fullState < masks[i])
            {
                diags[fitted_diags_count] = i;
                fitted_diags_count++;
            }
        }
        if(fitted_diags_count == 0)
        {
            return -1;
        }
        chosen_diag_index = random.nextInt(fitted_diags_count);
        index = diags[chosen_diag_index];
        int len = 0;
        int[] positions = new int[6];
        if (index == 0)
        {
            int[] pos = {1,8,15,22,29};
            positions = pos;
            len = 5;
        }
        else if (index == 1)
        {
            int[] pos = {0, 7, 14, 21, 28, 35};
            positions = pos;
            len = 6;
        }
        else if (index == 2)
        {
            int[] pos = {6, 13, 20, 27, 34};
            positions = pos;
            len =5;
        }
        else if (index == 3)
        {
            int[] pos = {5,10,15,20,25,30};
            positions = pos;
            len =6;
        }
        else if (index == 4)
        {
            int[] pos = {4,9,14,19,24};
            positions = pos;
            len =5;
        }
        else if (index == 5)
        {
            int[] pos = {11, 16, 21, 26,31};
            positions = pos;
            len =5;
        }
        int column_index_in_row = 0;
        int[] empty_cells_indexes = new int[6];
        for(int diag_cell_index = 0; diag_cell_index < len; diag_cell_index++)
        {
            if(board.checkLegal(positions[diag_cell_index]))
            {
                empty_cells_indexes[column_index_in_row] = positions[diag_cell_index];
                column_index_in_row++;
            }
        }
        return empty_cells_indexes[random.nextInt(column_index_in_row)];
    }
}

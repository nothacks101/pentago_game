package com.pentago.ComputerMoves;

import java.util.Random;

import com.pentago.PentagoBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdvancingRowMove implements ComputerMoves {
    private static final Logger logger = LoggerFactory.getLogger(AdvancingRowMove.class);
    public int getMovement(PentagoBoard board, boolean isBlack){
        logger.debug("in AdvancingRowMove");
        long cboard = isBlack ? board.getWhiteBoard() : board.getBlackBoard(); 
        int[] rows = new int[6];
        int fitted_row_count = 0;
        int chosen_row_index, index;
        Random random = new Random();
        for (int row = 0; row <=30; row+=6) {
            long rowMask = 0b111111L << row;
            long rowState = cboard & rowMask;
            long fullState = board.getOccupiedBoard() & rowMask;
            if (rowState > 0 && fullState < rowMask)
            {
                rows[fitted_row_count] = row;
                fitted_row_count++;
            }
        }
        if(fitted_row_count == 0)
        {
            return -1;
        }
        chosen_row_index = random.nextInt(fitted_row_count);
        index = rows[chosen_row_index];
        int column_index_in_row = 0;
        int[] empty_cells_indexes = new int[6];
        for(int col = index; col <= index+5; col++)
        {
            if(board.checkLegal(col))
            {
                empty_cells_indexes[column_index_in_row] = col;
                column_index_in_row++;
            }
        }
        if (column_index_in_row == 0){
            return -1;
        }
        int chosen_cell_index = random.nextInt(column_index_in_row);
        return empty_cells_indexes[chosen_cell_index];
    }
}

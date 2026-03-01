package com.pentago.ComputerMoves;

import java.util.Random;

import com.pentago.PentagoBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdvancingColumnMove implements ComputerMoves  {
    private static final Logger logger = LoggerFactory.getLogger(AdvancingColumnMove.class);
    public int getMovement(PentagoBoard board, boolean isBlack){
        logger.debug("in AdvancingColumnMove");
        long cboard = isBlack ? board.getWhiteBoard() : board.getBlackBoard(); 
        int[] cols = new int[6];
        int fitted_column_count = 0;
        int chosen_column_index, index;
        Random random = new Random();
        for (int col = 0; col <= 5; col++) {
            long colMask = 0b1000001000001000001000001000001L << col;
            long colState = cboard & colMask;
            long fullState = board.getOccupiedBoard() & colMask;
            if (colState > 0 && fullState < colMask) {
                cols[fitted_column_count] = col;
                fitted_column_count++;
            }
        }
        if (fitted_column_count == 0) {
            return -1;
        }
        chosen_column_index = random.nextInt(fitted_column_count);
        index = cols[chosen_column_index];
        int row_index_in_column = 0;
        int[] empty_cells_indexes = new int[6];
        for (int row = index; row <= index+30; row+=6) {
            if (board.checkLegal(row)) {
                empty_cells_indexes[row_index_in_column] = row;
                row_index_in_column++;
            }
        }
        if (row_index_in_column == 0){
            return -1;
        }
        int chosen_cell_index = random.nextInt(row_index_in_column);
        return empty_cells_indexes[chosen_cell_index];
    }
    
}

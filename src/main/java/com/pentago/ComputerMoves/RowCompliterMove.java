package com.pentago.ComputerMoves;

import java.util.Random;

import com.pentago.PentagoBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RowCompliterMove implements ComputerMoves {
    private static final Logger logger = LoggerFactory.getLogger(RowCompliterMove.class);
    public int getMovement(PentagoBoard board, boolean isBlack){
        logger.debug("in RowCompliterMove");
        long cboard = isBlack ? board.getBlackBoard() : board.getWhiteBoard();
        long row1 = 0b110L;
        long row2 = 0b101L;
        long row3 = 0b011L;
        int[] loc = new int[36];
        int count = -1;
        int chosen;
        Random random = new Random();
        for(int i = 0; i<= 33 ; i+=3)
        {
            if((row1 & cboard) == row1 && (board.checkLegal(i))){
                count++;
                loc[count] = i;
            }
            if((row2 & cboard) == row2&& (board.checkLegal(i + 1)))
            {
                count++;
                loc[count] = i+1;
            }
            if((row3 & cboard) == row3 && (board.checkLegal(i + 2)))
            {
                count++;
                loc[count] = i+2;
            }
            row1 = row1 << 3;
            row2 = row2 << 3;
            row3 = row3 << 3;
        }
        if (count == -1)
        {
            return -1;
        }
        chosen = random.nextInt(count + 1);

        return loc[chosen];
    }
    
}

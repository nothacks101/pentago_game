package com.pentago.ComputerMoves;

import java.util.Random;

import com.pentago.PentagoBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColumnCompliterMove implements ComputerMoves {
    private static final Logger logger = LoggerFactory.getLogger(ColumnCompliterMove.class);
    public int getMovement(PentagoBoard board, boolean isBlack){
        logger.debug("in ColumnCompliterMove");
        long cboard = isBlack ? board.getBlackBoard() : board.getWhiteBoard();
        long col1 = 0b1000001000000L;
        long col2 = 0b1000000000001L;
        long col3 = 0b1000001L;
        long col4 = 0b1000001000000000000000000000000L;
        long col5 = 0b1000000000001000000000000000000L;
        long col6 = 0b1000001000000000000000000L;
        int[] loc = new int[36];
        int count = -1;
        int chosen;
        Random random = new Random();
        for(int i = 0; i<=5; i++)
        {
            if((col1 & cboard) == col1 && (board.checkLegal(i)))
            {
                count++;
                loc[count] =i;
            }
            if((col2 & cboard) == col2&& (board.checkLegal(i+6)))
            {
                count++;
                loc[count] =i+6;
            }
            if((col3 & cboard) == col3&& (board.checkLegal(i+12)))
            {
                count++;
                loc[count] =i+12;
            }
            if((col4 & cboard) == col4&& (board.checkLegal(i+18)))
            {
                count++;
                loc[count] =i+18;
            }
            if((col5 & cboard) == col5&& (board.checkLegal(i+24)))
            {
                count++;
                loc[count] =i+24;
            }
            if((col6 & cboard) == col6&& (board.checkLegal(i+12)))
            {
                count++;
                loc[count] =i+12;
            }
            col1= col1<<1;
            col2 = col2<<1;
            col3 = col3<<1;
            col4 = col4<<1;
            col5 = col5<<1;
            col6 = col6<<1;
        }
        if (count == -1)
        {
            return -1;
        }
        chosen = random.nextInt(count + 1);
        return loc[chosen];
    }
}

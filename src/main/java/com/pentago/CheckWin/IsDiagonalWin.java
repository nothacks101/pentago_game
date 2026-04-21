package com.pentago.CheckWin;

import com.pentago.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IsDiagonalWin implements isWinningBoard {
    private static final Logger logger = LoggerFactory.getLogger(IsDiagonalWin.class);
    public Byte checkWin(long board)
    {
        long[] diagonals_mask = Const.FIVE_IN_DIAGONAL;
        for (long diagonal : diagonals_mask)
            if ((board & diagonal) == diagonal) {
                logger.debug("mask is: {} ", diagonal);
                return 1;
            }
        return 0;
    }
    
}

package com.pentago.CheckWin;

import com.pentago.ComputerMoves.RotateLoseMove;
import com.pentago.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IsColumnWin implements isWinningBoard {
    private static final Logger logger = LoggerFactory.getLogger(IsColumnWin.class);

    public Byte checkWin(long board) {
        long[] columns_mask = Const.FIVE_IN_COLUMN;
        for (long column : columns_mask)
            if ((board & column) == column) {
                logger.debug("mask is: {} ", column);
                return 1;
            }
        return 0;
    }
}

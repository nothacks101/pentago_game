package com.pentago.CheckWin;

import com.pentago.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class isRowWin implements isWinningBoard {
    private static final Logger logger = LoggerFactory.getLogger(isRowWin.class);

    public Byte checkWin(long board)
    {
        long[] rows_mask = Const.FIVE_IN_ROW;
        for (long row : rows_mask)
            if ((board & row) == row) {
                return 1;
            }
        return 0;
    }
}

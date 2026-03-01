package com.pentago.CheckWin;

public class IsColumnWin implements isWinningBoard {
    public Byte checkWin(long board)
    {
        long colMask;
        long colMask2;
        for (int col = 0; col <= 5; col++)
        {
            colMask = 0b1000001000001000001000001L << col;
            colMask2 = 0b1000001000001000001000001000000L  << col;
            if ((board & colMask) == colMask || (board & colMask2) == colMask2)
            {
                return 1;
            }

        }
        return 0;
    }
}

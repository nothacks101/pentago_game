package src.main.java.com.pentago.CheckWin;

public class isRowWin implements isWinningBoard {
    public Byte checkWin(long board)
    {
        long rowMask;
        long rowMask2;
        for (int col = 0; col <= 30; col+=6)
        {
            rowMask = 0b111110L << col;
            rowMask2 = 0b11111L  << col;
            if ((board & rowMask) == rowMask || (board & rowMask2) == rowMask2)
            {
                return 1;
            }

        }
        return 0;
    }
}

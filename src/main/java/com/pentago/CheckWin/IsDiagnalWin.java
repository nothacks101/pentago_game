package com.pentago.CheckWin;

public class IsDiagnalWin implements isWinningBoard {
    public Byte checkWin(long board)
    {
        long diag1 = 0b100000010000001000000100000010000000L; //1,4
        long diag2 = 0b10000001000000100000010000001L; //0,5
        long diag3 = 0b100000010000001000000100000010L; //0,4
        long diag4 = 0b10000001000000100000010000001000000L;//1,5
        long diag5 = 0b10000100001000010000100000L; //0,0
        long diag6 = 0b1000010000100001000010000000000L; //1,1
        long diag7 = 0b10000100001000010000100000000000L; //1,0
        long diag8 = 0b1000010000100001000010000L;// 0,1
        long[] masks = { diag1, diag2, diag3, diag4, diag5, diag6,diag7,diag8 };
        for (long mask : masks) {
            if ((board & mask) == mask)
            {
                return 1;
            }
        }
        return 0;
    }
    
}

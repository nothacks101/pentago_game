package com.pentago.CheckWin;

import com.pentago.Enums.BoardStatus;
import com.pentago.PentagoBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WinCheckLogic {
    private static final Logger logger = LoggerFactory.getLogger(ColumnWin.class);
    public BoardStatus checkWin(PentagoBoard board, long[] masks){
        boolean isWhiteWinning = false;
        boolean isBlackWinning = false;

        for (long column : masks) {
            if ((board.getWhiteBoard() & column) == column) {
                logger.debug("mask is: {} ", column);
                isWhiteWinning = true;
            }
            if ((board.getBlackBoard() & column) == column) {
                logger.debug("mask is: {} ", column);
                isBlackWinning = true;
            }
        }
        if (isWhiteWinning && isBlackWinning){
            return BoardStatus.TIE;
        } else if (isWhiteWinning) {
            return BoardStatus.WHITE_WIN;
        } else if (isBlackWinning) {
            return BoardStatus.BLACK_WIN;
        } else{
            return BoardStatus.RUNNING;
        }
    }
}

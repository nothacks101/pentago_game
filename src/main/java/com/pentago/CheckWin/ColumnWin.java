package com.pentago.CheckWin;

import com.pentago.Const;
import com.pentago.Enums.BoardStatus;
import com.pentago.PentagoBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColumnWin extends WinCheckLogic implements WinCheck {
    private static final Logger logger = LoggerFactory.getLogger(ColumnWin.class);

    public BoardStatus checkWin(PentagoBoard board) {
        return super.checkWin(board, Const.FIVE_IN_COLUMN);
    }
}

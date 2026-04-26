package com.pentago.CheckWin;

import com.pentago.Enums.BoardStatus;
import com.pentago.PentagoBoard;

public interface WinCheck {
   public BoardStatus checkWin(PentagoBoard board);
}

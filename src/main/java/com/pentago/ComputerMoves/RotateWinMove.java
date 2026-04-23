package com.pentago.ComputerMoves;

import com.pentago.Enums.BoardStatus;
import com.pentago.PentagoBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RotateWinMove implements ComputerMoves {
    private static final Logger logger = LoggerFactory.getLogger(RotateWinMove.class);

    public Move getMovement(PentagoBoard board, boolean isPlayerBlack){
        BoardStatus compWin = isPlayerBlack ? BoardStatus.WHITE_WIN : BoardStatus.BLACK_WIN;

        // עבור כל תא פנוי
        for (int pos = 0; pos < 36; pos++) {
            if (!board.checkLegal(pos)) continue;

            // עבור כל סיבוב אפשרי
            for (int quadrant = 1; quadrant <= 4; quadrant++) {
                for (int clockwise = 0; clockwise <= 1; clockwise++) {

                    PentagoBoard temp = board.copyBoard();

                    // 1. הנחה
                    temp.updateBoard(pos, isPlayerBlack);

                    // 2. סיבוב
                    temp.updateRotation(quadrant, clockwise == 1);

                    // בדיקת ניצחון
                    BoardStatus winCheck = temp.checkWin();
                    if (winCheck == compWin) {
                        logger.debug("found winning move: pos={}, quadrant={}, clockwise={}",
                                pos, quadrant, clockwise);
                        return new Move(pos, quadrant, clockwise == 1);
                    }
                }
            }
        }

        return null;
    }
}
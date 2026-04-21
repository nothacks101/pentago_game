package com.pentago.LineEvaluator;

import com.pentago.PentagoBoard;

public class MasksEvaluator {
    private final LineValue lineValue = new LineValue();
    
    public int evaluate(PentagoBoard board, boolean isBlack, long[] masks){
        long cboard = isBlack ? board.getWhiteBoard() : board.getBlackBoard(); 
        long player_board = isBlack ? board.getBlackBoard() : board.getWhiteBoard(); 
        int score = 0;
        for (long mask : masks) {
            int computer_count = Long.bitCount(cboard & mask);
            int player_count = Long.bitCount(player_board & mask);

            if (player_count == 0) {   
                score += lineValue.getValue(computer_count);
            }
            if (computer_count == 0){
                score -= lineValue.getValue(player_count);
            }
        }
        return score;
    }
}


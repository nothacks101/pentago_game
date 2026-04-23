package com.pentago.LineEvaluator;

import com.pentago.PentagoBoard;

public class MasksEvaluator {
    private final LineValue lineValue = new LineValue();
    
    public int evaluate(PentagoBoard board, boolean isPlayerBlack, long[] masks){
        long cboard = isPlayerBlack ? board.getWhiteBoard() : board.getBlackBoard();
        long player_board = isPlayerBlack ? board.getBlackBoard() : board.getWhiteBoard();
        int score = 0;
        for (long mask : masks) {
            int computer_count = Long.bitCount(cboard & mask);
            int player_count = Long.bitCount(player_board & mask);
            if (computer_count == 5) return Integer.MAX_VALUE;
            if (player_count == 5) return Integer.MIN_VALUE;

            if (player_count == 0) {   
                score += lineValue.getValue(computer_count);
            }
            else if (computer_count == 0){
                score -= lineValue.getValue(player_count);
            }
            if (computer_count == 3 && player_count == 0) {
                score += 150; // בונוס לאיום פתוח
            }
            if (player_count == 3 && computer_count == 0) {
                score -= 150;
            }
        }
        return score;
    }
}


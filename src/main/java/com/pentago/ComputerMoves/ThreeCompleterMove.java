package com.pentago.ComputerMoves;

import com.pentago.PentagoBoard;
import com.pentago.RandomScripts.ShuffleArr;

public class ThreeCompleterMove implements ComputerMoves{
    private ShuffleArr shuffleArr = new ShuffleArr();
    private ComputerMoves[] threeCompleter;

    public ThreeCompleterMove(ComputerMoves[] threeCompliter){
        this.threeCompleter = threeCompliter;
    }

    public Move getMovement(PentagoBoard board, boolean isBlack){
        int[] indexes = new int[this.threeCompleter.length];
        for (int i = 0; i < this.threeCompleter.length; i++) {
            indexes[i] = i;
        }
        int[] shuffled_indexes = this.shuffleArr.shuffle(indexes);

        for (int index : shuffled_indexes) {
            Move move = this.threeCompleter[index].getMovement(board, isBlack);
            if (move != null) {
                return move;
            }
        }

        return null;
    }
}

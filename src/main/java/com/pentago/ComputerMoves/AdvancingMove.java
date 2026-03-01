package com.pentago.ComputerMoves;

import com.pentago.PentagoBoard;
import com.pentago.RandomScripts.ShuffleArr;

public class AdvancingMove implements ComputerMoves {
    private ShuffleArr shuffleArr = new ShuffleArr();
    private ComputerMoves[] advancingMoves;

    public AdvancingMove(ComputerMoves[] advancingMoves){
        this.advancingMoves = advancingMoves;
    }

    public int getMovement(PentagoBoard board, boolean isBlack){
        int[] indexes = new int[this.advancingMoves.length];
        for (int i = 0; i < this.advancingMoves.length; i++) {
            indexes[i] = i;
        }
        int[] shuffled_indexes = this.shuffleArr.shuffle(indexes);

        for (int index : shuffled_indexes) {
            int move = this.advancingMoves[index].getMovement(board, isBlack);
            if (move != -1) {
                return index;
            }
        }

        return -1;
    }
}

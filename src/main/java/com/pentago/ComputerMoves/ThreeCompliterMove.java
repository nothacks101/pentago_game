package com.pentago.ComputerMoves;

import com.pentago.PentagoBoard;
import com.pentago.RandomScripts.ShuffleArr;

public class ThreeCompliterMove implements ComputerMoves{
    private ShuffleArr shuffleArr = new ShuffleArr();
    private ComputerMoves[] threeCompliter;

    public ThreeCompliterMove(ComputerMoves[] threeCompliter){
        this.threeCompliter = threeCompliter;
    }

    public int getMovement(PentagoBoard board, boolean isBlack){
        int[] indexes = new int[this.threeCompliter.length];
        for (int i = 0; i < this.threeCompliter.length; i++) {
            indexes[i] = i;
        }
        int[] shuffled_indexes = this.shuffleArr.shuffle(indexes);

        for (int index : shuffled_indexes) {
            int move = this.threeCompliter[index].getMovement(board, isBlack);
            if (move != -1) {
                return move;
            }
        }

        return -1;
    }
}

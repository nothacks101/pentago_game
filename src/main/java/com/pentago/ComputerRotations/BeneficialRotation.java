package com.pentago.ComputerRotations;

import com.pentago.PentagoBoard;
import com.pentago.RandomScripts.ShuffleArr;

public class BeneficialRotation implements ComputerRotations {
    private ShuffleArr shuffleArr = new ShuffleArr();
    private ComputerRotations[] beneficialRotation;

        public BeneficialRotation(ComputerRotations[] beneficialRotation){
        this.beneficialRotation = beneficialRotation;
    }

    public int getRotation(PentagoBoard board, boolean isBlack){
        int[] indexes = new int[this.beneficialRotation.length];
        for (int i = 0; i < this.beneficialRotation.length; i++) {
            indexes[i] = i;
        }
        int[] shuffled_indexes = this.shuffleArr.shuffle(indexes);

        for (int index : shuffled_indexes) {
            int move = this.beneficialRotation[index].getRotation(board, isBlack);
            if (move != -1) {
                return index;
            }
        }

        return -1;
    }
}

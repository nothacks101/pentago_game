package com.pentago.RandomScripts;

import java.util.Random;

public class ShuffleArr {
    public int[] shuffle(int[] arr) {
        int index, temp;
        Random random = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }
}

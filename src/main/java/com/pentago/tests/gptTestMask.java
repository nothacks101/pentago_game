package com.pentago.tests;

import com.pentago.Const;

public class gptTestMask {
    public static void main(String[] args) {
        long mask = Const.FIVE_IN_COLUMN[0];

        long board = mask; // בדיוק אותו דבר

        System.out.println((board & mask) == mask); // חייב להיות true

        board = 0;
        System.out.println((board & mask) == mask); // חייב להיות false
    }
}

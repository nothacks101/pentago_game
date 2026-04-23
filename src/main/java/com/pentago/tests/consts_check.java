package com.pentago.tests;

import com.pentago.Const;
import com.pentago.PentagoBoard;



public class consts_check {
    public static void check_masks(long[] columns_mask) {
        for (long column : columns_mask) {
            PentagoBoard board = new PentagoBoard();
            board.setBlackBoard(column);
            System.out.println(board.getBoardInStr());
            System.out.println(Long.toBinaryString(column));
        }
    }
    public static void main(String[] args) {
        check_masks(Const.FIVE_IN_COLUMN);
        System.out.println("-----------------------");
        check_masks(Const.FIVE_IN_ROW);
        System.out.println("-----------------------");
        check_masks(Const.FIVE_IN_DIAGONAL);
        System.out.println("-----------------------");
    }
}
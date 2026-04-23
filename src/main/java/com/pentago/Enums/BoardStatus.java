package com.pentago.Enums;

public enum BoardStatus {
    RUNNING(0),
    BLACK_WIN(1),
    WHITE_WIN(2),
    TIE(3);

    private final int value;

    BoardStatus(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}

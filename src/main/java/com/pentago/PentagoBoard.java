package com.pentago;
import com.pentago.CheckWin.IsColumnWin;
import com.pentago.CheckWin.IsDiagnalWin;
import com.pentago.CheckWin.isRowWin;
import com.pentago.CheckWin.isWinningBoard;


public class PentagoBoard {
    private long whiteBoard;
    private long blackBoard;
    private long occupiedBoard;
    private isWinningBoard[] isWinningBoard;

    public PentagoBoard(){
        isWinningBoard column = new IsColumnWin();
        isWinningBoard row = new isRowWin();
        isWinningBoard diagnal = new IsDiagnalWin();
        isWinningBoard[] arr = {column, row, diagnal};
        this.isWinningBoard = arr;
    }


    public long getWhiteBoard() {
        return whiteBoard;
    }
    public void setWhiteBoard(long whiteBoard) {
        this.whiteBoard = whiteBoard;
    }

    public long getBlackBoard() {
        return blackBoard;
    }

    public void setBlackBoard(long blackBoard) {
        this.blackBoard = blackBoard;
    }

    public long getOccupiedBoard() {
        return occupiedBoard;
    }

    public void setOccupiedBoard(long occupiedBoard) {
        this.occupiedBoard = occupiedBoard;
    }

    public boolean isFullBoard(long board)
    {
        long checkFull =  (1L << 36) - 1;
        if ((board & checkFull ) == checkFull)
        {
            return true;
        }
        return false;
    }
    public boolean checkLegal(int index)
    {
        if(((occupiedBoard >> index) & 1) == 1)
        {
            return false;
        }
        return true;
    }

    public Byte checkWin(long blackBoard, long whiteBoard)
    {
        if(isFullBoard(occupiedBoard))
        {
            return 3;
        }
        byte stat = 0;
        for (isWinningBoard win_check: isWinningBoard){
            if (win_check.checkWin(blackBoard) == 1)
            {
                stat = 1;
            }
        }
        for (isWinningBoard win_check: isWinningBoard){
            if (win_check.checkWin(whiteBoard) == 1)
            {
                if(stat == 1)
                {
                    return 3;
                }
                return 2;
            }
        }
        if(stat == 1)
        {
            return 1;
        }
        return 0;
    }

    public void updateBoard(int index, boolean isBlack) {
        long bitloc = 1L << index;
        occupiedBoard |= bitloc;

        if (isBlack) {
            blackBoard |= bitloc;
        } else {
            whiteBoard |= bitloc;
        }
    }
    
    private long setBit(long board, int index, byte value) {
        return (board & ~(1L << index)) | ((long)value << index);
    }
    public long rotateRight(Long board,int[] inindixes)
    {
        Byte i1 = (byte) ((board >> inindixes[0]) & 1L);
        Byte i2 = (byte) ((board >> inindixes[1]) & 1L);
        Byte i3 = (byte) ((board >> inindixes[6]) & 1L);
        Byte i4 = (byte) ((board  >> inindixes[7]) & 1L);
        board = setBit(board,inindixes[0],(byte)((board >> inindixes[2]) & 1L));
        board = setBit(board,inindixes[1],(byte)((board >> inindixes[4]) & 1L));
        board = setBit(board,inindixes[6],(byte)((board >> inindixes[3]) & 1L));
        board = setBit(board,inindixes[7],(byte)((board >> inindixes[5]) & 1L));
        board = setBit(board,inindixes[2],i4);
        board = setBit(board,inindixes[3],i2);
        board = setBit(board,inindixes[5],i1);
        board = setBit(board,inindixes[4],i3);
        return board;
    }
    public void printConsoleBoard(){
        for (int index = 0; index < 6; index ++){
            for (int jndex = 0; jndex < 6; jndex ++){
                if(((getBlackBoard() >> (jndex + index * 6)) & 1) == 1){
                    System.out.print('B');  
                }
                else if (((getWhiteBoard() >> (jndex + index * 6)) & 1) == 1) {
                    System.out.print('W');  
                }
                else {
                    System.out.print('-'); 
                }
            }
            System.out.println(); 
        }
    }
    public long rotateLeft(Long board,int[] inindixes)
    {
        Byte i1 = (byte) ((board >> inindixes[0]) & 1L);
        Byte i2 = (byte) ((board >> inindixes[1]) & 1L);
        Byte i3 = (byte) ((board >> inindixes[6]) & 1L);
        Byte i4 = (byte) ((board >> inindixes[7]) & 1L);
        board = setBit(board,inindixes[0],(byte)((board >> inindixes[5]) & 1L));
        board = setBit(board,inindixes[1],(byte)((board >> inindixes[3]) & 1L));
        board = setBit(board,inindixes[6],(byte)((board >> inindixes[4]) & 1L));
        board = setBit(board,inindixes[7],(byte)((board >> inindixes[2]) & 1L));
        board = setBit(board,inindixes[2],i1);
        board = setBit(board,inindixes[3],i3);
        board = setBit(board,inindixes[5],i4);
        board = setBit(board,inindixes[4],i2);
        return board;
    }
    public void updateRotaion(int key, int rotation)
    {
        int[] indixes = new int[8];
        switch (key) {
            case 1:
                indixes = new int[]{3, 4, 5, 9, 11, 15, 16, 17};
                break;
            case 2:
                indixes = new int[]{0, 1, 2, 6, 8, 12, 13, 14};
                break;
            case 3:
                indixes = new int[]{18, 19, 20, 24, 26, 30, 31, 32};
                break;
            default:
                indixes = new int[]{21, 22, 23, 27, 29, 33, 34, 35};
                break;
        }
        if (rotation == 1) {
            whiteBoard = rotateRight(whiteBoard, indixes);
            blackBoard=rotateRight(blackBoard, indixes);
        } else {
            whiteBoard = rotateLeft(whiteBoard, indixes);
            blackBoard = rotateLeft(blackBoard, indixes);
        }
        occupiedBoard = whiteBoard | blackBoard;
    }
    public void resetBoards()
    {
        whiteBoard=0;
        blackBoard=0;
        occupiedBoard=0;
    }
}
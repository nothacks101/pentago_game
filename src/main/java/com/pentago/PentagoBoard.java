package com.pentago;
import com.pentago.CheckWin.IsColumnWin;
import com.pentago.CheckWin.IsDiagonalWin;
import com.pentago.CheckWin.isRowWin;
import com.pentago.CheckWin.isWinningBoard;

import com.pentago.Enums.BoardStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PentagoBoard {
    private long whiteBoard;
    private long blackBoard;
    private long occupiedBoard;
    private isWinningBoard[] isWinningBoard;
    private static final Logger logger = LoggerFactory.getLogger(PentagoBoard.class);

    public PentagoBoard(){
        isWinningBoard column = new IsColumnWin();
        isWinningBoard row = new isRowWin();
        isWinningBoard diagonal = new IsDiagonalWin();
        isWinningBoard[] arr = {column, row, diagonal};
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
    public PentagoBoard copyBoard(){
        PentagoBoard tempBoard = new PentagoBoard();
        tempBoard.setBlackBoard(getBlackBoard());
        tempBoard.setWhiteBoard(getWhiteBoard());
        tempBoard.setOccupiedBoard(getOccupiedBoard());
        return tempBoard;
    }
    public BoardStatus checkWin()
    {
        if(isFullBoard(this.getOccupiedBoard()))
        {
            return BoardStatus.TIE;
        }
        BoardStatus stat = BoardStatus.RUNNING;
        for (isWinningBoard win_check: isWinningBoard){
            int win = win_check.checkWin(this.getBlackBoard());
            if (win != 0){
                logger.debug("in checkwin board is \n{}", this.getBoardInStr());
                logger.debug("black class {} result {}", win_check.getClass().getSimpleName(), win);
            }
            if (win == 1)
            {
                stat = BoardStatus.BLACK_WIN;
            }
        }
        for (isWinningBoard win_check: isWinningBoard){
            int win = win_check.checkWin(this.getWhiteBoard());
            if (win != 0){
                logger.debug("in checkwin board is \n{}", this.getBoardInStr());
                logger.debug("white class {} result {}", win_check.getClass().getSimpleName(), win);
            }
            
            if (win == 1)
            {
                if(stat.getValue() == BoardStatus.BLACK_WIN.getValue())
                {
                    return BoardStatus.TIE;
                }
                return BoardStatus.WHITE_WIN;
            }
        }
        return stat;
    }

    public void updateBoard(int index, boolean iWhiteTurn) {
        long bitloc = 1L << index;
        occupiedBoard |= bitloc;

        if (iWhiteTurn) {
            whiteBoard |= bitloc;
        } else {
            blackBoard |= bitloc;
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
    public String getBoardInStr() {
        StringBuilder str_board = new StringBuilder();

        for (int index = 0; index < 6; index++) {
            for (int jndex = 0; jndex < 6; jndex++) {
                if (((getBlackBoard() >> (jndex + index * 6)) & 1) == 1) {
                    str_board.append('B');  
                } else if (((getWhiteBoard() >> (jndex + index * 6)) & 1) == 1) {
                    str_board.append('W'); 
                } else {
                    str_board.append('-'); 
                }
            }
            str_board.append('\n');  
        }
        return str_board.toString(); 
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
    public void updateRotation(int small_board_index, boolean clockwise)
    {
        int[] indixes = new int[8];
        switch (small_board_index) {
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
        if (!clockwise) {
            whiteBoard = rotateRight(whiteBoard, indixes);
            blackBoard = rotateRight(blackBoard, indixes);
        } else {
            whiteBoard = rotateLeft(whiteBoard, indixes);
            blackBoard = rotateLeft(blackBoard, indixes);
        }
        occupiedBoard = whiteBoard | blackBoard;
    }
    public boolean isGameOver() {
        return this.checkWin() != BoardStatus.RUNNING;
    }

    public BoardStatus getWinner() {
        return this.checkWin();
    }
    public void resetBoards()
    {
        whiteBoard=0;
        blackBoard=0;
        occupiedBoard=0;
    }
}
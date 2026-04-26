package com.pentago;
import com.pentago.CheckWin.ColumnWin;
import com.pentago.CheckWin.DiagonalWin;
import com.pentago.CheckWin.RowWin;
import com.pentago.CheckWin.WinCheck;

import com.pentago.Enums.BoardStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PentagoBoard {
    private long whiteBoard;
    private long blackBoard;
    private long occupiedBoard;
    private WinCheck[] winningChecks;
    private static final Logger logger = LoggerFactory.getLogger(PentagoBoard.class);

    public PentagoBoard(){
        WinCheck column = new ColumnWin();
        WinCheck row = new RowWin();
        WinCheck diagonal = new DiagonalWin();
        this.winningChecks = new WinCheck[]{column, row, diagonal};
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
        return ((occupiedBoard >> index) & 1) != 1;
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
            logger.debug("board is full, its a tie");
            return BoardStatus.TIE;
        }
        BoardStatus result = BoardStatus.RUNNING;
        for (WinCheck winCheck: this.winningChecks){
            BoardStatus winResult = winCheck.checkWin(this);
            if (winResult == BoardStatus.TIE){
                logger.debug("tie result was reached from checker");
                return winResult;
            }
            if (winResult != BoardStatus.RUNNING && result == BoardStatus.RUNNING){
                logger.debug("{} result was reached from checker", winResult);
                result = winResult;
            }
            if (winResult != BoardStatus.RUNNING && winResult != result){
                logger.debug("2 differ results from masks results in tie");
                return BoardStatus.TIE;
            }
        }
        return result;
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
        byte i1 = (byte) ((board >> inindixes[0]) & 1L);
        byte i2 = (byte) ((board >> inindixes[1]) & 1L);
        byte i3 = (byte) ((board >> inindixes[6]) & 1L);
        byte i4 = (byte) ((board  >> inindixes[7]) & 1L);
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
        byte i1 = (byte) ((board >> inindixes[0]) & 1L);
        byte i2 = (byte) ((board >> inindixes[1]) & 1L);
        byte i3 = (byte) ((board >> inindixes[6]) & 1L);
        byte i4 = (byte) ((board >> inindixes[7]) & 1L);
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
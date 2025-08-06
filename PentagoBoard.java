public class PentagoBoard {
    private long whiteBoard;
    private long blackBoard;
    private long occupiedBoard;


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

    public Byte checkDiagnal(long board)
    {
        long diag1 = 0b100000010000001000000100000010000000L; //1,4
        long diag2 = 0b10000001000000100000010000001L; //0,5
        long diag3 = 0b100000010000001000000100000010L; //0,4
        long diag4 = 0b10000001000000100000010000001000000L;//1,5
        long diag5 = 0b10000100001000010000100000L; //0,0
        long diag6 = 0b1000010000100001000010000000000L; //1,1
        long diag7 = 0b10000100001000010000100000000000L; //1,0
        long diag8 = 0b1000010000100001000010000L;// 0,1
        long[] masks = { diag1, diag2, diag3, diag4, diag5, diag6,diag7,diag8 };
        for (long mask : masks) {
            if ((board & mask) == mask)
            {
                return 1;
            }
        }
        return 0;
    }
    public Byte checkColumn(long board) {
        long colMask;
        long colMask2;
        for (int col = 0; col <= 5; col++)
        {
            colMask = 0b1000001000001000001000001L << col;
            colMask2 = 0b1000001000001000001000001000000L  << col;
            if ((board & colMask) == colMask || (board & colMask2) == colMask2)
            {
                return 1;
            }

        }
        return 0;
    }
    public Byte checkRow(long board)
    {
        long rowMask;
        long rowMask2;
        for (int col = 0; col <= 30; col+=6)
        {
            rowMask = 0b111110L << col;
            rowMask2 = 0b11111L  << col;
            if ((board & rowMask) == rowMask || (board & rowMask2) == rowMask2)
            {
                return 1;
            }

        }
        return 0;
    }
    public boolean isFullBoard()
    {
        long checkFull =  (1L << 36) - 1;
        if ((occupiedBoard & checkFull ) == checkFull)
        {
            return true;
        }
        return false;
    }
    public Byte checkWin(long blackBoard, long whiteBoard)
    {
        if(isFullBoard())
        {
            return 3;
        }
        byte stat = 0;
        if (checkDiagnal(blackBoard) == 1 || checkColumn(blackBoard) == 1 || checkRow(blackBoard) == 1)
        {
            stat = 1;
        }
        if (checkDiagnal(whiteBoard) == 1 || checkColumn(whiteBoard) == 1 || checkRow(whiteBoard) == 1)
        {
            if(stat == 1)
            {
                return 3;
            }
            return 2;
        }
        if(stat == 1)
        {
            return 1;
        }
        return 0;
    }
    public boolean checkLegal(int index)
    {
        if(((occupiedBoard >> index) & 1) == 1)
        {
            return false;
        }
        return true;
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
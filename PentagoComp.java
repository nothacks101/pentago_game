import java.util.Random;

public class PentagoComp
{
    private PentagoBoard board;

    public PentagoComp(PentagoBoard board)
    {
        this.board = board;
    }

    public int makeMove(boolean isBlack) {
        long cboard = isBlack ? board.getBlackBoard() : board.getWhiteBoard();
        int winMove = findImmediateWin(cboard, isBlack);
        if (winMove != -1) {
            return winMove;
        }

        int winrot = winRotate(isBlack);
        if(winrot != -1)
        {
            return winrot;
        }

        int threatMove = findImmediateThreat(isBlack);
        if (threatMove != -1) {
            return threatMove;
        }

        int loserot = loseRotate(isBlack);
        if(loserot != -1)
        {
            return loserot;
        }

        int centerMove = findCenterMove();
        if (centerMove != -1) {
            return centerMove;
        }

        int three = threeGroup(isBlack);
        if(three!=-1)
        {
            return three;
        }
        int advanceMove = findAdvancingMove(cboard);
        if (advanceMove != -1) {
            return advanceMove;
        }

        return -1;
    }

    private int findImmediateWin(long cboard, boolean isBlack) {
        for (int i = 0; i < 36; i++) {
            if (board.checkLegal(i)) {
                long newBoardState = cboard | (1L << i);
                if (isBlack && board.checkWin(newBoardState, board.getWhiteBoard()) == 1)
                {
                    return i;
                }
                else if(!isBlack && board.checkWin(board.getBlackBoard(), newBoardState) == 2)
                {
                    return i;
                }
            }
        }
        return -1;
    }

    private int findImmediateThreat(boolean isBlack)
    {
        long cboard = isBlack ? board.getWhiteBoard() : board.getBlackBoard();
        for (int i = 0; i < 36; i++) {
            if (board.checkLegal(i)) {
                long newOpponentBoard = cboard | (1L << i);
                if (isBlack && board.checkWin(board.getBlackBoard(), newOpponentBoard) == 2)
                {
                    return i;
                }
                else if(!isBlack && board.checkWin(newOpponentBoard, board.getWhiteBoard()) == 1)
                {
                    return i;
                }
            }
        }
        return -1;
    }

    private int findCenterMove() {
        int[] centers = {7, 10, 25, 28};
        for (int center : centers) {
            if (board.checkLegal(center)) {
                return center;
            }
        }
        return -1;
    }


    public int findAdvancingMove(long cboard) {
        Random rand = new Random();

        int[] checks = {1, 2, 3};
        shuffleArr(checks);

        for (int check : checks) {
            int index = -1;
            switch (check) {
                case 1:
                    index = checkRows(cboard,board.getOccupiedBoard());
                    break;
                case 2:
                    index = checkColumns(cboard,board.getOccupiedBoard());
                    break;
                case 3:
                    index = checkDiagonals(cboard,board.getOccupiedBoard());
                    break;
            }
            if (index != -1) {
                return index;
            }
        }

        return -1;
    }
    public static void shuffleArr(int[] arr) {
        int index, temp;
        Random random = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }

    private int checkRows(long boardState, long occ)
    {
        int[] rows = new int[6];
        int count = -1;
        int chosen,index;
        Random random = new Random();
        for (int row = 0; row <=30; row+=6) {
            long rowMask = 0b111111L << row;
            long rowState = boardState & rowMask;
            long fullState = occ & rowMask;
            if (rowState > 0 && fullState < rowMask)
            {
                count++;
                rows[count] = row;
            }
        }
        if(count == -1)
        {
            return -1;
        }
        chosen = random.nextInt(count+1);
        index = rows[chosen];
        count=-1;
        for(int col = index; col <= index+5; col++)
        {
            if(board.checkLegal(col))
            {
                count++;
                rows[count] = col;
            }
        }
        chosen = random.nextInt(count+1);
        return rows[chosen];
    }

    private int checkColumns(long boardState, long occ) {
        int[] cols = new int[6];
        int count = -1;
        int chosen, index;
        Random random = new Random();
        for (int col = 0; col <= 5; col++) {
            long colMask = 0b1000001000001000001000001000001L << col;
            long colState = boardState & colMask;
            long fullState = occ & colMask;
            if (colState > 0 && fullState < colMask) {
                count++;
                cols[count] = col;
            }
        }
        if (count == -1) {
            return -1;
        }
        chosen = random.nextInt(count + 1);
        index = cols[chosen];
        count = -1;
        for (int row = index; row <= index+30; row+=6) {
            if (board.checkLegal(row)) {
                count++;
                cols[count] = row;
            }
        }
        chosen = random.nextInt(count + 1);
        return cols[chosen];
    }

    private int checkDiagonals(long boardState,long occ)
    {
        int[] diags = new int[6];
        int count = -1;
        int chosen, index;
        Random random = new Random();
        long diag1 = 0b100000010000001000000100000010L; //0,4
        long diag2 = 0b100000010000001000000100000010000001L; //0,5
        long diag3 = 0b10000001000000100000010000001000000L;//1,5
        long diag4 = 0b100001000010000100001000001000000L; //0,0
        long diag5 = 0b10000100001000010000100000000000L; //1,0
        long diag6 = 0b1000010000100001000010000L;// 0,1
        long[] masks = { diag1, diag2, diag3, diag4, diag5, diag6};
        for (int i =0; i<=5; i++)
        {
            long diagState = boardState & masks[i];
            long fullState = occ & masks[i];
            if (diagState > 0 && fullState < masks[i])
            {
                count++;
                diags[count] = i;
            }
        }
        if(count == -1)
        {
            return -1;
        }
        chosen = random.nextInt(count + 1);
        index = diags[chosen];
        count = -1;
        int len = 0;
        int[] positions = new int[6];
        if (index == 0)
        {
            int[] pos = {1,8,15,22,29};
            positions = pos;
            len =5;
        }
        else if (index == 1)
        {
            int[] pos = {0, 7, 14, 21, 28, 35};
            positions = pos;
            len =6;
        }
        else if (index == 2)
        {
            int[] pos = {6, 13, 20, 27, 34};
            positions = pos;
            len =5;
        }
        else if (index == 3)
        {
            int[] pos = {5,10,15,20,25,30};
            positions = pos;
            len =6;
        }
        else if (index == 4)
        {
            int[] pos = {4,9,14,19,24};
            positions = pos;
            len =5;
        }
        else if (index == 5)
        {
            int[] pos = {11, 16, 21, 26,31};
            positions = pos;
            len =5;
        }
        for(int i = 0; i<len; i++)
        {
            if(board.checkLegal(positions[i]))
            {
                count++;
                diags[count] = positions[i];
            }
        }
        return diags[random.nextInt(count + 1)];
    }

    public int threeGroup(boolean isBlack)
    {
        Random rand = new Random();

        int[] checks = {1, 2};
        shuffleArr(checks);

        for (int check : checks) {
            int index = -1;
            switch (check) {
                case 1:
                    index = threeRow(isBlack);
                    break;
                case 2:
                    index = threeCol(isBlack);
                    break;
            }
            if (index != -1) {
                return index;
            }
        }

        return -1;
    }

    public int threeRow(boolean isBlack)
    {
        long cboard = isBlack ? board.getBlackBoard() : board.getWhiteBoard();
        long row1 = 0b110L;
        long row2 = 0b101L;
        long row3 = 0b011L;
        int[] loc = new int[36];
        int count = -1;
        int chosen;
        Random random = new Random();
        for(int i = 0; i<= 33 ; i+=3)
        {
            if((row1 & cboard) == row1 && (board.checkLegal(i))){
                count++;
                loc[count] = i;
            }
            if((row2 & cboard) == row2&& (board.checkLegal(i + 1)))
            {
                count++;
                loc[count] = i+1;
            }
            if((row3 & cboard) == row3 && (board.checkLegal(i + 2)))
            {
                count++;
                loc[count] = i+2;
            }
            row1 = row1 << 3;
            row2 = row2 << 3;
            row3 = row3 << 3;
        }
        if (count == -1)
        {
            return -1;
        }
        chosen = random.nextInt(count + 1);

        return loc[chosen];
    }
    public int threeCol(boolean isBlack)
    {
        long cboard = isBlack ? board.getBlackBoard() : board.getWhiteBoard();
        long col1 = 0b1000001000000L;
        long col2 = 0b1000000000001L;
        long col3 = 0b1000001L;
        long col4 = 0b1000001000000000000000000000000L;
        long col5 = 0b1000000000001000000000000000000L;
        long col6 = 0b1000001000000000000000000L;
        int[] loc = new int[36];
        int count = -1;
        int chosen;
        Random random = new Random();
        for(int i = 0; i<=5; i++)
        {
            if((col1 & cboard) == col1 && (board.checkLegal(i)))
            {
                count++;
                loc[count] =i;
            }
            if((col2 & cboard) == col2&& (board.checkLegal(i+6)))
            {
                count++;
                loc[count] =i+6;
            }
            if((col3 & cboard) == col3&& (board.checkLegal(i+12)))
            {
                count++;
                loc[count] =i+12;
            }
            if((col4 & cboard) == col4&& (board.checkLegal(i+18)))
            {
                count++;
                loc[count] =i+18;
            }
            if((col5 & cboard) == col5&& (board.checkLegal(i+24)))
            {
                count++;
                loc[count] =i+24;
            }
            if((col6 & cboard) == col6&& (board.checkLegal(i+12)))
            {
                count++;
                loc[count] =i+12;
            }
            col1= col1<<1;
            col2 = col2<<1;
            col3 = col3<<1;
            col4 = col4<<1;
            col5 = col5<<1;
            col6 = col6<<1;
        }
        if (count == -1)
        {
            return -1;
        }
        chosen = random.nextInt(count + 1);
        return loc[chosen];
    }


    //////////////////////////////////////////////////////////////////////////////////
    public int winRotate(boolean isBlack)
    {
        int[] right = {12,  6,  0, 15,  9,  3, 13,  7,  1, 16, 10,  4, 14,  8,  2, 17, 11,  5, 30, 24, 18, 33, 27, 21, 31, 25, 19, 34, 28, 22, 32, 26, 20, 35, 29, 23};
        int[] left ={ 2,  8, 14,  5, 11, 17, 1,  7, 13,  4, 10, 16, 0,  6, 12,  3,  9, 15, 20, 26, 32, 23, 29, 35, 19, 25, 31, 22, 28, 34, 18, 24, 30, 21, 27, 33};
        int check =0;
        int index;;
        for (int quadrant = 1; quadrant <= 4; quadrant++) {
            for (int rotation = 1; rotation <= 2; rotation++) {
                PentagoBoard tempBoard = new PentagoBoard();
                tempBoard.setBlackBoard(board.getBlackBoard());
                tempBoard.setWhiteBoard(board.getWhiteBoard());
                tempBoard.setOccupiedBoard(board.getOccupiedBoard());
                tempBoard.updateRotaion(quadrant, rotation);
                check = findImmediateWinOnBoard(tempBoard,isBlack);
                if (check != -1) {
                    if (rotation == 1) {
                        index = right[check];
                    } else {
                        index = left[check];
                    }
                    if (findQuadrant(index) == quadrant) {
                        return index;
                    }
                }
            }
        }
        return -1;
    }
    public int loseRotate(boolean isBlack) {
        int[] right = {12,  6,  0, 15,  9,  3, 13,  7,  1, 16, 10,  4, 14,  8,  2, 17, 11,  5, 30, 24, 18, 33, 27, 21, 31, 25, 19, 34, 28, 22, 32, 26, 20, 35, 29, 23};
        int[] left ={ 2,  8, 14,  5, 11, 17, 1,  7, 13,  4, 10, 16, 0,  6, 12,  3,  9, 15, 20, 26, 32, 23, 29, 35, 19, 25, 31, 22, 28, 34, 18, 24, 30, 21, 27, 33};
        int check = -1;
        int index;
        for (int quadrant = 1; quadrant <= 4; quadrant++) {
            for (int rotation = 1; rotation <= 2; rotation++) {
                PentagoBoard tempBoard = new PentagoBoard();
                tempBoard.setBlackBoard(board.getBlackBoard());
                tempBoard.setWhiteBoard(board.getWhiteBoard());
                tempBoard.setOccupiedBoard(board.getOccupiedBoard());
                tempBoard.updateRotaion(quadrant, rotation);

                check = findImmediateThreatOnBoard(tempBoard, isBlack);
                if (check != -1) {

                    if (rotation == 1) {
                        index = right[check];
                    } else {
                        index = left[check];
                    }
                    if (findQuadrant(index) != quadrant) {
                        return check;
                    }
                    return index;
                }
            }
        }
        return -1;
    }
    public int findQuadrant(int index)
    {
        int row = index / 6;
        int col = index % 6;

        if  (row < 3 && col >= 3)
        {
            return 1;
        } else if (row < 3 && col < 3)
        {
            return 2;
        } else if (row >= 3 && col < 3)
        {
            return 3;
        } else
        {
            return 4;
        }
    }
    private int findImmediateWinOnBoard(PentagoBoard tempBoard, boolean isBlack)
    {
        long cboard = isBlack ? tempBoard.getBlackBoard() : tempBoard.getWhiteBoard();
        long opponentBoard = isBlack ? tempBoard.getWhiteBoard() : tempBoard.getBlackBoard();
        long newBoardState;
        for (int i = 0; i < 36; i++)
        {
            if (tempBoard.checkLegal(i)) {
                newBoardState = cboard | (1L << i);
                int winCheck = tempBoard.checkWin(newBoardState, opponentBoard);

                if ((isBlack && winCheck == 2) || (!isBlack && winCheck == 1)) {
                    return i;
                }
            }
        }
        return -1;
    }
    private int findImmediateThreatOnBoard(PentagoBoard tempBoard, boolean isBlack) {
        long opponentBoard = isBlack ? tempBoard.getWhiteBoard() : tempBoard.getBlackBoard();
        long playerBoard = isBlack ? tempBoard.getBlackBoard() : tempBoard.getWhiteBoard();
        long newOpponentBoard;
        for (int i = 0; i < 36; i++) {
            if (tempBoard.checkLegal(i)) {
                newOpponentBoard = opponentBoard | (1L << i);
                int winCheck = tempBoard.checkWin(playerBoard, newOpponentBoard);
                if ((isBlack && winCheck == 1) || (!isBlack && winCheck == 2)) {
                    return i;
                }
            }
        }
        return -1;
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    public int makeRotation(boolean isBlack)
    {
        int winningRotation = findWinningRotation(isBlack);
        if (winningRotation != -1) {
            return winningRotation;
        }

        int threatRotation = findThreatRotation(isBlack);
        if (threatRotation != -1) {

            return threatRotation;
        }

        int beneficial = findBeneficialRotation(isBlack);
        if(beneficial != -1)
        {
            return beneficial;
        }

        int randomizer = randomRotation(isBlack);
        if (randomizer != -1) {
            return randomizer;
        }

        return -1;
    }
    public int findBeneficialRotation(boolean isBlack) {
        long cboard = isBlack ? board.getBlackBoard() : board.getWhiteBoard();
        Random rand = new Random();

        int[] checks = {1, 2, 3};
        shuffleArr(checks);

        for (int check : checks) {
            int index = -1;
            switch (check) {
                case 1:

                    index = checkbenRows(cboard, board.getOccupiedBoard(), isBlack);
                    break;
                case 2:
                    index = checkbenColumns(cboard, board.getOccupiedBoard(), isBlack);
                    break;
                case 3:
                    index = checkbenDiagonals(cboard, board.getOccupiedBoard(), isBlack);
                    break;
            }
            if (index != -1) {
                return index;
            }
        }

        return -1;
    }


    private int findWinningRotation(boolean isBlack)
    {
        Byte winstate =0;
        for (int quadrant = 1; quadrant <= 4; quadrant++) {
            for (int rotation = 1; rotation <= 2; rotation++) {
                PentagoBoard tempBoard = new PentagoBoard();
                tempBoard.setBlackBoard(board.getBlackBoard());
                tempBoard.setWhiteBoard(board.getWhiteBoard());
                tempBoard.setOccupiedBoard(board.getOccupiedBoard());
                tempBoard.updateRotaion(quadrant, rotation);
                winstate = tempBoard.checkWin(tempBoard.getBlackBoard(), tempBoard.getWhiteBoard());
                if ((isBlack && winstate == 1))
                {
                    return (quadrant - 1) * 2 + rotation;
                } else if (!isBlack && winstate == 2) {
                    return (quadrant - 1) * 2 + rotation;
                }
            }
        }
        return -1;
    }

    private int findThreatRotation(boolean isBlack) {
        for (int quadrant = 1; quadrant <= 4; quadrant++) {
            for (int rotation = 1; rotation <= 2; rotation++) {
                PentagoBoard tempBoard = new PentagoBoard();
                tempBoard.setBlackBoard(board.getBlackBoard());
                tempBoard.setWhiteBoard(board.getWhiteBoard());
                tempBoard.setOccupiedBoard(board.getOccupiedBoard());
                tempBoard.updateRotaion(quadrant, rotation);
                long cboard = isBlack ? board.getBlackBoard() : board.getWhiteBoard();
                if (isBlack && tempBoard.checkWin(tempBoard.getWhiteBoard(), tempBoard.getBlackBoard()) == 2)
                {
                    if(rotation == 1)
                    {
                        return (quadrant - 1) * 2 +2;
                    }
                    return (quadrant - 1) * 2 +1;
                } else if (!isBlack && tempBoard.checkWin(tempBoard.getBlackBoard(), tempBoard.getWhiteBoard()) == 1)
                {
                    if(rotation == 1)
                    {
                        return (quadrant - 1) * 2 +2;
                    }
                    return (quadrant - 1) * 2 +1;
                }
            }
        }
        return -1;
    }
    private int checkbenRows(long cboard, long occupiedBoard, boolean isBlack) {
        long rowMask = 0b111111;
        int[] results = new int[6];
        int count =-1;
        int chosen;
        Random rand = new Random();
        for (int row = 0; row <= 30; row+=6) {
            long rowPattern = rowMask << row;
            if ((cboard & rowPattern) > 0 && (occupiedBoard & rowPattern) != rowPattern)
            {
                int rotation = findBestRotationForPattern(cboard, rowPattern, isBlack);
                if (rotation != -1) {
                    count++;
                    results[count]= rotation;
                }
            }
        }
        if(count == -1)
        {
            return -1;
        }
        chosen = rand.nextInt(count+1);
        return results[chosen];
    }
    private int checkbenColumns(long cboard, long occupiedBoard, boolean isBlack) {
        long colMask = 0b1000001000001000001000001000001L;
        int[] results = new int[6];
        int count =-1;
        int chosen;
        Random rand = new Random();
        for (int col = 0; col <= 5; col++) {
            long colPattern = colMask << col;
            if ((cboard & colPattern) > 0 && (occupiedBoard & colPattern) != colPattern)
            {
                int rotation = findBestRotationForPattern(cboard, colPattern, isBlack);
                if (rotation != -1) {
                    count++;
                    results[count]= rotation;
                }
            }
        }
        if(count == -1)
        {
            return -1;
        }
        chosen = rand.nextInt(count+1);
        return results[chosen];
    }
    private int checkbenDiagonals(long cboard, long occupiedBoard, boolean isBlack) {
        long diag1 = 0b100000010000001000000100000010L; //0,4
        long diag2 = 0b100000010000001000000100000010000001L; //0,5
        long diag3 = 0b10000001000000100000010000001000000L;//1,5
        long diag4 = 0b100001000010000100001000001000000L; //0,0
        long diag5 = 0b10000100001000010000100000000000L; //1,0
        long diag6 = 0b1000010000100001000010000L;// 0,1
        long[] masks = { diag1, diag2, diag3, diag4, diag5, diag6};
        long diagonal;
        int[] indexs ={0,1,2,3,4,5};
        shuffleArr(indexs);
        for (int i = 0; i<=5; i++)
        {
            diagonal = masks[indexs[i]];
            if ((cboard & diagonal) > 0 && (occupiedBoard & diagonal) != diagonal)
            {
                int rotation = findBestRotationForPattern(cboard, diagonal, isBlack);
                if (rotation != -1) {
                    return rotation;
                }
            }
        }
        return -1;
    }
    private int findBestRotationForPattern(long cboard,long pattern, boolean isBlack) {
        for (int quadrant = 1; quadrant <= 4; quadrant++) {
            for (int rotation = 1; rotation <= 2; rotation++) {
                PentagoBoard tempBoard = new PentagoBoard();
                tempBoard.setBlackBoard(board.getBlackBoard());
                tempBoard.setWhiteBoard(board.getWhiteBoard());
                tempBoard.setOccupiedBoard(board.getOccupiedBoard());
                tempBoard.updateRotaion(quadrant, rotation);

                long newBoard = isBlack ? tempBoard.getBlackBoard() : tempBoard.getWhiteBoard();
                if ((newBoard & pattern) > (cboard & pattern)) {
                    return (quadrant - 1) * 2 + rotation;
                }
            }
        }
        return -1;
    }
    private int randomRotation(boolean isBlack)
    {
        Random random = new Random();
        int quadrant = random.nextInt(4) + 1; // Random quadrant (1 to 4)
        int rotation = random.nextInt(2) + 1; // Random rotation (1 or 2)
        return (quadrant - 1) * 2 + rotation;
    }
}
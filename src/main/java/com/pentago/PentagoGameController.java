package com.pentago;


public class PentagoGameController {
    private PentagoBoard board;
    private PentagoComp computer;
    private boolean isBlackTurn;
    private boolean playerTurn;
    private boolean gameOver;
    private Byte winner;

    public PentagoGameController(boolean playerStartsFirst, PentagoBoard board, PentagoComp computre) {
        this.board = board;
        this.computer = computre;
        this.playerTurn = playerStartsFirst;
        this.isBlackTurn = false;
        this.gameOver = false;
        winner=0;

    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void computerTurn()
    {
        if(!gameOver)
        {
            int move = computer.makeMove(isBlackTurn);
            if(isBlackTurn)
            {
                board.updateBoard(move, true);
            }
            else
            {
                board.updateBoard(move, false);
            }
            
            int rotation = computer.makeRotation(isBlackTurn);
            int rot, key;
            if(rotation % 2 == 0)
            {
                rot = 2;
                key = rotation/2;
            }
            else
            {
                rot = 1;
                key = (rotation+1)/2;
            }

            board.updateRotaion(key,rot);
            winner = board.checkWin();
            if (winner != 0) {
                gameOver = true;
            }
            playerTurn = true;
            isBlackTurn = !isBlackTurn;
        }
    }
    public boolean isGameOver() {
        if(board.checkWin() != 0)
        {
            gameOver = true;
        }
        return gameOver;
    }

    public Byte getWinner() {
        winner = board.checkWin();
        return winner;
    }

    public boolean isBlackTurn() {
        return isBlackTurn;
    }
    public String getCurrentPlayer() {
        return isBlackTurn ? "Black" : "White";
    }


    public PentagoBoard getBoard() {
        return board;
    }

    public boolean makeMove(int row, int col)
    {
        if(playerTurn & !gameOver)
        {
            int index = row * 6 + col;
            if (!board.checkLegal(index)) {
                return false;
            }
            if(isBlackTurn)
            {
                board.updateBoard(index, true);
            }
            else
            {
                board.updateBoard(index, false);
            }
            winner = board.checkWin();
            if (winner != 0) {
                gameOver = true;
            }
            playerTurn = false;
            return true;
        }
        return false;

    }
    public void rotateBoard(int key, int rotate) {
        board.updateRotaion(key , rotate);
        byte winStatus = board.checkWin();
        if (winStatus != 0) {
            gameOver = true;
        }
        isBlackTurn = !isBlackTurn;
        computerTurn();
    }
    public void resetGame() {
        board.resetBoards();
        isBlackTurn = true;
        gameOver = false;
    }
}
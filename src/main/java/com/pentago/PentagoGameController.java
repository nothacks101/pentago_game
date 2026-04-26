package com.pentago;

import com.pentago.ComputerMoves.Move;
import com.pentago.Enums.BoardStatus;
import com.pentago.View.GameView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PentagoGameController {
    private static final Logger logger = LoggerFactory.getLogger(PentagoGameController.class);
    private PentagoBoard board;
    private PentagoComp computer;
    private boolean isPlayerBlack;
    private boolean isWhiteTurn;
    private boolean gameOver;
    private GameView view;

    public PentagoBoard getBoard(){
        return this.board;
    }

    public boolean getIsWhiteTurn(){
        return this.isWhiteTurn;
    }

    public void setView(GameView view){
        this.view = view;
    }
    public PentagoGameController(boolean playerStartsFirst, PentagoBoard board,
                                 PentagoComp computer, GameView view) {
        this.board = board;
        this.computer = computer;
        this.isWhiteTurn = true;
        this.isPlayerBlack = !playerStartsFirst;
        this.gameOver = false;
        this.view = view;
    }
    public void startGame() {
        view.updateView();
        if (isPlayerBlack == isWhiteTurn) {
            computerTurn();
        }
    }


    public void computerTurn()
    {
        if(!gameOver)
        {
            Move movement = computer.makeMove(this.board, this.isWhiteTurn);
            this.board.updateBoard(movement.position, isPlayerBlack);
            this.board.updateRotation(movement.rotation.quadrant, movement.rotation.clockwise);

            if (this.board.checkWin() != BoardStatus.RUNNING) {
                this.gameOver = true;
            }
            this.isWhiteTurn = !this.isWhiteTurn;
            this.view.updateView();
        }
    }




    public boolean playerMakeMove(int row, int col)
    {
        logger.debug("here 1");
        if(isPlayerBlack == !isWhiteTurn & !gameOver)
        {
            int index = row * 6 + col;
            if (!board.checkLegal(index)) {
                return false;
            }
            board.updateBoard(index, this.isWhiteTurn);
            this.view.updateView();
            return true;
        }
        return false;

    }
    public void playerRotateBoard(int key, int rotate) {
        board.updateRotation(key , rotate == 1);
        BoardStatus winStatus = board.checkWin();
        isWhiteTurn = !isWhiteTurn;
        this.view.updateView();
        this.computerTurn();
    }
    public void resetGame() {
        board.resetBoards();
        isPlayerBlack = true;
        gameOver = false;
    }

}
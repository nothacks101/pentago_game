package com.pentago.View;
import com.pentago.ComputerRotations.Rotation;
import com.pentago.Enums.BoardStatus;
import com.pentago.PentagoBoard;
import com.pentago.PentagoGameController;

import javax.swing.*;
import java.awt.*;

public class PentagoGUI extends JFrame implements GameView {
    private JFrame frame;
    private JPanel boardPanel;
    private JButton[][] buttons;
    private JLabel statusLabel;
    private PentagoGameController controller;

    public void setController(PentagoGameController controller){
        this.controller = controller;
    }


    public PentagoGUI(PentagoGameController controller) {
        this.controller = controller;
        this.frame = new JFrame("Pentago Game");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(600, 700);

        this.boardPanel = new JPanel();
        this.boardPanel.setLayout(new GridLayout(6, 6));
        this.buttons = new JButton[6][6];

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                final int inner_row = row;
                final int inner_col = col;
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[row][col].addActionListener(e -> {
                    if (controller.playerMakeMove(inner_row, inner_col)) {
                        Rotation r = rotateBoardPrompt();
                        controller.playerRotateBoard(r.quadrant, r.clockwise ? 1 : 0);
                    }
                });
                boardPanel.add(buttons[row][col]);
            }
        }

        this.statusLabel = new JLabel("Current turn: White");
        this.statusLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.frame.add(statusLabel, BorderLayout.NORTH);
        this.frame.add(boardPanel, BorderLayout.CENTER);
        this.frame.setVisible(true);
    }


    private Rotation rotateBoardPrompt() {
        boolean isValid = false;
        int quadrant = 0;
        int clockwise = 0;
        while (!isValid){
           try {
               quadrant = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter quadrant to rotate (1-4):"));
               if (1 <= quadrant & quadrant <= 4){
                   isValid = true;
               }
           } catch (Exception e){
           }
        }
        isValid = false;
        while (!isValid){
            try {
                clockwise = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter rotation direction " +
                        "(1 for not clockwise, 2 for clockwise):"));
                if (1 <= clockwise & clockwise <= 2){
                    isValid = true;
                }
            } catch (Exception e){
            }
        }
        return new Rotation(quadrant, clockwise == 2);
    }



    public void updateView() {
        PentagoBoard board = this.controller.getBoard();
        boolean isWhiteTurn = this.controller.getIsWhiteTurn();
        long whiteBoard = board.getWhiteBoard();
        long blackBoard = board.getBlackBoard();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int index = i * 6 + j;
                if (((whiteBoard >> index) & 1) == 1) {
                    buttons[i][j].setText("W");
                    buttons[i][j].setBackground(Color.WHITE);
                    buttons[i][j].setEnabled(false);
                } else if (((blackBoard >> index) & 1) == 1) {
                    buttons[i][j].setText("B");
                    buttons[i][j].setBackground(Color.BLACK);
                    buttons[i][j].setEnabled(false);
                    buttons[i][j].setForeground(Color.WHITE);
                } else {
                    buttons[i][j].setText("");
                    buttons[i][j].setEnabled(true);
                    buttons[i][j].setBackground(null);
                    buttons[i][j].setOpaque(true);
                    buttons[i][j].setBorderPainted(true);
                }
            }
        }

        if (board.isGameOver()) {
            BoardStatus winner = board.getWinner();
            String winnerText = "";
            switch (winner) {
                case BLACK_WIN:
                    winnerText = "Black";
                    break;
                case WHITE_WIN:
                    winnerText = "White";
                    break;
                case TIE:
                    winnerText = "Draw";
                    break;
            }
            statusLabel.setText("Game Over! Winner: " + winnerText);
        } else {
            statusLabel.setText("Current turn: " + (isWhiteTurn ? "White" : "Black"));
        }
    }

}

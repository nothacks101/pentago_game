package com.pentago;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PentagoGUI implements ActionListener {
    private PentagoGameController game;
    private JFrame frame;
    private JPanel boardPanel;
    private JButton[][] buttons;
    private JLabel statusLabel;

    public PentagoGUI(PentagoGameController game) {
        this.game = game;
        this.frame = new JFrame("Pentago Game");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(600, 700);

        this.boardPanel = new JPanel();
        this.boardPanel.setLayout(new GridLayout(6, 6));
        this.buttons = new JButton[6][6];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }

        this.statusLabel = new JLabel("Current turn: White");
        this.statusLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.frame.add(statusLabel, BorderLayout.NORTH);
        this.frame.add(boardPanel, BorderLayout.CENTER);
        this.frame.setVisible(true);
        if (!game.isPlayerTurn()) {
            game.computerTurn();
            updateView();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (buttons[i][j] == button) {
                    if (game.makeMove(i, j)) {
                        updateView();
                        if (!game.isGameOver()) {
                            rotateBoardPrompt();
                        }
                    }
                    return;
                }
            }
        }
    }

    private void rotateBoardPrompt() {
        int key = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter quadrant to rotate (1-4):"));
        int rotate = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter rotation direction (1 for right, 2 for left):"));
        game.rotateBoard(key, rotate);
        updateView();
    }

    private void updateView() {
        PentagoBoard board = game.getBoard();
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

        if (game.isGameOver()) {
            byte winner = game.getWinner();
            String winnerText = "";
            switch (winner) {
                case 1:
                    winnerText = "Black";
                    break;
                case 2:
                    winnerText = "White";
                    break;
                case 3:
                    winnerText = "Draw";
                    break;
            }
            statusLabel.setText("Game Over! Winner: " + winnerText);
        } else {
            statusLabel.setText("Current turn: " + (game.isBlackTurn() ? "Black" : "White"));
        }
    }

}

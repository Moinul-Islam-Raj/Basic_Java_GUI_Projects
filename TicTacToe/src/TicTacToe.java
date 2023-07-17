import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;


import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;



public class TicTacToe implements ActionListener, MouseListener{
    String turn = "X";
    int[][] winConditions = {
            // horizontal
            {0,1,2},
            {3,4,5},
            {6,7,8},

            {0,3,6},
            {1,4,7},
            {2,5,8},

            {0,4,8},
            {2,4,6}
    };

    JFrame frame = new JFrame();
    JPanel mainPanel = new JPanel(new BorderLayout(0,5));
    JPanel buttonGridPanel = new JPanel(new GridLayout(3,3));
    JLabel turnLabel = new JLabel(turn);
    JLabel winnerLabel = new JLabel();
    JButton[] buttons = new JButton[9];

    TicTacToe() {
        frame.setSize(500,650);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tic Tac Toe made by Raj 😎");
        frame.setLayout(new BorderLayout());

        turnLabel.setHorizontalAlignment(JLabel.CENTER);
        turnLabel.setBorder(BorderFactory.createLineBorder(Color.white,10));
        turnLabel.setFont(new Font(null,Font.PLAIN,35));
        turnLabel.setBackground(Color.white);

        winnerLabel.setHorizontalAlignment(JLabel.CENTER);
        winnerLabel.setBorder(BorderFactory.createLineBorder(Color.white,10));
        winnerLabel.setFont(new Font(null,Font.BOLD,45));
        winnerLabel.setBackground(Color.white);
        winnerLabel.setText("😎😎😎");

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("");
            buttons[i].addActionListener(this);
            buttons[i].addMouseListener(this);

            buttons[i].setFont(new Font(null,Font.BOLD,40));
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
            buttons[i].setBackground(Color.white);
            buttons[i].setFocusable(false);

            buttonGridPanel.add(buttons[i]);
        }
        mainPanel.add(turnLabel, BorderLayout.NORTH);
        mainPanel.add(buttonGridPanel, BorderLayout.CENTER);
        mainPanel.add(winnerLabel, BorderLayout.SOUTH);
        mainPanel.setBackground(Color.white);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }
    //-----  main  -----

    public static void main(String[] args) {
        new TicTacToe();
    }

    // class methods
    void swapTurn() {
        turn = turn == "X" ? "O" : "X";
        turnLabel.setText(turn);
    }
    boolean checkWin() {
        for (int i = 0; i < winConditions.length; i++) {
            int[] indexes = winConditions[i];
            if(buttons[indexes[0]].getText().equals(buttons[indexes[1]].getText())  && buttons[indexes[1]].getText().equals(buttons[indexes[2]].getText()) && !buttons[indexes[0]].getText().equals("")) return true;
        }
        return false;
    }
    boolean isDraw(){
        boolean temp = true;
        for (int i = 0; i < buttons.length; i++) {
            if(buttons[i].getText().equals("")) temp =  false;
        }
        return temp;
    }

    //------ Implements ------

    //ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttons[4] && !buttons[0].isEnabled()){
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].setEnabled(true);
                buttons[i].setText("");
                turn = "X";
                winnerLabel.setText("😎  😎  😎");
                turnLabel.setText(turn);
            }
        }
        else {
            for (int i = 0; i < buttons.length; i++) {
                if (e.getSource() == buttons[i] && buttons[i].getText().equals("")) {
                    buttons[i].setText(turn);

                    if (checkWin()) {
                        for (int j = 0; j < buttons.length; j++) {
                            if (j != 4) {
                                buttons[j].setEnabled(false);
                                buttons[j].setBackground(Color.white);
                                buttons[i].setBorder(BorderFactory.createLineBorder(Color.gray, 1));
                            }
                        }
                        buttons[4].setText("Reset");
                        winnerLabel.setText(turn + " is the WINNER!!!");
                        return;
                    } else if (isDraw()) {
                        for (int j = 0; j < buttons.length; j++) {
                            if (j != 4) {
                                buttons[j].setEnabled(false);
                                buttons[j].setBackground(Color.white);
                                buttons[i].setBorder(BorderFactory.createLineBorder(Color.gray, 1));
                            }
                        }
                        turnLabel.setText("DRAW");
                        buttons[4].setText("Reset");
                        winnerLabel.setText("Its a DRAW!!!");
                        return;
                    }
                    swapTurn();
                }
            }
        }
    }

    //MouseListener
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            if(e.getSource() == buttons[i] && buttons[i].isEnabled()) {
                buttons[i].setBackground(Color.lightGray);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            if(e.getSource() == buttons[i]) {
                buttons[i].setBackground(Color.white);
            }
        }
    }


}

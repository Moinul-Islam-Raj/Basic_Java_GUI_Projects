import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator implements ActionListener, MouseListener, KeyListener // Hey
{
    // vars
    double num1 = 0;
    double num2 = 0;
    double result = 0;
    String op = null;

    // J... 
    JTextField screen = new JTextField(); // screen
    JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 1 , 1)); // buttonsPane {buttons array}

    JPanel mainPanel = new JPanel(new BorderLayout()); // contentPane [screen, buttonPane]
    JFrame frame = new JFrame(); // window [contentPane]

    // button arrays declaration
    JButton[] numbers = new JButton[10];
    JButton[] funcButtons = new JButton[10];
    JButton[] buttons = new JButton[20];

    Calculator(){
        //frame works
        frame.setTitle("Dark Calculator By Raj");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(270 * 2, 380 * 2);
        
        // assign numbers array
        for(short i = 0; i < 10;i++){
            numbers[i] = new JButton(String.valueOf(i));
        }

        //func button assignment
        funcButtons[0] = new JButton("AC");
        funcButtons[1] = new JButton("DEL");
        funcButtons[2] = new JButton("+/-");
        funcButtons[3] = new JButton("Q");
        funcButtons[4] = new JButton("/");
        funcButtons[5] = new JButton("x");
        funcButtons[6] = new JButton("-");
        funcButtons[7] = new JButton(".");
        funcButtons[8] = new JButton("=");
        funcButtons[9] = new JButton("+");

        // num button assignment
        buttons[4] = numbers[7];
        buttons[5] = numbers[8];
        buttons[6] = numbers[9];
        buttons[8] = numbers[4];
        buttons[9] = numbers[5];
        buttons[10] = numbers[6];
        buttons[12] = numbers[1];
        buttons[13] = numbers[2];
        buttons[14] = numbers[3];
        buttons[17] = numbers[0];

        //*------ func loop -------- */
        int[] functonIndexes = {0,1,2,3,7,11,15,16,18,19};
        for(int i = 0; i < 20; i++){
            if (i<10){
                buttons[functonIndexes[i]] = funcButtons[i];
            }
            buttonPanel.add(buttons[i]);

            // common button tasks
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFocusable(false);
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 7));
            buttons[i].setFont(new Font("sans serif", Font.PLAIN, 40));
            buttons[i].addActionListener(this);
            buttons[i].addMouseListener(this);
        }
        for (int i = 0; i < 10; i++) {
            funcButtons[i].setForeground(Color.ORANGE);
        }
        //*------ func loop end -------- */
        
        funcButtons[0].setForeground(Color.red);
        funcButtons[1].setForeground(Color.red);
        funcButtons[3].setForeground(Color.red);


        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setSize(new Dimension(520, 480));

        screen.setHorizontalAlignment(JTextField.RIGHT);
        screen.setEditable(false);
        screen.setBackground(Color.BLACK);
        screen.setBorder(BorderFactory.createLineBorder(Color.BLACK,20));
        screen.setForeground(Color.white);
        screen.setFont(new Font(null, Font.PLAIN, 50));
        screen.setMargin(new Insets(20,10,20,10));
        screen.setSize(new Dimension(540, 600));
        screen.addKeyListener(this);

        mainPanel.add(screen, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.addKeyListener(this);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setResizable(true);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // append numbers by clicking num buttons
        for (int i = 0; i < 10; i++) {
            if(e.getSource() == numbers[i]){
            
                if(screen.getText().equals("0")){
                    screen.setText(numbers[i].getText());
                }else{
                    screen.setText(screen.getText() + numbers[i].getText());
                }
            
            }
        }

        // math operator buttons
        if(e.getSource() == funcButtons[9] && !screen.getText().isEmpty()){
            Op_erator("+");
        }
        if(e.getSource() == funcButtons[6] && !screen.getText().isEmpty()){
            Op_erator("-");
        }
        if(e.getSource() == funcButtons[5] && !screen.getText().isEmpty()){
            Op_erator("*");
        }
        if(e.getSource() == funcButtons[4] && !screen.getText().isEmpty()){
            Op_erator("/");
        }

        // equals
        if(e.getSource() == funcButtons[8] && !screen.getText().isEmpty()){
            num2 = Double.parseDouble(screen.getText());
            if (op == null)
                op = "";
            switch (op) {
                case "" -> {
                    op = null;
                    num2 = 0;
                    return;
                }
                case "+" -> {
                    result = num1 + num2;
                }
                case "-" -> {
                    result = num1 - num2;
                }
                case "*" -> {
                    result = num1 * num2;
                }
                case "/" -> {
                    if (num1 != 0 && num2 != 0) {
                        result = num1 / num2;
                    } else {
                        result = 0;
                        JOptionPane.showMessageDialog(null, "Dividing by 0 is not possible!!!", "Error", JOptionPane.ERROR_MESSAGE, null);
                    }
                }
            }
            screen.setText(String.valueOf(Double.parseDouble(String.format("%.15f", result))));
            op = null;
            num2 = 0;
        }

        // buttons of other functions
        if(e.getSource() == funcButtons[0]){
            num1 = 0;
            num2 = 0;
            op = null;
            result = 0;
            screen.setText("");
        }
        if(e.getSource() == funcButtons[1]){
            String text = screen.getText();
            screen.setText("");

            for (int i = 0; i < String.valueOf(text).length() - 1; i++) {
                screen.setText(screen.getText() + String.valueOf(text).charAt(i));
            }
        }
        if(e.getSource() == funcButtons[7] && !screen.getText().contains(".") && !screen.getText().equals("")){
            screen.setText(screen.getText() + ".");
        }
        if(e.getSource() == funcButtons[2] && !screen.getText().isEmpty()){
            double ops = Double.parseDouble(screen.getText()) * -1;
            screen.setText(String.valueOf(ops));
        }
        if(e.getSource() == funcButtons[3]){
            int quit;
            quit = JOptionPane.showConfirmDialog(null, "Quit?", "Don't Quit!", JOptionPane.OK_CANCEL_OPTION);
            if(quit == 0){
                System.exit(0);
            }
        }
    }
    // main
    public static void main(String[] args) {
        new Calculator();
    }

    public void Op_erator(String op_t){
        if(op == null){
                num1 = Double.parseDouble(screen.getText());
                op = op_t;
                screen.setText("");
            }
            else {
                double screenText = Double.parseDouble(screen.getText());
                switch(op){
                    case "+":
                        num1 = num1 + screenText;
                        break;
                    case "-":
                        num1 = num1 - screenText;
                        break;
                    case "*":
                        num1 = num1 * screenText;
                        break;
                    case "/":
                        if(num1 != 0 && screenText != 0){
                            num1 = num1 / screenText;
                        }else{
                            num1 = 0;
                            JOptionPane.showMessageDialog(null, "Dividing by 0 is not possible!!!", "I know you are a DUMB!", JOptionPane.ERROR_MESSAGE, null);
                            op = null;
                        }

                        break;
                }
                if(op != null){
                    op = op_t;
                }
                screen.setText("");
            }
    }

    // mouse events
    @Override
    public void mouseClicked(MouseEvent e){}

    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < 20; i++) {
            if(e.getSource() == buttons[i]){
                buttons[i].setBackground(Color.LIGHT_GRAY);
                buttons[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseEntered(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        for (int i = 0; i < 20; i++) {
            if(e.getSource() == buttons[i]){
                buttons[i].setBackground(Color.GRAY);
                buttons[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (int i = 0; i < 20; i++) {
            if(e.getSource() == buttons[i]){
                buttons[i].setBackground(Color.BLACK);
                buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
            }
        }
    }

    // key events
    @Override
    public void keyTyped(KeyEvent e) {

        char[] cs = {'0', '1' , '2', '3' , '4', '5' , '6', '7' , '8', '9'};
        for (int i = 0; i < 10; i++) {
            if(Character.valueOf(e.getKeyChar()).equals(cs[i])){
                if(screen.getText().equals("0")){
                    screen.setText(numbers[i].getText());
                }else{
                    screen.setText(screen.getText() + numbers[i].getText());
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 8){
            funcButtons[1].doClick();
        }                                       // KeyBoard Accesiblity
        if(e.getKeyCode() == 67){
            funcButtons[0].doClick();
        }
        if(e.getKeyCode() == 81){
            funcButtons[3].doClick();
        }
        if(e.getKeyCode() == 92){
            funcButtons[2].doClick();
        }
        if(e.getKeyCode() == 46){
            funcButtons[7].doClick();
        }
        if(e.getKeyCode() == 10){
            funcButtons[8].doClick();
        }
        if(e.getKeyCode() == 47){
            funcButtons[4].doClick();
        }
        if(e.getKeyCode() == 88){
            funcButtons[5].doClick();
        }
        if(e.getKeyCode() == 45){
            funcButtons[6].doClick();
        }
        if(e.getKeyCode() == 61){
            funcButtons[9].doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
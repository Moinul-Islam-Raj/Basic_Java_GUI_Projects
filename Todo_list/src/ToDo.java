
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JComponent;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ToDo implements ActionListener, KeyListener, MouseListener
{
    Color backgroundColor = new Color(123, 50, 250);

    JFrame frame = new JFrame("To-Do List by RAJ");// window
    JPanel mainPanel = new JPanel(new BorderLayout(4,4));// window contentPane
    JPanel inputPanel = new JPanel(new BorderLayout(5,5));
    JTextField inputScreen = new JTextField();
    JButton inputButton = new JButton("Enter");
    JButton deleteButton = new JButton("Delete All");
    

    JPanel listPanel = new JPanel();


    ToDo()
    {
        inputScreen.setSize(300,80);
        inputScreen.setFont(new Font("sans serif", Font.PLAIN, 20));
        inputScreen.setBackground(new Color(0x123456));
        inputScreen.setBorder(BorderFactory.createLineBorder(new Color(0x123456),4));
        inputScreen.setForeground(Color.WHITE);
        inputScreen.setCaretColor(Color.white);
        inputScreen.addKeyListener(this);

        deleteButton.setSize(100,100);
        deleteButton.setBackground(new Color(0xaa3322));
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(this);
        deleteButton.setToolTipText("Delete All");

        inputButton.setSize(100,100);
        inputButton.setBackground(new Color(0x33aa22));
        inputButton.setFocusable(false);
        inputButton.setToolTipText("Enter Text");
        inputButton.addActionListener(this);

        inputPanel.add(inputScreen,BorderLayout.CENTER);
        inputPanel.add(inputButton,BorderLayout.EAST);
        inputPanel.add(deleteButton,BorderLayout.WEST);
        inputPanel.setBackground(backgroundColor);
        inputPanel.setBorder(BorderFactory.createLineBorder(backgroundColor, 5));

        listPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        listPanel.setBorder(BorderFactory.createLineBorder(backgroundColor, 3));
        listPanel.setBackground(backgroundColor);
        listPanel.setLayout(new GridLayout(11,1,2,3));
        listPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(listPanel, BorderLayout.CENTER);
        mainPanel.setBackground(backgroundColor);

        frame.setSize(600,600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }


    @Override
    public void keyTyped(KeyEvent e) {}


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 10){
            inputButton.doClick();
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {}


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == inputButton && !inputScreen.getText().isEmpty()){
            myField a = new myField(inputScreen.getText());
            a.addMouseListener(this);
            listPanel.add(a);
            frame.setVisible(true);
            inputScreen.setText("");
            return;
        }
        else if(e.getSource() == deleteButton) {
            listPanel.removeAll();
            listPanel.revalidate();
            listPanel.repaint();
            frame.setVisible(true);
            inputScreen.setText("");
            return;
        }
        
        
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        listPanel.remove(e.getComponent());
        listPanel.revalidate();
        listPanel.repaint();   
    }


    @Override
    public void mousePressed(MouseEvent e) {}


    @Override
    public void mouseReleased(MouseEvent e) {}


    @Override
    public void mouseEntered(MouseEvent e) {}


    @Override
    public void mouseExited(MouseEvent e) {}
    
}
class myField extends JTextField
{
    myField(String text)
    {
        this.setText(text);
        this.setEditable(false);
        this.setFont(new Font("Sans Serif", Font.PLAIN,18));
        this.setBackground(new Color(0x123456));
        this.setBorder(BorderFactory.createLineBorder(new Color(0x123456),5));
        this.setForeground(Color.WHITE);
    }

}

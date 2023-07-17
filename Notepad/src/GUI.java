import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class GUI implements ActionListener
{
    public static void main(String[] args) {
        new GUI(); 
    }

    JFrame window;
    JTextArea textArea;

    JScrollPane scrollPane;

    JMenuBar menuBar;
    JMenu File;
    JMenuItem iOpen, iSave, iSaveAs, iClose;

    JMenu Edit;
    JMenuItem iUndo, iRedo;

    JMenu Format;
    JMenuItem iWrap, iFont, iSize, iColor;

    GUI()
    {
        setupWindow();
        setupTextArea();
        setupMenuBar();

        window.setVisible(true);
    }
    public void setupWindow(){
        window = new JFrame("Notepad by RAJ");
        window.setSize(600, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
    }
    public void setupTextArea(){
        textArea = new JTextArea("");
        textArea.setFont(new Font("San's serif",Font.PLAIN,14));

        scrollPane  = new JScrollPane
            (textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setBorder(BorderFactory.createEmptyBorder(0,3,0,0));

        JScrollBar vBar = scrollPane.getVerticalScrollBar();    
        JScrollBar hBar = scrollPane.getHorizontalScrollBar();

        vBar.setPreferredSize(new Dimension(10, 0));
        vBar.setBackground(Color.lightGray);

        hBar.setPreferredSize(new Dimension(0, 10));
        hBar.setBackground(Color.lightGray);

        window.add(scrollPane);
    }
    
    public void setupMenuBar(){
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(222,222,222));
        menuBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        menuBar.setFont(new Font("San's serif", Font.PLAIN, 15));
        window.setJMenuBar(menuBar);

        File = new JMenu("File");
        Edit = new JMenu("Edit");
        Format = new JMenu("Format");
          
        File.setMnemonic('F');

        iOpen = new JMenuItem("Open");
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        iOpen.setMnemonic('O');
        
        iSave = new JMenuItem("Save");
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");
        iSave.setMnemonic('S');

        
        iSaveAs = new JMenuItem("Save As");
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("SaveAs");
        iSaveAs.setMnemonic('A');

        
        iClose = new JMenuItem("Close");
        iClose.addActionListener(this);
        iClose.setActionCommand("Close");
        iClose.setMnemonic('C');
        
        File.add(iOpen);
        File.add(iSave);
        File.add(iSaveAs);
        File.add(iClose);

        Edit.setMnemonic('E');

        iUndo = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        iUndo.setMnemonic('U');

        iRedo = new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        iRedo.setMnemonic('R');

        Edit.add(iUndo);
        Edit.add(iRedo);
        
        Format.setMnemonic('O');

        iWrap = new JMenuItem("Wrap : Off");
        iWrap.addActionListener(this);
        iWrap.setActionCommand("Wrap");
        iWrap.setMnemonic('W');

        iFont = new JMenuItem("Font");
        
        iSize = new JMenuItem("Size");

        iColor = new JMenuItem("Color");

        Format.add(iWrap);
        Format.add(iFont);
        Format.add(iSize);
        Format.add(iColor);

        menuBar.add(File);
        menuBar.add(Edit);
        menuBar.add(Format);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        // ToDo: Make menuBar Functional  
        switch (actionCommand) {
            case "Open":
                
                break;
            case "Save":
                
                break;
            case "SaveAs":
                
                break;
            case "Close": System.exit(0);
                break;        
            case "Undo":
                
                break;
            case "Redo":
                
                break;
            case "Wrap": 
                Boolean wrap = !iWrap.getText().equals("Wrap : Off");
                textArea.setWrapStyleWord(wrap);
                iWrap.setText(wrap ? "Wrap : On" : "Wrap : Off");
                break;
            
            default:
                break;
        }
    }
}
package Gui_Frames;

import java.awt.*;
import javax.swing.*;


public class MainFrame extends JFrame {
    public MainFrame(){
        super("Digital WaterMarking System");
        setSize(900, 700);
        setJMenuBar(createMenu());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JMenuBar createMenu(){
        var menubar = new JMenuBar();
        var fileMenu = new JMenu("Options");
        var exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        menubar.add(fileMenu);
        return menubar;
    }

    public void createFrame(JFrame oldFrame,MainFrame newFrame){
            Container contentPane = newFrame.getContentPane();
            contentPane.setBackground(Color.decode("#FFFAF4"));
            var menubar = new JMenuBar();
            var fileMenu = new JMenu("Options");
            var backItem = new JMenuItem("Back");
            var closeItem = new JMenuItem("Exit");
            closeItem.addActionListener(ex -> System.exit(0));
            backItem.addActionListener(ep -> {
                newFrame.dispose(); 
                oldFrame.setVisible(true); 
            });
            fileMenu.add(backItem);
            menubar.add(fileMenu);
            fileMenu.add(closeItem);
            newFrame.setJMenuBar(menubar);
            newFrame.setVisible(true);
    }
    
    void createFramelayout(JFrame prevframe,MainFrame newFrame)
    {
        newFrame.createFrame(prevframe, newFrame);
        newFrame.setContentPane(newFrame.getContentPane());
        newFrame.getContentPane().setLayout(new GridBagLayout());
    }
}

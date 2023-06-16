package Gui_Frames;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class MainPanel extends JPanel {
    public MainPanel(){
        setBackground(Color.decode("#FFFAF4"));
        
        var title = new JLabel("DIGITAL WATERMARKING SYSTEM");
        var start_but = createButton("Start");

        setLayout(new GridBagLayout());
        var gc = new GridBagConstraints();
        
        title.setForeground(Color.decode("#D25380"));
        title.setFont(new Font("Arial", Font.BOLD, 40));
    
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weighty = 0.1;
        add(title, gc);
    
        gc.gridx = 0;
        gc.gridy = 1;
        gc.weighty = 0.1;
        add(start_but, gc);
        
        start_but.addActionListener(e -> {
            final JFrame prevframe = (JFrame) SwingUtilities.getWindowAncestor(this);
            prevframe.setVisible(false);

            MainFrame newFrame = new MainFrame();
            newFrame.createFramelayout(prevframe,newFrame);

            var title1 = new JLabel("SELECT THE IMAGE");
            title1.setForeground(Color.decode("#D25380"));
            title1.setFont(new Font("Arial", Font.BOLD, 40));
        
            gc.gridx = 0;
            gc.gridy = 0;
            gc.weighty = 0.01;
            newFrame.getContentPane().add(title1,gc);
    
            var bor_but = createButton("Browse");
            gc.gridx = 0;
            gc.gridy = 1;
            gc.weighty = 0.01;
            newFrame.getContentPane().add(bor_but, gc);
            newFrame.setVisible(true);

            bor_but.addActionListener(e2 -> {
                SelectImage selectImage = new SelectImage();
                selectImage.selectImage(newFrame,gc);

            });

        });
    }

    public MainPanel(String p){};


    public JButton createButton(String name){
        var start_but = new JButton(name);
        start_but.setPreferredSize(new Dimension(200, 50));
        start_but.setBackground(Color.decode("#D25380"));
        start_but.setForeground(Color.decode("#FFFAF4"));
        start_but.setFont(new Font("Arial", Font.BOLD, 20));
        start_but.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        start_but.setContentAreaFilled(false);
        start_but.setOpaque(true);
        start_but.setFocusPainted(false);
        start_but.setCursor(new Cursor(Cursor.HAND_CURSOR));
        start_but.addActionListener(e -> {
            start_but.setBackground(Color.decode("#FFFAF4"));
            start_but.setForeground(Color.decode("#D25380"));
        });
        start_but.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                start_but.setBackground(Color.decode("#FFFAF4"));
                start_but.setForeground(Color.decode("#D25380"));
            }

            public void mouseExited(MouseEvent e) {
                start_but.setBackground(Color.decode("#D25380"));
                start_but.setForeground(Color.decode("#FFFAF4"));
            }
        });
        return start_but;
    }
    
    String waterimgPreview(MainFrame textFrame,JTextField text){
        String textString = text.getText();
        return textString;
    }
    
    String getOutFile(String path,String name){
        int pos = name.lastIndexOf(".");
        if (pos > 0) {
            name = name.substring(0, pos);
        }
        String outputimg = path.replace(name, name + "_watermarkedimg");
        return outputimg;
    }

    void previewWatermark(MainFrame textFrame,MainFrame colorFrame,JTextField text,String outFile,GridBagConstraints gc){
        var title4 = new JLabel("Preview Watermark Image");
        title4.setForeground(Color.decode("#D25380"));
        title4.setFont(new Font("Arial", Font.BOLD, 40));
        gc.gridx = 0;
        gc.gridy = -3;
        gc.weighty = 0.01;
        colorFrame.getContentPane().add(title4, gc);

        SelectImage si = new SelectImage();
        
        var wmimg = si.resize(outFile);
        colorFrame.getContentPane().add(wmimg,gc);

        var back_but3 = createButton("Back");
        var done_but = createButton("Done");

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel1.add(back_but3);
        panel1.add(done_but);
        panel1.setBackground(Color.decode("#FFFAF4"));
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gc.gridx = 0;
        gc.gridy = 10;
        gc.weighty = 0.01;
        colorFrame.getContentPane().add(panel1, gc);

        back_but3.addActionListener(e6 -> {
            colorFrame.setVisible(false);
            textFrame.setVisible(true);
        });

        //done_but action
        done_but.addActionListener(e7 -> {
            colorFrame.setVisible(false);
            JOptionPane.showMessageDialog(null, "Watermark Image Saved Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }); 

    }
}

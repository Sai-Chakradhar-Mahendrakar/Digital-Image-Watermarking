package Gui_Frames;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SelectImage {

    int width, height;
    String watermarkString, inFile, outFile,hexColor;

    MainPanel p = new MainPanel("test");
    Watermark wm = new Watermark();

    public String selectImage(MainFrame newFrame, GridBagConstraints gc) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            this.inFile = selectedFile.getAbsolutePath();
            newFrame.setVisible(false);
            PreviewImage(newFrame, gc, inFile);
            String name = selectedFile.getName();
            this.outFile = p.getOutFile(inFile, name);
            return inFile;
        }
        return null;
    }

    void PreviewImage(MainFrame newFrame, GridBagConstraints gc, String path) {
        MainFrame previewFrame = new MainFrame();
        previewFrame.createFramelayout(newFrame, previewFrame);

        var title2 = new JLabel("SELECTED IMAGE");
        title2.setForeground(Color.decode("#D25380"));
        title2.setFont(new Font("Arial", Font.BOLD, 40));
        gc.gridx = 0;
        gc.gridy = -3;
        gc.weighty = 0.01;
        previewFrame.getContentPane().add(title2, gc);

        var img = resize(path);
        previewFrame.getContentPane().add(img, gc);

        var next_but = p.createButton("Next");
        var back_but = p.createButton("Back");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(back_but);
        panel.add(next_but);
        panel.setBackground(Color.decode("#FFFAF4"));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gc.gridx = 0;
        gc.gridy = 10;
        gc.weighty = 0.01;
        previewFrame.getContentPane().add(panel, gc);

        back_but.addActionListener(e3 -> {
            previewFrame.setVisible(false);
            newFrame.setVisible(true);
        });

        next_but.addActionListener(e2 -> {
            previewFrame.setVisible(false);
            MainFrame textFrame = new MainFrame();
            textFrame.createFramelayout(previewFrame, textFrame);

            var title3 = new JLabel("Enter Watermark Text");
            title3.setForeground(Color.decode("#D25380"));
            title3.setFont(new Font("Arial", Font.BOLD, 40));
            gc.gridx = 0;
            gc.gridy = 0;
            gc.weighty = 0.01;
            textFrame.getContentPane().add(title3, gc);

            JTextField text = new JTextField(15);
            text.setFont(new Font("Arial", Font.BOLD, 40));
            gc.gridx = 0;
            gc.gridy = 1;
            gc.weighty = 0.01;
            textFrame.getContentPane().add(text, gc);

            JTextArea color_chosed = new JTextArea();
            color_chosed.setFont(new Font("Arial", Font.BOLD, 40));
            color_chosed.setEditable(false);
            gc.gridx = 1;
            gc.gridy = 1;
            gc.weighty = 0.01;
            textFrame.getContentPane().add(color_chosed,gc);

            var color_picker = p.createButton("Select Color");
            gc.gridx = 0;
            gc.gridy = 2;
            gc.weighty = 0.01;
            textFrame.getContentPane().add(color_picker, gc);

            //actionListener to pick color
            color_picker.addActionListener(e -> {
                Color color = JColorChooser.showDialog(null, "Pick a Color", Color.BLACK);
                if (color != null) {
                    color_chosed.setBackground(color);
                    color_chosed.setText("   ");
                    //setborder 
                    color_chosed.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    hexColor = "#" + Integer.toHexString(color.getRGB()).substring(2);
                }
            });

            
            var next_but2 = p.createButton("Next");
            gc.gridx = 0;
            gc.gridy = 3;
            gc.weighty = 0.01;
            textFrame.getContentPane().add(next_but2, gc);

            text.addActionListener(e3 -> {
                textFrame.setVisible(false);
                this.watermarkString = p.waterimgPreview(textFrame, text);
                this.outFile = wm.imgWatermark(this.inFile, this.outFile, this.watermarkString,this.hexColor);
                MainFrame colorFrame = new MainFrame();
                colorFrame.createFramelayout(textFrame, colorFrame);
                p.previewWatermark(textFrame, colorFrame, text, outFile, gc);
            });

            var back_but2 = p.createButton("Back");
            gc.gridx = 0;
            gc.gridy = 4;
            gc.weighty = 0.01;
            textFrame.getContentPane().add(back_but2, gc);

            back_but2.addActionListener(e4 -> {
                textFrame.setVisible(false);
                previewFrame.setVisible(true);
            });

            next_but2.addActionListener(e5 -> {
                textFrame.setVisible(false);
                this.watermarkString = p.waterimgPreview(textFrame, text);
                this.outFile = wm.imgWatermark(this.inFile, this.outFile, this.watermarkString,this.hexColor);
                MainFrame colorFrame = new MainFrame();
                colorFrame.createFramelayout(textFrame, colorFrame);
                p.previewWatermark(textFrame, colorFrame, text, outFile, gc);
            });
        });
    }

    public JLabel resize(String path) {
        ImageIcon icon = new ImageIcon(path);
        this.width = icon.getIconWidth();
        this.height = icon.getIconHeight();

        if (width > 500 || height > 500) {
            if (width > height) {
                height = (height * 500) / width;
                width = 500;
            } else {
                width = (width * 500) / height;
                height = 500;
            }
        }

        JLabel img = new JLabel();
        img.setBounds(300, 300, width, height);
        img.setIcon(ResizeImage(path, img));
        return img;
    }

    public ImageIcon ResizeImage(String ImagePath, JLabel label) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
}
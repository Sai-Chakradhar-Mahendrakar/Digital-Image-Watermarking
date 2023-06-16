package Gui_Frames;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Watermark {
    
    String imgWatermark(String imagePath,String outputimg,String waterTxt,String hex){
        int initialFontSize = 1000;
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));

            java.awt.Graphics2D graphics = image.createGraphics();
            Font font = new Font("Arial", Font.BOLD, initialFontSize);
            Color color = Color.decode(hex);
            color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 80);

            int fontSize = calculateMaxFontSize(image, font, waterTxt);

            font = font.deriveFont(Font.BOLD, fontSize);
            graphics.setFont(font);
            graphics.setColor(color);

            FontMetrics fontMetrics = graphics.getFontMetrics();
            int x = (image.getWidth() - fontMetrics.stringWidth(waterTxt)) / 2;
            int y = (image.getHeight() + fontMetrics.getAscent()) / 2;

            graphics.drawString(waterTxt, x, y);

            graphics.dispose();

            ImageIO.write(image, "jpg", new File(outputimg));


        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        return outputimg;
    }


    int calculateMaxFontSize(BufferedImage image, Font font, String watermarkText) {
        int fontSize = font.getSize();
        FontMetrics fontMetrics = image.createGraphics().getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth(watermarkText);
        int textHeight = fontMetrics.getHeight();

        while (textWidth > image.getWidth() || textHeight > image.getHeight()) {
            fontSize--;
            font = font.deriveFont(Font.BOLD, fontSize);
            fontMetrics = image.createGraphics().getFontMetrics(font);
            textWidth = fontMetrics.stringWidth(watermarkText);
            textHeight = fontMetrics.getHeight();
        }

        return fontSize;
    }
}

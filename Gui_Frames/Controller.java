package Gui_Frames;

public class Controller {
    private MainFrame mainFrame;
    private MainPanel mainPanel;
    public Controller()
    {
        mainFrame = new MainFrame();
        mainPanel = new MainPanel();
        mainFrame.setContentPane(mainPanel);
        mainFrame.setVisible(true);
    }
}
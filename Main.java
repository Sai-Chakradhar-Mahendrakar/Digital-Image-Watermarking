import Gui_Frames.*;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] arg){
        SwingUtilities.invokeLater(new Runnable() {
            	public void run() {
            		new Controller();
            	}
            });
    }
}

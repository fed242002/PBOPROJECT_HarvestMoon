package Main;
import javax.swing.JFrame;


//tes
public class App {
    public static void main(String[] args) throws Exception {
        MapDB listMap = new MapDB(); 
        JFrame window = new JFrame("nanem Bulan");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel(); 
        window.add(gamePanel); 

        window.pack(); 

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        

        gamePanel.setupGame(); 
        gamePanel.startGameThread(); // Start the game thread after the window is visible
            
    
    }
}

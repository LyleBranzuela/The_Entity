/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GameEntities.Player;
import MenuPanels.PanelManager;
import Stages.Stage;
import Stages.Stage_1;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Main driver of the game.
 * 
 * @author lyleb and khoap
 */
public class MainDriver 
{
    /**
     * Main driver of the game.
     * 
     * @param args 
         */
    
    public static JFrame frame = new JFrame("The Entity");
    
    public static void main(String[] args)
    {
        // Connect to the To the PlayerDatabase();
        DatabaseManager.connectToPlayerDatabase();
        DatabaseManager.createPlayerSaveDatabase();
        DatabaseManager.savePlayerToDatabase(new Player("Lyle"), false);
        // DatabaseManager.savePlayerToDatabase(new Player("Cody"), true);

        // Instantiate Panel Manager
        PanelManager myPanel = new PanelManager();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        frame.getContentPane().add(myPanel);
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(new Point((d.width / 2) - (frame.getWidth() / 2), (d.height / 2) - (frame.getHeight() / 2)));
        frame.setVisible(true);
  
    }

}

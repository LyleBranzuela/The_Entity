/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GameEntities.Player;
import MenuPanels.PanelManager;
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
    public static void main(String[] args)
    {
        // Connect to the Database
        DatabaseManager.connectToPlayerDatabase();
        DatabaseManager.createPlayerSaveDatabase();
        DatabaseManager.savePlayerToDatabase(new Player("John"));
        
        // Instantiate Panel Manager
        PanelManager myPanel = new PanelManager();
        JFrame frame = new JFrame("The Entity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(myPanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(new Point((d.width / 2) - (frame.getWidth() / 2), (d.height / 2) - (frame.getHeight() / 2)));
        frame.setVisible(true);
    }

}

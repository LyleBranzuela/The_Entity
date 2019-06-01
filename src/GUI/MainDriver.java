/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GameEntities.Player;
import MenuPanels.PanelManager;
import Stages.Stage_1;
import Stages.Stage_2;
import Stages.Stage_3;
import Stages.Stage_4;
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
        // Connect to the To the PlayerDatabase();
        DatabaseManager.connectToPlayerDatabase();
        DatabaseManager.createPlayerSaveDatabase();

        // Instantiate Panel Manager
        PanelManager myPanel = new PanelManager();
        JFrame frame = new JFrame("The Entity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myPanel.setPreferredSize(new Dimension(1000,600));
        frame.getContentPane().add(myPanel);
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(new Point((d.width / 2) - (frame.getWidth() / 2), (d.height / 2) - (frame.getHeight() / 2)));
        frame.setVisible(true);
        
        UtilityMethods utils = new UtilityMethods();
        utils.playSoundtrack("sound/Ambient_Spooky_Music.wav", 300);
    }

}

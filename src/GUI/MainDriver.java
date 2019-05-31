/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GameEntities.Player;
import MenuPanels.PanelManager;
import Stages.*;
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
        DatabaseManager.savePlayerToDatabase(new Player("Lyle"), false);
        // DatabaseManager.savePlayerToDatabase(new Player("Cody"), true);

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
        utils.playSoundtrack("sound\\Ambient_Spooky_Music.wav", 300);
//        Player TestPlayer1stStage = new Player("TestPlayer1stStage");
//        TestPlayer1stStage.setCurrentStageLevel(new Stage_1());
//        Player TestPlayer2ndStage = new Player("TestPlayer2ndStage");
//        TestPlayer2ndStage.setCurrentStageLevel(new Stage_2());
//        Player TestPlayer3rdStage = new Player("TestPlayer3rdStage");
//        TestPlayer3rdStage.setCurrentStageLevel(new Stage_3());
//        Player TestPlayer4thStage = new Player("TestPlayer4thStage");
//        TestPlayer4thStage.setCurrentStageLevel(new Stage_4());
//
//        DatabaseManager.savePlayerToDatabase(TestPlayer1stStage, false);
//        DatabaseManager.savePlayerToDatabase(TestPlayer2ndStage, false);
//        DatabaseManager.savePlayerToDatabase(TestPlayer3rdStage, false);
//        DatabaseManager.savePlayerToDatabase(TestPlayer4thStage, false);
    }

}

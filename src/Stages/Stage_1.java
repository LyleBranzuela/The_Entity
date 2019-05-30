/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stages;

import GUI.UtilityMethods;
import GameEntities.Player;
import MenuPanels.PanelManager;
import static MenuPanels.PanelManager.menuCardPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class for the Stage 1 of the Game. Password by the riddle that needs to be
 * inputted by the user.
 *
 * Player input a number to unlock a padlock to escape their cell
 *
 * @author lyleb and khoap
 */
public class Stage_1 extends Stage
{
                                  
    public int xcoord, ycoord;
    
    /**
     * 
     * @param player 
     */
    public Stage_1(Player player)
    {
        super(player);
        this.xcoord = 30;
        this.ycoord = 60;
        this.isCompleted = false;
    }
    
    /**
     * Create the content for stage 1.
     * 
     * @param player current player playing;
     * @param g
     */
    @Override
    public void initiateStage(Player player, Graphics g) {

//        UtilityMethods utility = new UtilityMethods();
//
//        int firstDigit = UtilityMethods.randNum(10);
//        int secondDigit = UtilityMethods.randNum(10);
//        int thirdDigit = UtilityMethods.randNum(10);
        draw(g);

    }
    
    @Override
    public void draw(Graphics g) {
        
            int index = 0;
           
            
            
            g.setColor(Color.BLACK);
            g.clearRect(0, 0, 1000, 600);
            ImageIcon image = new ImageIcon("c:\\Users\\Khoa Pham\\Desktop\\Entity\\background\\Stage1_Prison.png");
            
            
            image.paintIcon(this, g, 0, 0);

//            
    }
}

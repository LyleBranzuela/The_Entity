/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stages;

import GameEntities.Player;
import MenuPanels.PanelManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

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
    public boolean isCompleted;
    public Player currentPlayer;
    private DrawingPanel drawingPanel;
    private int i;

    /**
     *
     */
    public Stage_1()
    {
        super();
        super.stageLevel = 1;
        
        this.drawingPanel = new DrawingPanel();
        this.drawingPanel.setBackground(Color.BLACK);
        this.drawingPanel.setFocusable(true);
        updateStagePlayer();

        add(this.drawingPanel);
    }

    /**
     * 
     */
    public void updateStagePlayer()
    {
        this.currentPlayer = PanelManager.getCurrentPlayer();
        this.isCompleted = false;
        this.i = 0;
    }

    /**
     * A separate JPanel meant to handle all the painting and drawing of the
     * component.
     */
    private class DrawingPanel extends JPanel implements ActionListener
    {
        private Timer timer = new Timer(4000, this);
        
        public String Stage1_story[] =
        {
            "An evil entity appeared 10 years ago, anyone seeing this Entity becomes either...",
            "...a mindless slave or an insane person who would commit suicide immediately.",
            "You were rescued by a group of blind survivors when you ten years old.",
            "Taught by the group to see with sounds, you managed to survive the apocalypse...",
            "Until one day you were captured by a group of slaves...",
            "Waking up in a prison cell, you must now look for a way to escape..."
        };

        public DrawingPanel()
        {
            super();

        }

        /**
         * Draws all the components of the drawing panel
         *
         * @param g specifies the current graphic space the panel is using.
         */
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            if (i < 5)
            {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Tahoma", Font.PLAIN, 14));
                g.drawString(Stage1_story[i], 30, 280);
            }
            else
            {
                g.setColor(Color.BLACK);
                g.clearRect(0, 0, 1000, 600);
                Image image = Toolkit.getDefaultToolkit().getImage("background/Stage1_Prison.png");
                g.drawImage(image, 0, 0, this);
            }
            timer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (i < 5)
            {
                i++;
                repaint();
            }
            else
            {
                //Do nothing
            }
        }
    }
    
}

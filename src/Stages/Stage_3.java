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
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Class for the Stage 3 of the Game.
 *
 * @author lyleb and khoap
 */
public class Stage_3 extends Stage
{

    private Player currentPlayer;
    public boolean isCompleted;
    private DrawingPanel drawingPanel;
    private int i;

    public Stage_3()
    {
        super();
        super.stageLevel = 3;

        this.drawingPanel = new DrawingPanel();
        this.drawingPanel.setBackground(Color.BLACK);
        this.drawingPanel.setFocusable(true);
        updateStagePlayer();

        add(this.drawingPanel);
    }

    @Override
    public void updateStagePlayer()
    {
        this.currentPlayer = PanelManager.getCurrentPlayer();
        this.isCompleted = false;
        this.i = 0;
    }

    private class DrawingPanel extends JPanel implements ActionListener
    {

        private Timer timer = new Timer(5000, this);

        public String Stage3_story[] =
        {
            "After avoiding all the guards, your hear the Entity's howl.",
            "You found a room where the police used to store confiscated weapons...",
            "This is the perfect opportunity to kill the Entity, you need to arm yourself.",
            "The door is locked. Use the clips to break in and find a weapon!",
            "Lockpick successful. You open the door to find a room full of guns.",
            "Knowing the guns in this room will do nothing because of the entity's power... ",
            "...you dig around to find some melee weapons for a fight without vision...",
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
            if (i < 6)
            {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Tahoma", Font.BOLD, 14));
                g.drawString(Stage3_story[i], 30, 280);
            }
            else
            {
                Image image = Toolkit.getDefaultToolkit().getImage("background/Stage1_Prison.png");
                g.drawImage(image, 0, 0, this);
            }
            timer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (i < 6)
            {
                i++;
                repaint();
            }

        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stages;

import GUI.DesignAttributes;
import GUI.UtilityMethods;
import GameEntities.Player;
import Items.Bat;
import Items.Daggers;
import Items.Machete;
import MenuPanels.PanelManager;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 * Class for the Stage 3 of the Game.
 *
 * @author lyleb and khoap
 */
public class Stage_3 extends Stage
{

    private Player currentPlayer;
    public boolean lockpickCompleted, stageCompleted;
    private DrawingPanel drawingPanel;
    private UtilityMethods utils = new UtilityMethods();
    private int k, i, lock1, lock2, lock3, unlock1, unlock2, unlock3;
    private JButton section1, section2, section3, batButton, dagButton, macButton;
    private DesignAttributes designAttributes;
    /**
     * Constructor for Stage 3.
     */
    public Stage_3()
    {
        super();
        super.stageLevel = 3;

        this.designAttributes = new DesignAttributes();
        this.drawingPanel = new DrawingPanel();
        this.drawingPanel.setBackground(Color.BLACK);
        this.drawingPanel.setFocusable(true);
        
        section1 = UtilityMethods.generateButton("Section 1", 11,
                designAttributes.primaryColor, designAttributes.tertiaryColor, true);
        section1.addActionListener(this.drawingPanel);
        
        section2 = UtilityMethods.generateButton("Section 2", 11,
                designAttributes.primaryColor, designAttributes.tertiaryColor, true);
        section2.addActionListener(this.drawingPanel);
        
        section3 = UtilityMethods.generateButton("Section 3", 11,
                designAttributes.primaryColor, designAttributes.tertiaryColor, true);
        section3.addActionListener(this.drawingPanel);
        
        Image bat = Toolkit.getDefaultToolkit().getImage("items/Baseball_Bat.png");
        Image dualDag = Toolkit.getDefaultToolkit().getImage("items/Dual_Daggers.png");
        Image machete = Toolkit.getDefaultToolkit().getImage("items/Machete.jpg");
                
        batButton = new JButton(new ImageIcon(bat));
        batButton.setOpaque(false);
        batButton.setContentAreaFilled(false);
        batButton.setBorderPainted(false);
        batButton.addActionListener(this.drawingPanel);
        
        dagButton = new JButton(new ImageIcon(dualDag));
        dagButton.setOpaque(false);
        dagButton.setContentAreaFilled(false);
        dagButton.setBorderPainted(false);
        dagButton.addActionListener(this.drawingPanel);
        
        macButton = new JButton(new ImageIcon(machete));
        macButton.setOpaque(false);
        macButton.setContentAreaFilled(false);
        macButton.setBorderPainted(false);
        macButton.addActionListener(this.drawingPanel);
        
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Escape");
        // Customized Action for pressing Escape
        Action escapeAction = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
                cl.show(PanelManager.menuCardPanel, "MIDGAMEMENU");
            }
        };
        
        // Adding an escape action map to the action map
        this.getActionMap().put("Escape", escapeAction);
        
        updateStagePlayer();

        add(this.drawingPanel);
    }

    @Override
    public void updateStagePlayer()
    {
        this.currentPlayer = PanelManager.getCurrentPlayer();
        this.lockpickCompleted = false;
        this.stageCompleted = false;
        this.i = 0;
        this.k = 3;
        this.unlock1 = 0;
        this.unlock2 = 0;
        this.unlock3 = 0;
        this.lock1 = UtilityMethods.randNum(3) + 1;
        this.lock2 = UtilityMethods.randNum(3) + 1;
        this.lock3 = UtilityMethods.randNum(3) + 1;
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
            timer.start();
            if (i < 4)
            {
                g.setColor(Color.WHITE);
                if (i == 1)
                {
                    utils.playSoundtrack("sound/Monster_Howl_1.wav", 5);
                }
                g.setFont(new Font("Tahoma", Font.BOLD, 18));
                g.drawString(Stage3_story[i], 30, 280);
            }
            else
            {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 1000, 600);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Tahoma", Font.BOLD, 20));
                g.drawString("Push the right amount of pins in each section with the clips to unlock door!", 130, 400);  
                
                section1.setBounds(250,250,100,50);
                section2.setBounds(450,250,100,50);
                section3.setBounds(650,250,100,50);
                
                add(section1);
                add(section2);
                add(section3);
                
                section1.setOpaque(true);
                section1.setContentAreaFilled(true);
                section1.setBorderPainted(true);

            }
            
            if(lockpickCompleted == true)
            {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 1000, 600);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Tahoma", Font.BOLD, 20));
                g.drawString("Lockpick successful! You dig around to find something for a fight without vision:", 80, 100);
                g.drawString("You have found: ", 400, 150);
                
                
                batButton.setLocation(220, 210);
                dagButton.setLocation(420, 210);
                macButton.setLocation(620, 210);
                
                add(batButton);
                add(dagButton);
                add(macButton);
                
                batButton.setOpaque(true);
                batButton.setContentAreaFilled(true);
                batButton.setBorderPainted(true);
                
                macButton.setOpaque(true);
                macButton.setContentAreaFilled(true);
                macButton.setBorderPainted(true);
                
                dagButton.setOpaque(true);
                dagButton.setContentAreaFilled(true);
                dagButton.setBorderPainted(true);                
            }
            
            if(stageCompleted == true)
            {
                currentPlayer.setCurrentStageLevel(new Stage_4());
                PanelManager.setCurrentPlayer(currentPlayer);
                PanelManager.changeToStagePanel();
            }
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            Object source = e.getSource();
            if (i < 4)
            {
                i++;
                repaint();
            }
            
            if(source == section1 )
            {
                if(unlock1 < lock1)
                {
                    utils.playSoundtrack("sound/Lockpick_Failed.wav", 2);
                    unlock1++;
                }
                else if(unlock1 == lock1)
                {
                    utils.playSoundtrack("sound/Lockpick_Success.wav", 2);
                    section1.setVisible(false);
                    section2.setOpaque(true);
                    section2.setContentAreaFilled(true);
                    section2.setBorderPainted(true);
                }
            }
            else if(source == section2 )
            {
                if(unlock2 < lock2)
                {
                    utils.playSoundtrack("sound/Lockpick_Failed.wav", 2);
                    unlock2++;
                }
                else if(unlock2 == lock2)
                {
                    utils.playSoundtrack("sound/Lockpick_Success.wav", 2);
                    section2.setVisible(false);
                    section3.setOpaque(true);
                    section3.setContentAreaFilled(true);
                    section3.setBorderPainted(true);
                }
            }
            else if(source == section3 )
            {
                if(unlock3 < lock3)
                {
                    utils.playSoundtrack("sound/Lockpick_Failed.wav", 2);
                    unlock3++;
                }
                else if(unlock3 == lock3)
                {
                    utils.playSoundtrack("sound/Lockpick_Success.wav", 2);
                    section3.setVisible(false);
                    lockpickCompleted = true; 
                    repaint();
                }
            }
            else if(source == batButton)
            {
                Bat baseballBat = new Bat();
                currentPlayer.pickupItem(baseballBat);
                stageCompleted = true;
                repaint();
            }
            
            else if(source == dagButton)
            {
                Daggers saiDag = new Daggers();
                currentPlayer.pickupItem(saiDag);
                stageCompleted = true;
                repaint();
            }
            
            else if(source == macButton)
            {
                Machete machete = new Machete();
                currentPlayer.pickupItem(machete);
                stageCompleted = true;
                repaint();
            }
        }
    }

}

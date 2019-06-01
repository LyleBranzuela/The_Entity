/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stages;

import GUI.DesignAttributes;
import GUI.UtilityMethods;
import GameEntities.Player;
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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
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
    private boolean clueFound;
    public Player currentPlayer;
    private DrawingPanel drawingPanel;
    private int i, lock1, lock2, lock3, unlock1, unlock2, unlock3;
    private UtilityMethods utils = new UtilityMethods();
    private DesignAttributes designAttributes;
    private JButton clue, section1, section2, section3;
    
    /**
     *
     */
    public Stage_1()
    {
        super();
        super.stageLevel = 1;
        designAttributes = new DesignAttributes();
        
        this.drawingPanel = new DrawingPanel();
        this.drawingPanel.setBackground(Color.BLACK);
        this.drawingPanel.setFocusable(true);
        
        clue = new JButton();
        clue.setOpaque(false);
        clue.setContentAreaFilled(false);
        clue.setBorderPainted(false);
        clue.setBounds(50, 50, 220, 250);
        clue.addActionListener(this.drawingPanel);
        
        // Set up the JButtons
        section1 = new JButton();
        section2 = new JButton();
        section3 = new JButton();
        
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
        
        add(this.clue);
        add(this.drawingPanel);
        
    }

    /**
     * 
     */
    @Override
    public void updateStagePlayer()
    {
        this.currentPlayer = PanelManager.getCurrentPlayer();
        this.isCompleted = false;
        this.clueFound = false;
        this.i = 0;
        this.unlock1 = 0;
        this.unlock2 = 0;
        this.unlock3 = 0;
        this.lock1 = UtilityMethods.randNum(3) + 1;
        this.lock2 = UtilityMethods.randNum(3) + 1;
        this.lock3 = UtilityMethods.randNum(3) + 1; 
        
        drawingPanel.remove(section1);
        section1 = UtilityMethods.generateButton("Section 1", 11,
                designAttributes.primaryColor, designAttributes.tertiaryColor, true);
        section1.setBounds(500, 50, 250, 300);
        section1.addActionListener(this.drawingPanel);
        
        drawingPanel.remove(section2);
        section2 = UtilityMethods.generateButton("Section 2", 11,
                designAttributes.primaryColor, designAttributes.tertiaryColor, true);
        section2.setBounds(50, 50, 250, 300);
        section2.addActionListener(this.drawingPanel);
        
        drawingPanel.remove(section3);
        section3 = UtilityMethods.generateButton("Section 3", 11,
                designAttributes.primaryColor, designAttributes.tertiaryColor, true);
        section3.setBounds(50, 50, 250, 300);
        section3.addActionListener(this.drawingPanel);
    }

    /**
     * A separate JPanel meant to handle all the painting and drawing of the
     * component.
     */
    private class DrawingPanel extends JPanel implements ActionListener
    {
        private Timer timer = new Timer(3500, this);
        
        public String Stage1_story[] =
        {
            "An alien entity appeared 10 years ago, anyone seeing this Entity becomes either...",
            "...a mindless slave or an insane person who would commit suicide.",
            "You were rescued by a group of blind survivors when you were ten years old.",
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
            timer.start();
           
            if (i < 6) 
            {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Tahoma", Font.BOLD, 18));
                g.drawString(Stage1_story[i], 30, 280);
            } 
            else 
            {
                g.setColor(Color.BLACK);
                g.clearRect(0, 0, 1000, 600);
                Image image = Toolkit.getDefaultToolkit().getImage("background/Stage1_Prison.png");
                g.drawImage(image, 0, 0, this);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Tahoma", Font.BOLD, 20));
                g.drawString("OBJECTIVE: Search the room and find a way to escape!", 250, 70);
            }

            if (clueFound == true) 
            {
                Image image = Toolkit.getDefaultToolkit().getImage("background/Door_Wallpaper.jpg");
                g.drawImage(image, 0, 0, this);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Tahoma", Font.BOLD, 20));
                g.drawString("You've found some clips hidden behind that brick! Perfect for picking locks!", 130, 150);
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
            
            if (isCompleted)
            {
                currentPlayer.setCurrentStageLevel(new Stage_2());
                PanelManager.setCurrentPlayer(currentPlayer);
                PanelManager.changeToStagePanel();
            }
        }

        /**
         * Listens for events from the JPanel
         * 
         * @param e event that happened.
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Object source = e.getSource();
            
           // Print the text cutscene
            if (i < 6)
            {
                i++;
            }
            repaint();
            
            // Check what clue is found, and what to unlock
            if(source == clue)
            {
                clueFound = true;
            }
            else if(source == section1 )
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
            // final section
            else if(source == section3)
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
                    isCompleted = true;
                }
            }
        }
    }
    
}

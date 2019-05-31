/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stages;

import GUI.DesignAttributes;
import GUI.UtilityMethods;
import GameEntities.*;
import Items.Blindfold;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 * Class for the Stage 4 of the Game.
 *
 * @author lyleb and khoap
 */
public class Stage_4 extends Stage 
{

    private Player currentPlayer;
    private DrawingPanel drawingPanel;
    private JButton yesOption, noOption, blockButton, attackButton;
    private boolean wearingBlindfold, attackBlocked;
    private DesignAttributes designAttributes;
    private int i, step;
    private long blockTime, startOfAttack;
    Monster monster;

    /**
     * Constructor for creating Stage 4.
     */
    public Stage_4()
    {
        super();
        super.stageLevel = 4;
        this.designAttributes = new DesignAttributes();
        
        this.drawingPanel = new DrawingPanel();
        this.drawingPanel.setBackground(Color.BLACK);
        this.drawingPanel.setFocusable(true);

        // Setup input map
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
        this.getActionMap().put("Escape", escapeAction);

        
        // Yes Option
        yesOption = UtilityMethods.generateButton("Yes", 16,
                designAttributes.primaryColor, designAttributes.tertiaryColor, false);
        yesOption.addActionListener(this.drawingPanel);
        yesOption.setLocation(400, 300);
        yesOption.setSize(100, 50);
        
        
        // No Option
        noOption = UtilityMethods.generateButton("No", 16,
                designAttributes.primaryColor, designAttributes.tertiaryColor, false);
        noOption.addActionListener(this.drawingPanel);
        noOption.setLocation(700, 300);
        noOption.setSize(100, 50);
        
        blockButton = new JButton("Block");
        blockButton.setOpaque(true);
        blockButton.setContentAreaFilled(true);
        blockButton.setBorderPainted(true);
        blockButton.addActionListener(this.drawingPanel);
        
        attackButton = new JButton("Attack");
        attackButton.setOpaque(true);
        attackButton.setContentAreaFilled(true);
        attackButton.setBorderPainted(true);
        attackButton.addActionListener(this.drawingPanel);

        
        updateStagePlayer();
        this.drawingPanel.add(yesOption);
        this.drawingPanel.add(noOption);
        add(this.drawingPanel);
        
         
    }

    /**
     * Updates the stage player to initialize stage.
     */
    @Override
    public void updateStagePlayer()
    {
        this.monster = new Monster("Monster");
        this.attackBlocked = true;
        this.i = 0;
        this.step = 0;
        this.blockTime = 0;
        this.currentPlayer = PanelManager.getCurrentPlayer();
        this.currentPlayer.pickupItem(new Blindfold());
        this.wearingBlindfold = false;
        this.yesOption.setVisible(false);
        this.noOption.setVisible(false);
        this.blockButton.setVisible(true);
        this.attackButton.setVisible(true);
        
        repaint();
    }
    
    /**
     * A separate JPanel meant to handle all the painting and drawing of the
     * component.
     */
    private class DrawingPanel extends JPanel implements ActionListener
    {
        private Timer timer = new Timer(5000, this);

        public String Stage4_story[] =
        {
            "Following the Entity's howl to a room, you get ready to engage it...",
            "The Entity will howl each time it attacks...",
            "Block all attacks and find opportunities to strike it down...",
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
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Tahoma", Font.BOLD, 20));
            g.drawString("Actions", 460, 470);
            
            blockButton.setLocation(400, 500);
            blockButton.setSize(100, 35);
            
            attackButton.setLocation(500, 500);
            attackButton.setSize(100, 35);
            
            add(blockButton);
            add(attackButton);
            
            switch (step) {
                case 0:
                    if (i < 3) {
                        g.setColor(Color.WHITE);
                        
                        g.setFont(new Font("Tahoma", Font.BOLD, 16));
                        
                        g.drawString(Stage4_story[i], 30, 280);
                    } else if (i >= 3) {
                        step++;
                    }   break;
                case 1:
                    if (!wearingBlindfold) {
                        g.setColor(Color.WHITE);
                        g.setFont(new Font("Tahoma", Font.BOLD, 20));
                        g.drawString("Wear the blindfold?", 400, 70);
                        yesOption.setVisible(true);
                        noOption.setVisible(true);
                    } else {
                        
                        
                    }   break;
                case 2:
                    // Set player's health to 0 if attack is unblocked.
                    
                    //TODO: FIX LOOPING ON MONSTER ATTACK
                    blockButton.setVisible(true);
                    attackButton.setVisible(true);
                    startOfAttack = System.currentTimeMillis();
                    monster.attack();
                    if (attackBlocked == true) {
                        g.setFont(new Font("Tahoma", Font.BOLD, 16));
                        g.drawString("You blocked the Entity's attack. Finish it now!", 30, 280);
                    }
                    else 
                    {
                         currentPlayer.setHealth(0);
                    }
                        
                      break;
                default:
                    break;
            }
                
            // Victory screen if monster dies
            if (monster.getHealth() <= 0) {
                blockButton.setVisible(false);
                attackButton.setVisible(false);
                noOption.setVisible(false);
                yesOption.setVisible(false);
                Image image = Toolkit.getDefaultToolkit().getImage("background/Monster_And_Stage_Combined.jpg");
                g.drawImage(image, 0, 0, this);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Tahoma", Font.PLAIN, 20));
                g.drawString("You have defeated the monster. All slaves return to normal and the apocalypse is over.", 150, 100);
            } 
            
            // Losing screen if player dies.
            else if (currentPlayer.getHealth() <= 0) {
                CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
                cl.show(PanelManager.menuCardPanel, "GAMEOVERSCREEN");
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            Object source = e.getSource();
            if(i < 3)
            {
                i++;
            }
            repaint();
            
            if (currentPlayer.hasBlindfold) {
                if (source == yesOption) {
                    wearingBlindfold = true;
                    yesOption.setVisible(false);
                    noOption.setVisible(false);
                    step++;
                } else if (source == noOption) {
                    wearingBlindfold = false;
                    yesOption.setVisible(false);
                    noOption.setVisible(false);
                    CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
                    cl.show(PanelManager.menuCardPanel, "GAMEOVERSCREEN");
                }
                repaint();
            } else {
                CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
                cl.show(PanelManager.menuCardPanel, "GAMEOVERSCREEN");
        }
            
            if(source == blockButton)
            {
                //Change value of attackBlocked when pressed
                blockTime = System.currentTimeMillis();
                blockTime = blockTime - startOfAttack;
                long attackDuration = monster.getAttackDur();
                
                if(blockTime <= attackDuration)
                {
                    attackBlocked = true;    
                }
                
                else if(blockTime > attackDuration)
                {
                    attackBlocked = false;
                }
            }
            
            else if(source == attackButton)
            {
                monster.setHealth(monster.getHealth() - currentPlayer.getWeapon().attack());
            }
        }
    }
}

                
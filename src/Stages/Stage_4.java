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
        yesOption.setLocation(500, 300);
        yesOption.setSize(100, 50);
        
        
        // No Option
        noOption = UtilityMethods.generateButton("No", 16,
                designAttributes.primaryColor, designAttributes.tertiaryColor, false);
        noOption.addActionListener(this.drawingPanel);
        noOption.setLocation(800, 300);
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
        this.i = 0;
        this.step = 0;
        this.blockTime = 0;
        this.startOfAttack = 0;
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
            "The Entity will howl when it attacks...",
            "Parry its attack and strike it down...",
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
            g.drawString("Player Actions", 430, 480);
            
            blockButton.setLocation(400, 500);
            blockButton.setSize(100, 35);

            attackButton.setLocation(500, 500);
            attackButton.setSize(100, 35);
           
            add(blockButton);
            add(attackButton);
            
           
                if (step == 0) {
                if (i < 3) {
                    g.setColor(Color.WHITE);

                    g.setFont(new Font("Tahoma", Font.BOLD, 16));

                    g.drawString(Stage4_story[i], 30, 280);
                } else if (i >= 3) {
                    step++;
                }

            } else if (step == 1) {
                if (!wearingBlindfold) {
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Tahoma", Font.BOLD, 20));
                    g.drawString("Wear the blindfold?", 400, 70);
                    yesOption.setVisible(true);
                    noOption.setVisible(true);
                    blockButton.setVisible(true);
                    attackButton.setVisible(true);
                }
            } else if (step == 2) {
                if (!wearingBlindfold) {
                    CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
                    cl.show(PanelManager.menuCardPanel, "GAMEOVERSCREEN");
                } else {
                    blockTime = 0;
                    startOfAttack = 0;
                    startOfAttack = System.currentTimeMillis() / 1000L;
                    monster.attack();
                }

            } else if (step >= 3) {
                if (attackBlocked == true) {
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Tahoma", Font.BOLD, 16));
                    g.drawString("You blocked the Entity's attack. Finish it now!", 30, 280);

                } else if (attackBlocked == false) {
                    CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
                    cl.show(PanelManager.menuCardPanel, "GAMEOVERSCREEN");
                }
            }
  
            // Victory screen if monster dies
            if (monster.getHealth() <= 0) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 1000, 600);
                blockButton.setOpaque(false);
                blockButton.setContentAreaFilled(false);
                blockButton.setBorderPainted(false);
                attackButton.setOpaque(false);
                attackButton.setContentAreaFilled(false);
                attackButton.setBorderPainted(false);
                blockButton.setVisible(false);
                attackButton.setVisible(false);
                        
                Image image = Toolkit.getDefaultToolkit().getImage("background/Monster_And_Stage_Combined.jpg");
                g.drawImage(image, 0, 0, this);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Tahoma", Font.PLAIN, 20));
                g.drawString("You have defeated the monster. After 10 years the apocalypse is finally over.", 140, 100);
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
                repaint();
                if(i == 3)
                {
                    step++;
                }
            }
            else
            {
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
                        step++;
                      
                    }
                   
                    repaint();
                } else {
                    CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
                    cl.show(PanelManager.menuCardPanel, "GAMEOVERSCREEN");
                }

                if (source == blockButton) {
                    //Change value of attackBlocked when pressed
                    if (startOfAttack == 0) {
                        // Do nothing if attack not started
                    } else if (startOfAttack != 0) {
                        blockTime = 0;
                        blockTime = System.currentTimeMillis() / 1000L;
                        blockTime = (blockTime - startOfAttack);
                        long attackDuration = monster.getAttackDur();

                        System.out.println(blockTime);
                        if (blockTime <= attackDuration) {
                            attackBlocked = true;
                        } else if (blockTime > attackDuration) {
                            attackBlocked = false;
                            currentPlayer.setHealth(0);
                        }

                        step++;
                        repaint();
                    }
                } else if (source == attackButton) {
                    if (startOfAttack == 0) {
                        // Do nothing
                    } else if (startOfAttack != 0) {
                        monster.setHealth(monster.getHealth() - currentPlayer.getWeapon().attack());
                        repaint();
                    }
                }
        }
        }
    }
}

                
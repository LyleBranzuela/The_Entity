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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * Class for the Stage 4 of the Game.
 *
 * @author lyleb and khoap
 */
public class Stage_4 extends Stage implements ActionListener
{

    private Player currentPlayer;
    private DrawingPanel drawingPanel;
    private JButton yesOption, noOption;
    private boolean wearingBlindfold;
    private DesignAttributes designAttributes;

    /**
     * Constructor for creating Stage 4.
     */
    public Stage_4()
    {
        super();
        super.stageLevel = 4;
        this.designAttributes = new DesignAttributes();

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
        this.yesOption = UtilityMethods.generateButton("Yes", 16,
                designAttributes.primaryColor, designAttributes.tertiaryColor, false);
        this.yesOption.addActionListener(this);
        this.yesOption.setLocation(400,300);
        
        // No Option
        this.noOption = UtilityMethods.generateButton("No", 16,
                designAttributes.primaryColor, designAttributes.tertiaryColor, false);
        this.noOption.addActionListener(this);
        this.noOption.setLocation(600,300);
        
        this.drawingPanel = new DrawingPanel();
        this.drawingPanel.add(this.yesOption);
        this.drawingPanel.add(this.noOption);
        this.drawingPanel.setBackground(Color.BLACK);

        repaint();
        updateStagePlayer();
        add(this.drawingPanel);
    }

    /**
     * Updates the stage player.
     */
    @Override
    public void updateStagePlayer()
    {
        this.currentPlayer = PanelManager.getCurrentPlayer();
                /* 
        
        
                    For CHECKING ONLY
        
        
                */
                this.currentPlayer.pickupItem(new Blindfold());
        this.wearingBlindfold = false;
        this.yesOption.setVisible(true);
        this.noOption.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (this.currentPlayer.hasBlindfold)
        {
            if (source == this.yesOption)
            {
                this.wearingBlindfold = true;
                this.yesOption.setVisible(false);
                this.noOption.setVisible(false);
            }
            else if (source == this.noOption)
            {
                this.wearingBlindfold = false;
                this.yesOption.setVisible(false);
                this.noOption.setVisible(false);
                CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
                cl.show(PanelManager.menuCardPanel, "GAMEOVERSCREEN");
            }
            repaint();
        }
        else
        {
            CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
            cl.show(PanelManager.menuCardPanel, "GAMEOVERSCREEN");
        }
    }
    
    /**
     * A separate JPanel meant to handle all the painting and drawing of the
     * component.
     */
    private class DrawingPanel extends JPanel
    {

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
            if (!wearingBlindfold)
            {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Tahoma", Font.BOLD, 20));
                g.drawString("Wear the blindfold?", 300, 300);
                
                // Ending Screen
//                Image image = Toolkit.getDefaultToolkit().getImage("background/Monster_And_Stage_Combined.jpg");
//                g.drawImage(image, 0, 0, this);
            }
        }
    }
}

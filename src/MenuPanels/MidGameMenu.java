/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuPanels;

import GUI.DatabaseManager;
import GUI.DesignAttributes;
import GUI.UtilityMethods;
import Stages.Stage_2;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author lyleb and khoap
 */
public class MidGameMenu extends JPanel implements ActionListener
{

    private JLabel pausedScreen;
    private JPanel midGameMenuPanel;
    private DesignAttributes designAttributes;
    private JButton resumeButton, saveGameButton, loadGameButton, mainMenuButton, exitButton;

    /**
     *
     */
    public MidGameMenu()
    {
        super(new BorderLayout());
        repaint();
        this.designAttributes = new DesignAttributes();

        this.pausedScreen = new JLabel("PAUSED");
        this.pausedScreen.setFont(new Font("Tahoma", Font.BOLD, 64));
        this.pausedScreen.setForeground(this.designAttributes.primaryColor);
        this.pausedScreen.setBorder(designAttributes.createMarginBorder(0, 8, 0, 8));

        // Setting up the Buttons
        // Resume Game Button
        this.resumeButton = UtilityMethods.generateButton("Resume", 32,
                designAttributes.secondaryColor, null, true);
        this.resumeButton.addActionListener(this);

        // Save Curent Player Button
        this.saveGameButton = UtilityMethods.generateButton("Save Checkpoint", 32,
                designAttributes.secondaryColor, null, true);
        this.saveGameButton.addActionListener(this);

        // Go to the Load Screen Button
        this.loadGameButton = UtilityMethods.generateButton("Load Game", 32,
                designAttributes.secondaryColor, null, true);
        this.loadGameButton.addActionListener(this);

        // Go back to Main Menu Button
        this.mainMenuButton = UtilityMethods.generateButton("Main Menu", 32,
                designAttributes.secondaryColor, null, true);
        this.mainMenuButton.addActionListener(this);

        // Exit Button
        this.exitButton = UtilityMethods.generateButton("Exit", 32,
                designAttributes.secondaryColor, null, true);
        this.exitButton.addActionListener(this);

        // Putting all the buttons into a Button Group
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.resumeButton);
        buttonGroup.add(this.saveGameButton);
        buttonGroup.add(this.loadGameButton);
        buttonGroup.add(this.mainMenuButton);
        buttonGroup.add(this.exitButton);

        this.midGameMenuPanel = new JPanel();
        this.midGameMenuPanel.setBackground(Color.BLACK);
        this.midGameMenuPanel.setOpaque(false);
        this.midGameMenuPanel.add(this.pausedScreen);
        this.midGameMenuPanel.add(this.resumeButton);
        this.midGameMenuPanel.add(this.saveGameButton);
        this.midGameMenuPanel.add(this.loadGameButton);
        this.midGameMenuPanel.add(this.mainMenuButton);
        this.midGameMenuPanel.add(this.exitButton);
        this.midGameMenuPanel.setLayout(new BoxLayout(this.midGameMenuPanel, BoxLayout.Y_AXIS));
        this.midGameMenuPanel.setBorder(designAttributes.marginBorder);
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Escape");
        // Customized Action for pressing Escape
        Action escapeAction = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                PanelManager.changeToStagePanel();
            }
        };
        // Adding an action map to the input map
        this.getActionMap().put("Escape", escapeAction);
        
        
        add(this.midGameMenuPanel);
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
        if (source == this.resumeButton)
        {
            PanelManager.changeToStagePanel();
        }
        // Saves the player name
        else if (source == this.saveGameButton)
        {
            // Storing all the player's names in the Arraylist to check for duplicates later
            ResultSet rs = DatabaseManager.getAllPlayers();
            ArrayList<String> playersInDatabase = new ArrayList<>();
            try
            {
                while (rs.next())
                {
                    String playerName = rs.getString("PLAYERNAME");
                    playersInDatabase.add(playerName);
                }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(LoadMenu.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Checks if the current player's name is in the Array List, meaning it's in the database.
            if (playersInDatabase.contains(PanelManager.getCurrentPlayer().getName()))
            {
                int confirmDialog = JOptionPane.showConfirmDialog(null, "Found Duplicate: Overwrite Save?", "Warning", JOptionPane.YES_NO_OPTION);
                if (confirmDialog == JOptionPane.YES_OPTION)
                {
                    DatabaseManager.savePlayerToDatabase(PanelManager.getCurrentPlayer(), false);
                    JOptionPane.showMessageDialog(null, PanelManager.getCurrentPlayer().getName() + " Save Updated!", "Character Updated", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else
            {
                DatabaseManager.savePlayerToDatabase(PanelManager.getCurrentPlayer(), true);
                JOptionPane.showMessageDialog(null, PanelManager.getCurrentPlayer().getName() + " Saved!",
                        "Character Saved", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        // Goes to the list of Saved files that can be loaded
        else if (source == this.loadGameButton)
        {
            PanelManager.getLoadScreenPanel().updatePlayerSaves();
            PanelManager.setBackToMainMenu(false);
            cl.show(PanelManager.menuCardPanel, "LOADSCREEN");
        }
        // Goes to Main Menu
        else if (source == this.mainMenuButton)
        {
            PanelManager.setBackToMainMenu(true);
            cl.show(PanelManager.menuCardPanel, "MAINMENU");
        }
        // Exits the GUI
        else if (source == this.exitButton)
        {
            UtilityMethods.exitConfirmation();
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Image image = Toolkit.getDefaultToolkit().getImage("background/Pause_Screen.jpg");
        g.drawImage(image, 0, 0, this);
    }
}

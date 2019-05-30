/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuPanels;

import GUI.DatabaseManager;
import GUI.DesignAttributes;
import GUI.UtilityMethods;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * JPanel for the game over screen.
 *
 * @author lyleb and khoap
 */
public class GameOverMenu extends JPanel implements ActionListener
{

    private JLabel gameOverTitle;
    private JPanel gameOverPanel;
    private DesignAttributes designAttributes;
    private JButton retryButton, saveGameButton, mainMenuButton, exitButton;

    public GameOverMenu()
    {
        super(new BorderLayout());
        this.designAttributes = new DesignAttributes();
        
        // Game over Title
        this.gameOverTitle = new JLabel("YOU DIED");
        this.gameOverTitle.setFont(new Font("Tahoma", Font.BOLD, 64));
        this.gameOverTitle.setForeground(Color.RED);
        this.gameOverTitle.setBorder(this.designAttributes.createMarginBorder(0, 8, 0, 8));

        // Setting up the Buttons
        // Retry Game Button
        this.retryButton = UtilityMethods.generateButton("Retry", 32,
                designAttributes.secondaryColor, null, true);
        this.retryButton.addActionListener(this);

        // Save Game Button
        this.saveGameButton = UtilityMethods.generateButton("Save Checkpoint", 32,
                designAttributes.secondaryColor, null, true);
        this.saveGameButton.addActionListener(this);

        // Go to Main Menu Button
        this.mainMenuButton = UtilityMethods.generateButton("Main Menu", 32,
                designAttributes.secondaryColor, null, true);
        this.mainMenuButton.addActionListener(this);

        // Exit Button
        this.exitButton = UtilityMethods.generateButton("Exit", 32,
                designAttributes.secondaryColor, null, true);
        this.exitButton.addActionListener(this);
        
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.retryButton);
        buttonGroup.add(this.saveGameButton);
        buttonGroup.add(this.mainMenuButton);
        buttonGroup.add(this.exitButton);
        
        this.gameOverPanel = new JPanel();
        this.gameOverPanel.add(this.gameOverTitle);
        this.gameOverPanel.add(this.retryButton);
        this.gameOverPanel.add(this.saveGameButton);
        this.gameOverPanel.add(this.mainMenuButton);
        this.gameOverPanel.add(this.exitButton);
        this.gameOverPanel.setBackground(Color.BLACK);
        this.gameOverPanel.setBorder(designAttributes.marginBorder);
        this.gameOverPanel.setLayout(new BoxLayout(this.gameOverPanel, BoxLayout.Y_AXIS));
        
        add(this.gameOverPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        // Retry Button
        if (source == this.retryButton)
        {
            // Reinitialization by updating the player
            PanelManager.setCurrentPlayer(PanelManager.player);
            PanelManager.initiateStage();
        }
        // Save Checkpoint
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
        // Goes to Main Menu
        else if (source == this.mainMenuButton)
        {
            PanelManager.setBackToMainMenu(true);
            CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
            cl.show(PanelManager.menuCardPanel, "MAINMENU");
        }
        // Exits the GUI
        else if (source == this.exitButton)
        {
            UtilityMethods.exitConfirmation();
        }
    }
}

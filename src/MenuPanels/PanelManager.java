/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuPanels;

import GameEntities.Player;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author lyleb and khoap
 */
public class PanelManager extends JPanel
{
    // Controls What Panel is showing
    public static JPanel menuCardPanel;
    private static Player player;
    private JPanel mainMenuPanel, creditsPanel, optionsPanel, gamePanel, loadMenuPanel;
    
    /**
     * 
     */
    public PanelManager()
    {
        super(new BorderLayout());
        
        // Making the Card Menu Panel
        this.mainMenuPanel = new StartMenu();
        this.loadMenuPanel = new LoadMenu();
        this.optionsPanel = new Options();
        this.creditsPanel = new Credits();
        
        // Adding them to the Card Layout JPanel 
        menuCardPanel = new JPanel(new CardLayout());
        menuCardPanel.add(this.mainMenuPanel, "MAINMENU");
        // menuCardPanel.add(this.gamePanel, "GAMEPANEL");
        menuCardPanel.add(this.loadMenuPanel, "LOADSCREEN");
        menuCardPanel.add(this.optionsPanel, "OPTIONSCREEN");
        menuCardPanel.add(this.creditsPanel, "CREDITSCREEN");
        
        CardLayout cl = (CardLayout)(menuCardPanel.getLayout());
        add(menuCardPanel);
        cl.show(menuCardPanel, "MAINMENU");
    }
    
    /**
     * Sets a new player to a current player.
     * 
     * @param newPlayer new player to be a current player.
     */
    public static void setCurrentPlayer(Player newPlayer)
    {
        player = newPlayer;
    }
    
    /**
     * Returns the current player
     * 
     * @return the current player.
     */
    public static Player getCurrentPlayer()
    {
        return player;
    }
}

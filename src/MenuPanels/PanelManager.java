/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuPanels;

import GameEntities.Player;
import Stages.StageDrawingPanel;
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
    public static boolean backToMainMenu;
    public static Player player;
    public JPanel mainMenuPanel, midGameMenuPanel, creditsPanel, optionsPanel;
    public static JPanel loadMenuPanel, gamePanel;
    
    /**
     * 
     */
    public PanelManager()
    {
        super(new BorderLayout());
        
        // Making the Card Menu Panel
        this.mainMenuPanel = new StartMenu();
        this.midGameMenuPanel = new MidGameMenu();
        this.optionsPanel = new Options();
        this.creditsPanel = new Credits();
        player = new Player("Placeholder");
        loadMenuPanel = new LoadMenu();
        gamePanel = new StageDrawingPanel();
        
        // Adding them to the Card Layout JPanel 
        menuCardPanel = new JPanel(new CardLayout());
        menuCardPanel.add(mainMenuPanel, "MAINMENU");
        menuCardPanel.add(midGameMenuPanel, "MIDGAMEMENU");
        menuCardPanel.add(gamePanel, "GAMEPANEL");
        menuCardPanel.add(loadMenuPanel, "LOADSCREEN");
        menuCardPanel.add(optionsPanel, "OPTIONSCREEN");
        menuCardPanel.add(creditsPanel, "CREDITSCREEN");
        
        // Specifies whether the back buttons should go to the main menu or game menu.
        backToMainMenu = true;
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
    
    /**
     * Load screen panel needs to be updated constantly, 
     * this function is just to access it easier for other JPanels.
     * 
     * @return the load menu panel to be updated.
     */
    public static LoadMenu getLoadScreenPanel()
    {
        return (LoadMenu) loadMenuPanel;
    }
    
    /**
     * The Stage Drawing Panel needs to be updated constantly.
     * this function is just to access it easier for other JPanels.
     * 
     * @return the stage drawing panel to be updated.
     */
    public static StageDrawingPanel getStageDrawingPanel()
    {
        return (StageDrawingPanel) gamePanel;
    }
    
    /**
     * Sets whether the back button should go to main menu or mid game menu.
     * 
     * @param bool true to go back to main menu, false to go to mid game menu.
     */
    public static void setBackToMainMenu(boolean bool)
    {
        backToMainMenu = bool;
    }
    

}

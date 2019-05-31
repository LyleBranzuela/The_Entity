/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuPanels;

import GameEntities.Player;
import Stages.*;
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
    public JPanel mainMenuPanel, midGameMenuPanel, creditsPanel, gameOverPanel;
    public static JPanel loadMenuPanel;
    public static Stage_1 stage1Panel;
    public static Stage_2 stage2Panel;
    public static Stage_3 stage3Panel;
    public static Stage_4 stage4Panel;
    
    
    /**
     * 
     */
    public PanelManager()
    {
        super(new BorderLayout());
        
        // Making the Card Menu Panel
        player = new Player("PlaceHolder");
        this.mainMenuPanel = new StartMenu();
        this.midGameMenuPanel = new MidGameMenu();
        this.creditsPanel = new Credits();
        this.gameOverPanel = new GameOverMenu();
        loadMenuPanel = new LoadMenu();
        stage1Panel = new Stage_1();
        stage2Panel = new Stage_2();
        stage3Panel = new Stage_3();
        stage4Panel = new Stage_4();
        
        // Adding them to the  Card Layout JPanel ,j
        menuCardPanel = new JPanel(new CardLayout());
        menuCardPanel.add(stage1Panel, "STAGE1PANEL");
        menuCardPanel.add(stage2Panel, "STAGE2PANEL");
        menuCardPanel.add(stage3Panel, "STAGE3PANEL");
        menuCardPanel.add(stage4Panel, "STAGE4PANEL");
        menuCardPanel.add(mainMenuPanel, "MAINMENU");
        menuCardPanel.add(midGameMenuPanel, "MIDGAMEMENU");
        menuCardPanel.add(loadMenuPanel, "LOADSCREEN");
        menuCardPanel.add(creditsPanel, "CREDITSCREEN");
        menuCardPanel.add(gameOverPanel, "GAMEOVERSCREEN");
        
        // Specifies whether the back buttons should go to the main menu or game menu.
        backToMainMenu = true;
        CardLayout cl = (CardLayout)(menuCardPanel.getLayout());
        add(menuCardPanel);
        cl.show(menuCardPanel, "MAINMENU");
    }
    
    /**
     * Sets a new player to a current player, this also updates the stage the player's in.
     * 
     * @param newPlayer new player to be a current player.
     */
    public static void setCurrentPlayer(Player newPlayer)
    {
        player = newPlayer;
        stage1Panel.updateStagePlayer();
        stage2Panel.updateStagePlayer();
        stage3Panel.updateStagePlayer();
        stage4Panel.updateStagePlayer();
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
     * Sets whether the back button should go to main menu or mid game menu.
     * 
     * @param bool true to go back to main menu, false to go to mid game menu.
     */
    public static void setBackToMainMenu(boolean bool)
    {
        backToMainMenu = bool;
    }
   
    /**
     * Changes the panel into one of the stage panels.
     */
    public static void changeToStagePanel()
    {
        CardLayout cl = (CardLayout) (menuCardPanel.getLayout());
        switch (player.getCurrentStage().getStageLevel())
        {
            case 1:
                cl.show(menuCardPanel, "STAGE1PANEL");
                break;

            case 2:
                cl.show(menuCardPanel, "STAGE2PANEL");
                break;

            case 3:
                cl.show(menuCardPanel, "STAGE3PANEL");
                break;

            case 4:
                cl.show(menuCardPanel, "STAGE4PANEL");
                break;

            default:
                cl.show(menuCardPanel, "STAGE1PANEL");
                break;
        }
    }
}

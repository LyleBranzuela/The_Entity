/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuPanels;

import GUI.DesignAttributes;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author lyleb
 */
public class PanelManager extends JPanel
{
    // Controls What Panel is showing
    public static JPanel menuCardPanel;
    private JPanel mainMenuPanel, creditsPanel, optionsPanel, continuePanel, loadMenuPanel;
    
    public PanelManager()
    {
        super(new BorderLayout());
        DesignAttributes designAttributes = new DesignAttributes();
        
        // Making the Card Menu Panel
        this.mainMenuPanel = new StartMenu();
        this.loadMenuPanel = new LoadMenu();
        this.optionsPanel = new Options();
        this.creditsPanel = new Credits();
        
        // Adding them to the Card Layout JPanel 
        menuCardPanel = new JPanel(new CardLayout());
        menuCardPanel.add(this.mainMenuPanel, "MAINMENU");
        // menuCardPanel.add(this.continueGamePanel, "CONTINUEGAME");
        // menuCardPanel.add(this.stage1Panel, "STARTNEWGAME");
        menuCardPanel.add(this.loadMenuPanel, "LOADSCREEN");
        menuCardPanel.add(this.optionsPanel, "OPTIONSCREEN");
        menuCardPanel.add(this.creditsPanel, "CREDITSCREEN");
        
        CardLayout cl = (CardLayout)(menuCardPanel.getLayout());
        add(menuCardPanel);
        cl.show(menuCardPanel, "MAINMENU");
    }
}

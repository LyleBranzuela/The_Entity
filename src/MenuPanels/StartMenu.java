/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuPanels;

import GUI.DesignAttributes;
import GUI.UtilityMethods;
import GameEntities.Player;
import Stages.Stage_1;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author lyleb and khoap
 */
public class StartMenu extends JPanel implements ActionListener
{

    private JLabel gameName;
    public JButton newGameButton, loadGameButton, creditsButton, exitButton;
    private JPanel menuListPanel;
    private DesignAttributes designAttributes;

    /**
     *
     */
    public StartMenu()
    {
        super(new BorderLayout());
        repaint();
        this.designAttributes = new DesignAttributes();

        this.gameName = new JLabel("The Entity");
        this.gameName.setFont(new Font("Tahoma", Font.BOLD, 64));
        this.gameName.setForeground(this.designAttributes.primaryColor);
        this.gameName.setBorder(designAttributes.createMarginBorder(0, 8, 0, 8));

        // Setting up the Buttons
        // New Game Button
        this.newGameButton = UtilityMethods.generateButton("New Game", 32,
                designAttributes.secondaryColor, null, true);
        this.newGameButton.addActionListener(this);

        // Load Game Button
        this.loadGameButton = UtilityMethods.generateButton("Load Game", 32,
                designAttributes.secondaryColor, null, true);
        this.loadGameButton.addActionListener(this);

        // Credits Button
        this.creditsButton = UtilityMethods.generateButton("Credits", 32,
                designAttributes.secondaryColor, null, true);
        this.creditsButton.addActionListener(this);

        // Exit Button
        this.exitButton = UtilityMethods.generateButton("Exit", 32,
                designAttributes.secondaryColor, null, true);
        this.exitButton.addActionListener(this);

        // Putting all the buttons into a Button Group
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.newGameButton);
        buttonGroup.add(this.loadGameButton);
        buttonGroup.add(this.creditsButton);
        buttonGroup.add(this.exitButton);

        // Combining all the components into a single JPanel
        this.menuListPanel = new JPanel();
        this.menuListPanel.setBackground(Color.BLACK);
        this.menuListPanel.setOpaque(false);
        this.menuListPanel.add(this.gameName);
        this.menuListPanel.add(this.newGameButton);
        this.menuListPanel.add(this.loadGameButton);
        this.menuListPanel.add(this.creditsButton);
        this.menuListPanel.add(this.exitButton);
        this.menuListPanel.setLayout(new BoxLayout(this.menuListPanel, BoxLayout.Y_AXIS));
        this.menuListPanel.setBorder(designAttributes.marginBorder);

        add(this.menuListPanel);
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
        // Creates a New Game
        if (source == this.newGameButton)
        {
            String name = JOptionPane.showInputDialog(null, "What is your name? [No Spaces, Special Characters, and Numbers]", 
                    "Character Name", JOptionPane.QUESTION_MESSAGE);
            // Check if it was cancelled
            if (name != null) 
            {
                // Check if it's A-Z or a-z
                if (name.matches("[A-Za-z]*"))
                {
                    // Default Values
                    Player newPlayer = new Player(name);
                    newPlayer.setCurrentStageLevel(new Stage_1());
                    PanelManager.setCurrentPlayer(newPlayer);
                    PanelManager.changeToStagePanel();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Found illegal characters! Please try again.", "Error in Name Found", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        // Goes to the Load Screen
        else if (source == this.loadGameButton)
        {
            PanelManager.getLoadScreenPanel().updatePlayerSaves();
            PanelManager.setBackToMainMenu(true);
            cl.show(PanelManager.menuCardPanel, "LOADSCREEN");
        }
        // Goes to the Credits Panel
        else if (source == this.creditsButton)
        {
            PanelManager.setBackToMainMenu(true);
            cl.show(PanelManager.menuCardPanel, "CREDITSCREEN");
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
        Image image = Toolkit.getDefaultToolkit().getImage("background/Stage4_Ruins.jpg");
        g.drawImage(image, 0, 0, this);
    }
}

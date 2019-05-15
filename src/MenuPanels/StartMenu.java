/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuPanels;

import GUI.DesignAttributes;
import GUI.UtilityMethods;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author lyleb and khoap
 */
public class StartMenu extends JPanel implements ActionListener
{

    private JLabel gameName;
    public JButton continueButton, newGameButton, loadGameButton, optionsButton, creditsButton, exitButton;
    private JPanel menuListPanel;
    private DesignAttributes designAttributes;

    /**
     *
     */
    public StartMenu()
    {
        super(new BorderLayout());
        this.designAttributes = new DesignAttributes();

        this.gameName = new JLabel("The Entity");
        this.gameName.setFont(new Font("Tahoma", Font.BOLD, 64));
        this.gameName.setForeground(this.designAttributes.primaryColor);
        this.gameName.setBorder(designAttributes.createMarginBorder(0, 8, 0, 8));

        // Setting up the Buttons
        // Continue Button
        this.continueButton = UtilityMethods.generateButton("Continue", 32,
                designAttributes.secondaryColor, null, true);
        this.continueButton.addActionListener(this);

        // New Game Button
        this.newGameButton = UtilityMethods.generateButton("New Game", 32,
                designAttributes.secondaryColor, null, true);
        this.newGameButton.addActionListener(this);

        // Load Game Button
        this.loadGameButton = UtilityMethods.generateButton("Load Game", 32,
                designAttributes.secondaryColor, null, true);
        this.loadGameButton.addActionListener(this);

        // Options Button
        this.optionsButton = UtilityMethods.generateButton("Options", 32,
                designAttributes.secondaryColor, null, true);
        this.optionsButton.addActionListener(this);

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
        buttonGroup.add(this.continueButton);
        buttonGroup.add(this.newGameButton);
        buttonGroup.add(this.loadGameButton);
        buttonGroup.add(this.optionsButton);
        buttonGroup.add(this.creditsButton);
        buttonGroup.add(this.exitButton);
        
        // Combining all the components into a single JPanel
        this.menuListPanel = new JPanel();
        this.menuListPanel.add(this.gameName);
        this.menuListPanel.add(this.continueButton);
        this.menuListPanel.add(this.newGameButton);
        this.menuListPanel.add(this.loadGameButton);
        this.menuListPanel.add(this.optionsButton);
        this.menuListPanel.add(this.creditsButton);
        this.menuListPanel.add(this.exitButton);
        this.menuListPanel.setBackground(Color.BLACK);
        this.menuListPanel.setLayout(new BoxLayout(this.menuListPanel, BoxLayout.Y_AXIS));
        this.menuListPanel.setBorder(designAttributes.marginBorder);

        add(this.menuListPanel);
    }

    /**
     *
     */
    public class OpaqueCellRenderer extends DefaultListCellRenderer
    {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            setOpaque(isSelected);
//            System.out.println("Is Selecetd: " + isSelected);
//            System.out.println("Cell Has Focus: " + cellHasFocus);
//            if (isSelected)
//            {
//                setForeground(Color.WHITE);
//            }
//            else
//            {
//                setForeground(designAttributes.secondaryColor);
//            }
            return this;
        }

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
        if (source == this.continueButton)
        {
            cl.show(PanelManager.menuCardPanel, "CONTINUEGAME");
        }
        // Continues the most recent Game
        else if (source == this.newGameButton)
        {
            cl.show(PanelManager.menuCardPanel, "STARTNEWGAME");
        }
        // Creates a New Game
        else if (source == this.loadGameButton)
        {
            PanelManager.setBackToMainMenu(true);
            cl.show(PanelManager.menuCardPanel, "LOADSCREEN");
        }
        // Goes to the Options Panel
        else if (source == this.optionsButton)
        {
            PanelManager.setBackToMainMenu(true);
            cl.show(PanelManager.menuCardPanel, "OPTIONSCREEN");
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
}

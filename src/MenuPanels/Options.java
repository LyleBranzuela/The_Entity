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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author lyleb and khoap
 */
public class Options extends JPanel implements ActionListener
{

    private JLabel optionLabel;
    private JButton backButton;
    private JPanel optionPanel;
    private DesignAttributes designAttributes;
    public static float volume;
    public static boolean hasEarphones;

    /**
     *
     */
    public Options()
    {
        super(new BorderLayout());
        this.designAttributes = new DesignAttributes();

        // Text of Options on the Top
        this.optionLabel = new JLabel("Options");
        this.optionLabel.setFont(new Font("Tahoma", Font.BOLD, 64));
        this.optionLabel.setForeground(this.designAttributes.primaryColor);
        this.optionLabel.setBorder(designAttributes.createMarginBorder(0, 8, 0, 8));

        // Goes back to the main menu
        this.backButton = UtilityMethods.generateButton("Back", 32,
                designAttributes.secondaryColor, null, true);
        this.backButton.addActionListener(this);

        ButtonGroup buttonGroup = new ButtonGroup();

        this.optionPanel = new JPanel();
        this.optionPanel.add(this.optionLabel);
        this.optionPanel.add(this.backButton);
        this.optionPanel.setBackground(Color.BLACK);
        this.optionPanel.setLayout(new BoxLayout(this.optionPanel, BoxLayout.Y_AXIS));
        this.optionPanel.setBorder(designAttributes.marginBorder);

        add(this.optionPanel);
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
        Object source = (Object) e.getSource();
        if (source == this.backButton)
        {
            if (PanelManager.backToMainMenu)
            {
                cl.show(PanelManager.menuCardPanel, "MAINMENU");
            }
            else
            {
                cl.show(PanelManager.menuCardPanel, "MIDGAMEMENU");
            }
        }
    }
}

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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author lyleb and khoap
 */
public class Credits extends JPanel implements ActionListener
{
    private JLabel creditsLabel;
    private JButton backButton;
    private JPanel creditsPanel;
    private DesignAttributes designAttributes;

    /**
     * 
     */
    public Credits() {
        super(new BorderLayout());
        this.designAttributes = new DesignAttributes();
        
        // Text of Credits on the Top
        this.creditsLabel = new JLabel("Credits");
        this.creditsLabel.setFont(new Font("Tahoma", Font.BOLD, 64));
        this.creditsLabel.setForeground(this.designAttributes.primaryColor);
        
        // Goes back to the main menu
        this.backButton = UtilityMethods.generateButton("Back", 32,
                designAttributes.secondaryColor, null, true);
        this.backButton.addActionListener(this);
        
        this.creditsPanel = new JPanel();
        this.creditsPanel.add(this.creditsLabel);
        this.creditsPanel.add(this.backButton);
        this.creditsPanel.setLayout(new BoxLayout(this.creditsPanel, BoxLayout.Y_AXIS));
        this.creditsPanel.setBorder(designAttributes.marginBorder);
        
        add(this.creditsPanel, BorderLayout.WEST);
    }

    /**
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = (Object) e.getSource();
        if (source == this.backButton)
        {
            CardLayout cl = (CardLayout)(PanelManager.menuCardPanel.getLayout());
            cl.show(PanelManager.menuCardPanel, "MAINMENU");
        }
    }
    
    
    

}

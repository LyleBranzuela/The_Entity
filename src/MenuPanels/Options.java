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
public class Options extends JPanel implements ActionListener
{
    private JLabel optionLabel;
    private JButton backButton;
    private JPanel optionPanel;
    private DesignAttributes designAttributes;

    /**
     * 
     */
    public Options() {
        super(new BorderLayout());
        this.designAttributes = new DesignAttributes();
        
        // Text of Options on the Top
        this.optionLabel = new JLabel("Options");
        this.optionLabel.setFont(new Font("Tahoma", Font.BOLD, 64));
        this.optionLabel.setForeground(this.designAttributes.primaryColor);
        
        // Goes back to the main menu
        this.backButton = UtilityMethods.generateButton("Back", 32, 
                designAttributes.secondaryColor, null, true);
        this.backButton.addActionListener(this);
        
        this.optionPanel = new JPanel();
        this.optionPanel.add(this.optionLabel);
        this.optionPanel.add(this.backButton);
        this.optionPanel.setLayout(new BoxLayout(this.optionPanel, BoxLayout.Y_AXIS));
        this.optionPanel.setBorder(designAttributes.marginBorder);
        
        add(this.optionPanel, BorderLayout.WEST);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author lyleb and khoap
 */
public class UtilityMethods
{
    /**
     * Generates a random number for a variety of purposes
     *
     * @param range for random number to be created in
     * @return a random number within range.
     */
    public static int randNum(int range)
    {
        Random rand = new Random();
        int x = rand.nextInt(range);

        return x;
    }
    
    /**
     * 
     * 
     * @param fontName
     * @param fontSize
     * @param fontColor
     * @param buttonColor
     * @param opaque
     * @return 
     */
    public static JButton generateButton(String fontName, int fontSize,
            Color fontColor, Color buttonColor, Boolean opaque)
    {
        JButton generatedButton = new JButton(fontName);
        generatedButton.setFont(new Font("Tahoma", Font.BOLD, fontSize));
        generatedButton.setForeground(fontColor);

        if (buttonColor != null)
        {
            generatedButton.setBackground(buttonColor);
        }

        if (opaque)
        {
            generatedButton.setOpaque(false);
            generatedButton.setContentAreaFilled(false);
            generatedButton.setBorderPainted(false);
        }
        
        generatedButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                generatedButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                generatedButton.setBackground(UIManager.getColor("control"));
            }
        });
        
        
        return generatedButton;
    }
    
    /**
     * 
     */
    public static void exitConfirmation()
    {
        int confirmDialog = JOptionPane.showConfirmDialog (null, "Would You Like To Exit?", "Warning", JOptionPane.YES_NO_OPTION);
        if (confirmDialog == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }
}

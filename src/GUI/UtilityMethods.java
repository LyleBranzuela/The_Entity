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

        return generatedButton;
    }
}

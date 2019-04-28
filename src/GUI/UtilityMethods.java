/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

/**
 *
 * @author lyleb
 */
public class UtilityMethods
{

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

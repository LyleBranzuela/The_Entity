/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import MenuPanels.StartMenu;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * 
 * @author lyleb and khoap
 */
public class MainDriver 
{
    /**
     * 
     * 
     * @param args 
     */
    public static void main(String[] args)
    {
        StartMenu myPanel = new StartMenu();
        JFrame frame = new JFrame("The Entity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(myPanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(new Point((d.width / 2) - (frame.getWidth() / 2), (d.height / 2) - (frame.getHeight() / 2)));
        frame.setVisible(true);
    }

}

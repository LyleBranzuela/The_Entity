/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stages;

import GameEntities.Entity;
import GameEntities.Player;
import MenuPanels.PanelManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A separate JPanel meant to handle all the painting and drawing of the
 * component.
 *
 * @author lyleb and khoap
 */
public class StageDrawingPanel extends JPanel
{
    public ArrayList<Entity> entityList;
    
    /**
     * 
     * @param entityList 
     */
    public StageDrawingPanel(ArrayList<Entity> entityList)
    {
        super();
        this.entityList = entityList;
    }
    
    /**
     * Updates the entity list of the stage drawing panel.
     * 
     * @param entityList the entity list to be updated.
     */
    public void updateEntityList(ArrayList<Entity> entityList)
    {
        this.entityList = entityList;
    }
    
    /**
     * Clears the entity list of the Stage JPanel.
     */
    public void clearEntityList()
    {
        this.entityList.clear();
    }
    
    /**
     * Draws all the components of the drawing panel
     *
     * @param g specifies the current graphic space the panel is using.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (!this.entityList.isEmpty())
        {
            for (Entity e : this.entityList)
            {
                e.draw(g);
            }
        }
    }
    
    public static void main(String[] args)
    {
        ArrayList<Entity> entityList = new ArrayList<>();
        entityList.add(new Player("Ja Yeet"));
        
           // Instantiate Panel Manager
        StageDrawingPanel myPanel = new StageDrawingPanel(entityList);
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


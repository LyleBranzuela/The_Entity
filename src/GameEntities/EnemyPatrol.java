/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntities;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author lyleb and khoap
 */
public class EnemyPatrol extends Entity  
{

    public EnemyPatrol(String name, int health)
    {
        super("Enemy Patrol", 3);
    }
    
    /**
     * Method the player object.
     * 
     * @param g graphics from a drawing panel.
     */
    @Override
    public void draw(Graphics g)
    {
        g.setColor(Color.BLACK);
        // Center of the Circle    
        int x = super.entityMovement.getXMovement();
        int y = super.entityMovement.getYMovement();
        g.fillOval(x, y+10, 60, 30);
        
        g.setColor(Color.BLACK);
        g.fillOval(x+18, y, 45, 45);
    }
}

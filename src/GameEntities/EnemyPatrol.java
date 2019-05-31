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
    public int initialDirection;
    
    public EnemyPatrol(String name, int health, int initialDirection)
    {
        super(name, 3);
        this.initialDirection = initialDirection;
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
        if (this.initialDirection == EntityMovement.LEFT ||
            this.initialDirection == EntityMovement.RIGHT)
        {
            g.fillOval(x+25, y-15, 30, 75);
        }
        else
        {
            g.fillOval(x, y + 10, 75, 30);
        }
        
        //g.setColor(new Color(255,219,172));
        g.setColor(Color.BLACK);
        g.fillOval(x+15, y, 45, 45);
    }
}

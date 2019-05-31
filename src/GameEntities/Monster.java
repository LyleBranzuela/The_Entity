/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntities;

import java.awt.Graphics;

/**
 * Created enemy Class, monster type.
 * 
 * @author lyleb and khoap.
 */
public class Monster extends Entity
{
    public Monster(String name)
    {
        super(name, 3);
    }

    /**
     * Monster is never seen in the game.
     * @param g graphics to be drawn on.
     */
    @Override
    public void draw(Graphics g)
    {
        // Monster is never seen in the game.
    }
}

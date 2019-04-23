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
public class HiddenMonster extends Entity implements Enemy
{
    public HiddenMonster(String name)
    {
        super(name, 5);
    }

    /**
     * 
     * @param player 
     */
    @Override
    public void attackPlayer(Player player)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param damage 
     */
    @Override
    public void attackedByPlayer(int damage)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
     * @param g 
     */
    @Override
    public void draw(Graphics g)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntities;

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
    
    @Override
    public void draw(Graphics g)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

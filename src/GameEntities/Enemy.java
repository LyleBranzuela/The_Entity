/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntities;

/**
 * Interface for the Enemy type Entity.
 * This will be needed on the GUI.
 * 
 * @author lyleb and khoap
 */
public interface Enemy
{
    public void attackPlayer(Player player);
    public void attackedByPlayer(int damage);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntities;

import GUI.UtilityMethods;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created enemy Class, monster type.
 * 
 * @author lyleb and khoap.
 */
public class Monster extends Entity
{
    UtilityMethods utils = new UtilityMethods();
    long attackDuration;
    
    public Monster(String name)
    {
        super(name, 3);
    }

    /**
     * Monster is never seen during the game.
     * @param g graphics to be drawn on.
     */
    @Override
    public void draw(Graphics g)
    {
        // Monster is never seen during the game.
    }
    
    /**
     * Function to simulate monster's attack sound
     * 
     */
    public void attack() {

        long startTime = System.currentTimeMillis();

        utils.playSoundtrack("sound/Monster_Howl_1.wav", 2);
        long endTime = System.currentTimeMillis();

        this.attackDuration = startTime - endTime;

    }

    
    /**
     * Function to fetch duration of attack for calculations
     * 
     * @return attackDuration
     */
    public long getAttackDur()
    {
        return this.attackDuration;
    }
}

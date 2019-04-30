/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntities;

import Items.*;
import Stages.*;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Class for the main character of the game.
 *
 * @author lyleb and khoap
 */
public class Player extends Entity
{

    private Item weaponHolding;
    private Stage playerStage;
    public boolean hasBlindfold;

    /**
     * Override Constructor for the game player.
     *
     * @param name name of the player chosen by the user.
     */
    public Player(String name)
    {
        super(name, 1);
        this.playerStage = new Stage_1();
        this.weaponHolding = null;
        this.hasBlindfold = false;
    }

    /**
     * Picks up the selected item from the parameter.
     *
     * @param item item to be picked up.
     */
    public void pickupItem(Item item)
    {
        if (item instanceof Blindfold)
        {
            this.hasBlindfold = true;
        }
        else
        {
            this.weaponHolding = item;
        }
    }
    
    /**
     * Gets the current held weapon.
     *
     * @return the weapon being held.
     */
    public Item getWeapon()
    {
        return this.weaponHolding;
    }


    /**
     * Sets the stage level the character is in.
     *
     * @param stage what stage it should be set in.
     */
    public void setCurrentStageLevel(Stage stage)
    {
        this.playerStage = stage;
    }

    /**
     * Returns the current stage of the player.
     *
     * @return stage of the player object.
     */
    public Stage getCurrentStage()
    {
        return this.playerStage;
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

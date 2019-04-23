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
    private ArrayList<Item> itemsHeld;
    private Stage playerStage;
    
    /**
     * Override Constructor for the game player.
     *
     * @param name name of the player chosen by the user.
     */
    public Player(String name)
    {
        super(name, 1);
        this.itemsHeld  = new ArrayList();
        this.playerStage = new Stage_1();
    }

    /**
     * Picks up the selected item from the parameter.
     *
     * @param item item to be picked up.
     */
    public void pickupItem(Item item)
    {
        System.out.println("You picked up " + item.getName() + ".");
        this.itemsHeld.add(item);
    }
    
    /**
     * Shows the player's weapon in inventory
     *
     * @return The player's chosen weapon from stage 3.
     */
    public Weapon showWeapon()
    {
        int x;
        
        for ( x = 0; itemsHeld.get(x) != null; x++)
        {
            if(itemsHeld.get(x) instanceof Weapon)
            {
                break;
            }
        }
        
        return (Weapon) itemsHeld.get(x);
    }
    
    /**
     * Uses the specified item in the inventory.
     *
     * @param index what item from the list.
     */
    public void useItem(int index)
    {
        this.itemsHeld.get(index).useItem();
        this.itemsHeld.remove(index);
        this.itemsHeld.trimToSize();
    }

    /**
     * Opens the character's inventory if there's something in.
     *
     * @return gives back the inventory.
     */
    public ArrayList<Item> openCharacterInventory()
    {
        if (this.itemsHeld.isEmpty())
        {
            System.out.println("Your inventory is empty.");
            return null;
        }
        else
        {
            for (int counter = 0; counter < this.itemsHeld.size(); counter++)
            {
                System.out.println("[" + counter + "] " + this.itemsHeld.get(counter).getName());
            }
            return this.itemsHeld;
        }
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

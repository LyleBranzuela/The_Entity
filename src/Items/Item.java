/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import java.io.Serializable;

/**
 * Abstract parent class for the Items.
 * @author lyleb and khoap
 */
public abstract class Item implements Serializable
{
    public String name;
    public int itemID;
    
    /**
     * Constructor for the abstract Item class.
     * 
     * @param name name of the item.
     * @param itemID
     */
    public Item (String name, int itemID) {
        this.name = name;
        this.itemID = itemID;
    }
    
    /**
     * Abstract class for children classes, to decide what the item does.
     */
    abstract public void useItem();
    
    /**
     * Returns the name of the item.
     * 
     * @return the item name.
     */
    public String getName() {
        return this.name;
    }
    
    public int getItemID() {
        return this.itemID;
    }
    
    abstract public int attack();
    abstract public String printDescription();
}

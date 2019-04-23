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
    
    /**
     * Constructor for the abstract Item class.
     * 
     * @param name name of the item.
     */
    public Item (String name) {
        this.name = name;
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
    
    abstract public String printDescription();
}

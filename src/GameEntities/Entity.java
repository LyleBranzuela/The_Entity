/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntities;

import java.awt.Graphics;
import java.io.Serializable;

/**
 * Abstract class for the Entity.
 *
 * @author lyleb and khoap
 */
public abstract class Entity implements Serializable
{
    public String name;
    protected int health;
    public int x_coord;
    public int y_coord;

    /**
     * Constructor for the entity's name and health.
     *
     * @param name takes the name of the entity.
     * @param health takes the health of the entity.
     */
    public Entity(String name, int health)
    {
        this.name = name;
        this.health = health;
    }

    /**
     * Abstract function to be fulfilled by the entity for GUI.
     */
    //abstract protected void characterMovement();

    /**
     * Sets the name of the entity.
     * 
     * @param name specified name sent.
     */
    public void getName(String name)
    {
        this.name = name;
    }
    
    /**
     * Returns the name of the entity.
     * 
     * @return the entity's name.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Returns the health of the entity.
     * 
     * @return the entity's health.
     */
    public int getHealth()
    {
        return this.health;
    }
    
     /**
     * Sets the health of the entity.
     * 
     * @param health how much health they set.
     */
    public void setHealth(int health)
    {
        this.health = health;
    }
    
    /**
     * 
     * @param x
     * @param y 
     */
    public void setLocation(int x, int y)
    {
        this.x_coord = x;
        this.y_coord = y;
    }
    
     /**
     * 
     * @param g 
     */
    public abstract void draw(Graphics g);
}

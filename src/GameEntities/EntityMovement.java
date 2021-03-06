/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntities;

import java.awt.Point;

/**
 * Class for moving the entity around the JFrame.
 * This class also provides where they are in the current game world.
 *  
 * @author lyleb and khoap
 */
public class EntityMovement
{
    // Set the constants of the directions for easier access through static variables
    public static final int FORWARD = 1;
    public static final int BACKWARD = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    
    // Actual parameter of the entity's location
    private int movementSpeed; // How Fast They Move
    private int x, y;
    private Point point;    

    /**
     * Sets where the entity is currently on the JFrame.
     * 
     * @param x the Entity's current x.
     * @param y the Entity's current y.
     */
    public EntityMovement(int x, int y)
    {
        this.point = new Point();
        this.movementSpeed = 6;
    }
        
     /**
     * Sets the movement speed of the entity.
     * 
     * @param movementSpeed the movement speed desired by the caller.
     */
    public void setMovementSpeed(int movementSpeed)
    {
        this.movementSpeed = movementSpeed;
    }

     /**
     * Returns the Entity's movement speed.
     *
     * @return Entity's movement speed.
     */
    public int getMovementSpeed()
    {
        return this.movementSpeed;
    }
    
    
    /**
     * Returns the Entity's current X.
     * 
     * @return the entity's current X.
     */
    public int getXMovement()
    {
        return this.x;
    }

    /**
     * Returns the Entity's current Y.
     * 
     * @return the entity's current y.
     */
    public int getYMovement()
    {
        return this.y;
    }

    /**
     * Returns the Entity's movement point.
     * 
     * @return the entity's current point.
     */
    public Point getPointMovement()
    {
        return this.point;
    }
    
    /**
     * Sets the location on where the entity is.
     * 
     * @param x parameter for the x-axis of the JPanel.
     * @param y parameter for the y-axis of the JPanel.
     */
    public void setLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.point.setLocation(this.x, this.y);
    }

    /**
     * Moves the entity based on the direction parameter.
     * 
     * @param direction which direction the entity moves.
     */
    public void moveEntity(int direction)
    {
        // Decides what direction the caller wants to go
        switch (direction)
        {
            case FORWARD:
                this.y-=this.movementSpeed;
                break;
                
            case BACKWARD:
                this.y+=this.movementSpeed;
                break;
                
            case LEFT:
                this.x-=this.movementSpeed;
                break;
                
            case RIGHT:
                this.x+=this.movementSpeed;
                break;
                
            default:
                break;

        }
    }
}

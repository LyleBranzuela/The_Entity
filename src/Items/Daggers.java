/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

/**
 * Item subclass for the Daggers Weapon.
 * 
 * @author lyleb and khoap
 */
public class Daggers extends Item implements Weapon
{
    private final int dagDmg = 2;
    private final int dagSeq = 2;
    
    /**
     * Constructor for the Daggers Weapon Item.
     */
    public Daggers()
    {
        super("Dual Daggers");
    }

    /**
     * Prints out the Daggers Details when Used by the Player.
     */
    @Override
    public void useItem()
    {
        printDescription();
    }

    /**
     * Attack an entity with two daggers.
     *
     * @return the damage made.
     */
    @Override
    public int attack()
    {
        return dagDmg; //Returns damage of the dagger for use in damage calculation
    }
    
    /**
     * Parry the attack of the enemy monster with two daggers.
     * 
     * @return the damage taken.
     */
    @Override
    public int parrySeq()
    {
        return dagSeq; //Returns int to adjust parry difficulty
    }
    
    /**
     * Prints out the description of the daggers.
     * 
     * @return the description of the daggers.
     */
    @Override
    public String printDescription() 
    {
        return "A deadly pair of kukris. Medium damage and maneuverability.";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;


/**
 * Item subclass for the Machete Weapon.
 *
 * @author lyleb and khoap
 */
public class Machete extends Item implements Weapon
{

    private final int machDmg = 3;
    private final int machSeq = 3;

    /**
     * Constructor for the Machete Weapon Item.
     */
    public Machete()
    {
        super("Machete");
    }

    /**
     * Prints out the Machete Details when Used by the Player.
     */
    @Override 
    public void useItem()
    {
        printDescription();
    }

    /**
     * Attack an entity with a machete.
     *
     * @return the damage made.
     */
    @Override
    public int attack()
    {
        return this.machDmg; //Returns machete damage for damage calculation
    }

    /**
     * Parry the attack of the enemy monster with a machete.
     *
     * @return the damage taken.
     */
    @Override
    public int parrySeq()
    {

        return this.machSeq; //Returns int to adjust parry difficulty
    }

    /**
     * Prints out the description of the machete.
     * 
     * @return description of the machete.
     */
    @Override
    public String printDescription()
    {
        return "A hard-hitting weapon but lacks maneuverability due to its size and weight.";
    }
}

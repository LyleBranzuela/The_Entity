/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

/**
 * Item subclass for the Barbed Bat Weapon.
 *
 * @author lyleb and khoap
 */
public class Bat extends Item
{
    private final int batDmg = 3;
    private final int batSeq = 1;
    
    /**
     * Constructor for the Barbed Bat Weapon Item.
     */
    public Bat()
    {
        super("Bat", 1);
    }

    /**
     * Prints out the Barbed Bat's Details when Used by the Player.
     */
    @Override
    public void useItem()
    {
        printDescription();
    }

    /**
     * Attack an entity with a barbed bat.
     *
     * @return the damage made.
     */
    @Override
    public int attack()
    {
        return batDmg; // Returns damage of the bat for use in damage calculation.
    }

    /**
     * Prints out the description of the barbed bat.
     * 
     * @return description of the bat.
     */
    @Override
    public String printDescription() 
    {
        return "A light wooden barbed bat. Has plenty of maneuverability but lacks damage.";
    }

}

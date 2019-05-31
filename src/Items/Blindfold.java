/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

/**
 * Class for the blindfold item.
 * 
 * @author lyleb and khoap
 */
public class Blindfold extends Item
{

    public boolean worn;

    public Blindfold()
    {
        super("Blindfold", 4);
        this.worn = false;
    }

    @Override
    public void useItem()
    {
        if (this.worn == false)
        {
            this.worn = true;
            System.out.println("You have worn the blindfold.");
        }
        else
        {
            removeBlindfold();
        }

    }

    public void removeBlindfold()
    {
        System.out.println("You have removed the blindfold.");
        this.worn = false;
    }

    @Override
    public String printDescription() {
        return "A blindfold";
    }

    @Override
    public int attack() {
        //Do nothing as blindfold is not a weapon
        return 0;
    }
}

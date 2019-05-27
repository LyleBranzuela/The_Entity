/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stages;

import GameEntities.Player;
import Items.*;
import java.util.Scanner;

/**
 * Class for the Stage 3 of the Game.
 *
 * @author lyleb and khoap
 */
public class Stage_3 extends Stage
{
    /**
     * 
     * @param player 
     */
    public Stage_3(Player player)
    {
        super(player);
    }
    /*
     * Creates content for stage 3
     */
    @Override
    public void initiateStage(Player player)
    {
        // Initating the Variables
        super.stageLevel = 3;
        Scanner scan = new Scanner(System.in);
        String userInput;
        int weaponChoice;
        boolean isValid = false;
        Item barbedBat = new BarbedBat();
        Item dualDag = new Daggers();
        Item machete = new Machete();

        try
        {
            // Continues from stage 2 and prints weapon info
            System.out.println("As you walk down a long hallway, you found a room where police kept confiscated weapons.");
            Thread.sleep(3000);
            System.out.println("Knowing guns will disrupt your hearing as well as alert the Infected, you start digging for something else.");
            System.out.println("");
            Thread.sleep(3000);
            System.out.println("You have Found: ");
            System.out.println("1) Barbed Bat: " + barbedBat.printDescription());
            Thread.sleep(2000);
            System.out.println("2) Dual Daggers: " + dualDag.printDescription());
            Thread.sleep(2000);
            System.out.println("3) Heavy Machete: " + machete.printDescription());
            System.out.println("=============================================================================================");
            Thread.sleep(2000);
            System.out.print("Pick a Weapon [1|2|3]: ");

            do
            {
                userInput = scan.nextLine();
                try
                {
                    weaponChoice = Integer.parseInt(userInput);
                    if (weaponChoice != 1 && weaponChoice != 2 && weaponChoice != 3)
                    {
                        System.out.println("[Invalid input! Please input a number from 1 - 3!]");
                        isValid = false;
                    }
                    else
                    {
                        switch (weaponChoice)
                        {
                            case 1:
                                player.pickupItem(barbedBat);
                                break;
                            case 2:
                                player.pickupItem(dualDag);
                                break;
                            case 3:
                                player.pickupItem(machete);
                                break;
                            default:
                                break;
                        }
                        isValid = true;
                    }
                }
                catch (NumberFormatException e)
                {

                    isValid = false;
                    System.out.println("[Invalid input! Please input a number from 1 - 3]");
                }
            } while (!isValid);  //Validates user input to make sure it is a number between 1 - 3.

            System.out.println("");
            System.out.println("You hear a strange howl followed by loud, inhuman footsteps. The entity has arrived");
            Thread.sleep(3000);
            System.out.println("");
            System.out.println("You think to yourself: If you can kill the entity, this apocalypse will finally be over.");
            Thread.sleep(3000);
            System.out.println("");
            System.out.println("You then cut a piece of your clothes to make a blindfold to protect yourself from the Entity's powers.");
            Thread.sleep(3000);
            System.out.println("");
            System.out.println("=============================================================================================");
            System.out.println("With your new found determination, you walk out of the room and towards the howl.");
            System.out.println("=============================================================================================");

            player.setCurrentStageLevel(new Stage_4(player));
            player.getCurrentStage().initiateStage(player);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt(); // restore interrupted status
        }
    }
}

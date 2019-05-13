/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stages;

import GameEntities.Player;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class for the Stage 1 of the Game. Password by the riddle that needs to be
 * inputted by the user.
 *
 * Player input a number to unlock a padlock to escape their cell
 *
 * @author lyleb and khoap
 */
public class Stage_1 extends Stage
{
    /**
     * 
     * @param player 
     */
    public Stage_1(Player player)
    {
        super(player);
        repaint();
    }
    
    /**
     * Create the content for stage 1.
     * 
     * @param player current player playing;
     */
    @Override
    public void initiateStage(Player player)
    {
        // Iniating the Stage's Variables
        super.stageLevel = 1;
        final int password = 1412;
        int userInput = 0;
        Scanner scan = new Scanner(System.in);

        try
        {
            Scanner skipScan = new Scanner(System.in);
            System.out.print("[Type S to Skip Cutscene | Type Any to Start]: ");
            String selection = skipScan.nextLine();
            System.out.println("============================================================");
            switch (selection)
            {
                case "s":
                case "S":
                    System.out.println("[Skipping Cutscene...]");
                    Thread.sleep(2000);
                    break;
                default:
                    // First Paragraph - Intro
                    System.out.println("A decade after the appearance of an alien entity, humanity is on the brink of extinction.");
                    Thread.sleep(2000);
                    System.out.println("");
                    System.out.println("Anyone or anything that has seen the entity's face have gone insane and committed suicide.");
                    Thread.sleep(2000);
                    System.out.println("");
                    System.out.println("Those who didn't were purposely kept alive and brainwashed by the entity to become Infecteds.");
                    Thread.sleep(2000);
                    System.out.println("");
                    System.out.println("These Infecteds are then sent out to hunt down any other surivors.");
                    Thread.sleep(2000);
                    System.out.println("");
                    System.out.println("You and several others learnt to navigate with sounds to survive this apocalypse");
                    Thread.sleep(2000);
                    System.out.println("");
                    System.out.println("While searching for supplies for your community, you were captured by a group of Infected.");
                    Thread.sleep(2000);
                    
                  
                    
                    Thread.sleep(3000);

                    // Second Paragraph - Eavesdropping
                    System.out.println();
                    System.out.println("You wake up to find yourself locked in a prison cell.");
                    Thread.sleep(2000);
                    System.out.println("You hear a voice coming from the door. ");
                    Thread.sleep(2000);
                    System.out.println("");
                    Thread.sleep(2000);
                    System.out.print("\n...");
                    Thread.sleep(3000);
                    System.out.print("Someone talking.\n");
                    Thread.sleep(2000);
                    System.out.println("[\"We've captured " + player.getName() + ". I think he's one of the kids from the hidden community.\"]");
                    Thread.sleep(4000);
                    System.out.println("As you try to collect yourself, you hear a response from another guard...");
                    Thread.sleep(4000);
                    System.out.println("[\"Perfect. Contact the Entity, we can corrupt this " + player.getName() + " kid and use him to find the others\"]");
                    Thread.sleep(5000);
                    System.out.println("The conversation ends and you hear faint footsteps going further away.");
                    Thread.sleep(3000);
                    System.out.println("Realizing the Entity is coming to corrupt you, you start searching for a way to escape...");
                    Thread.sleep(3000);

                    // Third Paragraph - Rummaging Through The Room
                    System.out.println();
                    System.out.println("As you rummage through the room, you have found a note under the carpet.");
                    Thread.sleep(3000);
                    System.out.println("You notice the cell's door has a lock that requires a 4 digit code to unlock.");
                    Thread.sleep(3000);
                    System.out.println("The note contains a riddle that might be the passcode, solve it and get out before the entity arrives: ");
                    Thread.sleep(3000);
            }
            // Fourth Paragraph - The Note
            System.out.println();
            System.out.println("The note suggests it's a four digit password:");
            Thread.sleep(2000);
            System.out.println("- The second digit is four times the first digit.");
            Thread.sleep(2000);
            System.out.println("- The third digit is three less than the second digit.");
            Thread.sleep(2000);
            System.out.println("- The the fourth digit is half of the second digit and two times the third digit.");
            Thread.sleep(2000);
            System.out.println("- The passcode must be answered within 5 minutes.");
            Thread.sleep(2000);
            
            
            int tries = 0;
            long limit = 300000L;
            long startTime = System.currentTimeMillis();
            while (userInput != password && tries != 3)
            {
                try
                {
                    System.out.println("==============================================================================");
                    System.out.print("Enter the passcode: ");
                    userInput = scan.nextInt();
                    Thread.sleep(1000);
                    System.out.print("...");
                    Thread.sleep(2000);
                    
                    if ((startTime + limit) < System.currentTimeMillis())
                    {
                        System.out.println("5 Minutes have passed.");
                        System.out.println("The guards get suspicious and looks inside the room.");
                        System.out.println();
                        //GameOverScreen.printGameOverScreen(player, "You have been detected!");
                    }
                    else if (userInput != password)
                    {
                        tries++;
                        System.out.print("The passcode was wrong, " + tries + " of the 3 Red Dots glow.\n");
                    }
                }
                // If the user inputted a passcode that isn't a number.
                catch (InputMismatchException e)
                {
                    System.out.print("This is a numeric passcode!\n");
                    scan = new Scanner(System.in);
                }
                finally
                {
                    if (tries == 3)
                    {
                        System.out.print("All the red dots on the passcode glows.\n");
                        Thread.sleep(2000);
                        System.out.println("The alarm goes off and the guard notices you.");
                        System.out.println();
                        //GameOverScreen.printGameOverScreen(player, "You have been detected!");
                    }
                }
            }
            System.out.print("\n\n======================================================================================================\n");
            System.out.println("The dots flashed green and the door opened. You peek through the door to spot three patrolling guards.");
            System.out.println("======================================================================================================");
            // Sets the stage level into Stage 2 and Iniate it with the Player Object
            player.setCurrentStageLevel(new Stage_2(player));
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt(); // restore interrupted status
        }
    }

}

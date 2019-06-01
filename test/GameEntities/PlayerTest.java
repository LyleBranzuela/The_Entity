/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntities;

import Items.*;
import Stages.Stage;
import Stages.Stage_1;
import Stages.Stage_2;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Entity Movement.
 * 
 * @author lyleb and khoap
 */
public class PlayerTest
{
    private Player testPlayer;
    public PlayerTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
        this.testPlayer = new Player("testPlayer");
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of pickupItem method, of class Player.
     */
    @Test
    public void testPickupItem()
    {
        System.out.println("pickupItem");
        Item item = new Blindfold();
        this.testPlayer.pickupItem(item);
        assertEquals(true, this.testPlayer.hasBlindfold);
    }
    
    /**
     * Test of pickupItem (WEAPON) method, of class Player.
     */
    @Test
    public void testPickupItemWeapon()
    {
        System.out.println("pickupWeaponItem");
        Item item = new Bat();
        this.testPlayer.pickupItem(item);
        assertEquals(item, this.testPlayer.getWeapon());
    }


    /**
     * Test of getWeapon method, of class Player.
     */
    @Test
    public void testGetWeapon()
    {
        System.out.println("getWeapon");
        Item item = new Daggers();
        this.testPlayer.pickupItem(item);
        Item result = this.testPlayer.getWeapon();
        assertEquals(item, result);
    }

    /**
     * Test of setCurrentStageLevel method, of class Player.
     */
    @Test
    public void testSetCurrentStageLevel()
    {
        System.out.println("setCurrentStageLevel");
        Stage stage = new Stage_1();
        this.testPlayer.setCurrentStageLevel(stage);
        Stage result = this.testPlayer.getCurrentStage();
        assertEquals(stage, result);
    }

    /**
     * Test of getCurrentStage method, of class Player.
     */
    @Test
    public void testGetCurrentStage()
    {
        System.out.println("getCurrentStage");
        Stage expResult = new Stage_1();
        this.testPlayer.setCurrentStageLevel(expResult);
        Stage result = this.testPlayer.getCurrentStage();
        assertEquals(expResult, result);
    }
}

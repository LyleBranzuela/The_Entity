/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntities;

import Items.Item;
import Stages.Stage;
import java.awt.Graphics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lyleb
 */
public class PlayerTest
{
    
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
        Item item = null;
        Player instance = null;
        instance.pickupItem(item);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWeapon method, of class Player.
     */
    @Test
    public void testGetWeapon()
    {
        System.out.println("getWeapon");
        Player instance = null;
        Item expResult = null;
        Item result = instance.getWeapon();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentStageLevel method, of class Player.
     */
    @Test
    public void testSetCurrentStageLevel()
    {
        System.out.println("setCurrentStageLevel");
        Stage stage = null;
        Player instance = null;
        instance.setCurrentStageLevel(stage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentStage method, of class Player.
     */
    @Test
    public void testGetCurrentStage()
    {
        System.out.println("getCurrentStage");
        Player instance = null;
        Stage expResult = null;
        Stage result = instance.getCurrentStage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of draw method, of class Player.
     */
    @Test
    public void testDraw()
    {
        System.out.println("draw");
        Graphics g = null;
        Player instance = null;
        instance.draw(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

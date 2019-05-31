/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntities;

import java.awt.Point;
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
public class EntityMovementTest
{
    
    public EntityMovementTest()
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
     * Test of setMovementSpeed method, of class EntityMovement.
     */
    @Test
    public void testSetMovementSpeed()
    {
        System.out.println("setMovementSpeed");
        int movementSpeed = 0;
        EntityMovement instance = null;
        instance.setMovementSpeed(movementSpeed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMovementSpeed method, of class EntityMovement.
     */
    @Test
    public void testGetMovementSpeed()
    {
        System.out.println("getMovementSpeed");
        EntityMovement instance = null;
        int expResult = 0;
        int result = instance.getMovementSpeed();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMovement method, of class EntityMovement.
     */
    @Test
    public void testGetXMovement()
    {
        System.out.println("getXMovement");
        EntityMovement instance = null;
        int expResult = 0;
        int result = instance.getXMovement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getYMovement method, of class EntityMovement.
     */
    @Test
    public void testGetYMovement()
    {
        System.out.println("getYMovement");
        EntityMovement instance = null;
        int expResult = 0;
        int result = instance.getYMovement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointMovement method, of class EntityMovement.
     */
    @Test
    public void testGetPointMovement()
    {
        System.out.println("getPointMovement");
        EntityMovement instance = null;
        Point expResult = null;
        Point result = instance.getPointMovement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLocation method, of class EntityMovement.
     */
    @Test
    public void testSetLocation()
    {
        System.out.println("setLocation");
        int x = 0;
        int y = 0;
        EntityMovement instance = null;
        instance.setLocation(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveEntity method, of class EntityMovement.
     */
    @Test
    public void testMoveEntity()
    {
        System.out.println("moveEntity");
        int direction = 0;
        EntityMovement instance = null;
        instance.moveEntity(direction);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

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
    private EntityMovement emt;
    
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
        this.emt = new EntityMovement(0, 0); 
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
        int movementSpeed = 2;
        int expResult = 2;
        
        emt.setMovementSpeed(movementSpeed);
        int result = emt.getMovementSpeed();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getMovementSpeed method, of class EntityMovement.
     */
    @Test
    public void testGetMovementSpeed()
    {
        System.out.println("getMovementSpeed");
        int expResult = 2;
        int result = emt.getMovementSpeed();
        assertEquals(expResult, result);
    }

    /**
     * Test of getXMovement method, of class EntityMovement.
     */
    @Test
    public void testGetXMovement()
    {
        System.out.println("getXMovement");
        int expResult = 0;
        int result = emt.getXMovement();
        assertEquals(expResult, result);
    }

    /**
     * Test of getYMovement method, of class EntityMovement.
     */
    @Test
    public void testGetYMovement()
    {
        System.out.println("getYMovement");
        int expResult = 0;
        int result = emt.getXMovement();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPointMovement method, of class EntityMovement.
     */
    @Test
    public void testGetPointMovement()
    {
        System.out.println("getPointMovement");
        Point expResult = new Point(0, 0);
        Point result = emt.getPointMovement();
        assertEquals(expResult, result);
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

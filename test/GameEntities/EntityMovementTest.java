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
 * Test class for Entity Movement.
 * 
 * @author lyleb and khoap
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
        int movementSpeed = 2;
        this.emt.setMovementSpeed(movementSpeed);
        int result = this.emt.getMovementSpeed();
        assertEquals(movementSpeed, result);
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
        this.emt.setLocation(x, y);
        assertEquals(x, this.emt.getXMovement());
        assertEquals(y, this.emt.getYMovement());
    }

    /**
     * Test of moveEntity method (LEFT), of class EntityMovement.
     */
    @Test
    public void testMoveEntityLeft()
    {
        System.out.println("moveEntityLeft");
        int direction = EntityMovement.LEFT;
        this.emt.setLocation(0, 0);
        this.emt.moveEntity(direction);
        
        int expectedX = -6;
        int expectedY = 0;
        int resultX = this.emt.getXMovement();
        int resultY = this.emt.getYMovement();
        
        assertEquals(expectedX, resultX);
        assertEquals(expectedY, resultY);
    }
    
    /**
     * Test of moveEntity method (RIGHT), of class EntityMovement.
     */
    @Test
    public void testMoveEntityRight()
    {
        System.out.println("moveEntityRight");
        int direction = EntityMovement.RIGHT;
        this.emt.setLocation(0, 0);
        this.emt.moveEntity(direction);
        
        int expectedX = 6;
        int expectedY = 0;
        int resultX = this.emt.getXMovement();
        int resultY = this.emt.getYMovement();
        
        assertEquals(expectedX, resultX);
        assertEquals(expectedY, resultY);
    }
    
    
    /**
     * Test of moveEntity (FORWARD) method, of class EntityMovement.
     */
    @Test
    public void testMoveEntityForward()
    {
        System.out.println("moveEntityForward");
        int direction = EntityMovement.FORWARD;
        this.emt.setLocation(0, 0);
        this.emt.moveEntity(direction);
        
        int expectedX = 0;
        int expectedY = -6;
        int resultX = this.emt.getXMovement();
        int resultY = this.emt.getYMovement();
        
        assertEquals(expectedX, resultX);
        assertEquals(expectedY, resultY);
    }
    
    /**
     * Test of moveEntity method (BACKWARD), of class EntityMovement.
     */
    @Test
    public void testMoveEntityDownBackward()
    {
        System.out.println("moveEntityBackward");
        int direction = EntityMovement.BACKWARD;
        this.emt.setLocation(0, 0);
        this.emt.moveEntity(direction);
        
        int expectedX = 0;
        int expectedY = 6;
        int resultX = this.emt.getXMovement();
        int resultY = this.emt.getYMovement();
        
        assertEquals(expectedX, resultX);
        assertEquals(expectedY, resultY);
    }
}

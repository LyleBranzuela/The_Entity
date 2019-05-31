/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GameEntities.Player;
import java.sql.ResultSet;
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
public class DatabaseManagerTest
{
    
    public DatabaseManagerTest()
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
     * Test of connectToPlayerDatabase method, of class DatabaseManager.
     */
    @Test
    public void testConnectToPlayerDatabase()
    {
        System.out.println("connectToPlayerDatabase");
        DatabaseManager.connectToPlayerDatabase();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPlayerSaveDatabase method, of class DatabaseManager.
     */
    @Test
    public void testCreatePlayerSaveDatabase()
    {
        System.out.println("createPlayerSaveDatabase");
        DatabaseManager.createPlayerSaveDatabase();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearPlayerDatabase method, of class DatabaseManager.
     */
    @Test
    public void testClearPlayerDatabase()
    {
        System.out.println("clearPlayerDatabase");
        DatabaseManager.clearPlayerDatabase();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllPlayers method, of class DatabaseManager.
     */
    @Test
    public void testGetAllPlayers()
    {
        System.out.println("getAllPlayers");
        ResultSet expResult = null;
        ResultSet result = DatabaseManager.getAllPlayers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of databaseToPlayer method, of class DatabaseManager.
     */
    @Test
    public void testDatabaseToPlayer()
    {
        System.out.println("databaseToPlayer");
        String playerName = "";
        int stageLevel = 0;
        boolean hasBlindfold = false;
        int itemID = 0;
        Player expResult = null;
        Player result = DatabaseManager.databaseToPlayer(playerName, stageLevel, hasBlindfold, itemID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playerToDatabase method, of class DatabaseManager.
     */
    @Test
    public void testPlayerToDatabase()
    {
        System.out.println("playerToDatabase");
        Player player = null;
        String expResult = "";
        String result = DatabaseManager.playerToDatabase(player);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadPlayerFromDatabase method, of class DatabaseManager.
     */
    @Test
    public void testLoadPlayerFromDatabase()
    {
        System.out.println("loadPlayerFromDatabase");
        String playerName = "";
        Player expResult = null;
        Player result = DatabaseManager.loadPlayerFromDatabase(playerName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of savePlayerToDatabase method, of class DatabaseManager.
     */
    @Test
    public void testSavePlayerToDatabase()
    {
        System.out.println("savePlayerToDatabase");
        Player player = null;
        boolean newPlayer = false;
        DatabaseManager.savePlayerToDatabase(player, newPlayer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePlayerFromDatabase method, of class DatabaseManager.
     */
    @Test
    public void testDeletePlayerFromDatabase()
    {
        System.out.println("deletePlayerFromDatabase");
        String playerName = "";
        DatabaseManager.deletePlayerFromDatabase(playerName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

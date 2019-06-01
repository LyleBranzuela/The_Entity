/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GameEntities.Player;
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
     * Test of databaseToPlayer method, of class DatabaseManager.
     */
    @Test
    public void testDatabaseToPlayer()
    {
        System.out.println("databaseToPlayer");
        String expPlayerName = "testPlayer";
        int expStageLevel = 1;
        boolean expHasBlindfold = false;
        int expItemID = 0;
        
        Player result = DatabaseManager.databaseToPlayer(expPlayerName, expStageLevel, expHasBlindfold, expItemID);
        String playerName = result.getName();
        int stageLevel = result.getCurrentStage().determineStageLevel(result.getCurrentStage());
        boolean hasBlindFold = result.hasBlindfold;
        int itemID = result.getWeapon() == null ? 0 : result.getWeapon().getItemID();
        
        assertEquals(expPlayerName, playerName);
        assertEquals(expStageLevel, stageLevel);
        assertEquals(expHasBlindfold, hasBlindFold);
        assertEquals(expItemID, itemID);
    }

    /**
     * Test of playerToDatabase method, of class DatabaseManager.
     */
    @Test
    public void testPlayerToDatabase()
    {
        System.out.println("playerToDatabase");
        Player player = new Player("testPlayer");
        String expResult = "\'testPlayer\', 1, 0, 0";
        String result = DatabaseManager.playerToDatabase(player);
        assertEquals(expResult, result);
    }
}

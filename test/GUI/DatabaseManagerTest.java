/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.DatabaseManager.databaseToPlayer;
import GameEntities.Player;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        // Connect to the To the PlayerDatabase();
        DatabaseManager.connectToPlayerDatabase();
        DatabaseManager.createPlayerSaveDatabase();
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of load player and save player from the database method, of class DatabaseManager.
     */
    @Test
    public void testSaveLoadFunctionsFromDatabase()
    {
        System.out.println("testSaveLoadFunctionsFromDatabase");
        // Save And Load the player
        Player expectedPlayer = new Player("loadTestPlayer");
        DatabaseManager.savePlayerToDatabase(expectedPlayer, true);
        
        Player result = DatabaseManager.loadPlayerFromDatabase(expectedPlayer.getName());
        String playerName = result.getName();
        int stageLevel = result.getCurrentStage().determineStageLevel(result.getCurrentStage());
        boolean hasBlindFold = result.hasBlindfold;
        
        // Test if it's the same as the player that was saved.
        assertEquals(expectedPlayer.getName(), playerName);
        assertEquals(expectedPlayer.getCurrentStage().getStageLevel(), stageLevel);
        assertEquals(expectedPlayer.hasBlindfold, hasBlindFold);
        assertEquals(expectedPlayer.getWeapon(), result.getWeapon());
        
        // Delete player after testing
        DatabaseManager.deletePlayerFromDatabase(playerName);
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

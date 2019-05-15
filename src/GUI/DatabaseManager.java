/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GameEntities.Player;
import Items.BarbedBat;
import Items.Daggers;
import Items.Machete;
import Stages.Stage_1;
import Stages.Stage_2;
import Stages.Stage_3;
import Stages.Stage_4;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class for handling Databases.
 *
 * @author lyleb and khoap
 */
public class DatabaseManager
{

    private static Connection conn = null;
    private static final String URL = "jdbc:derby://localhost:1527/PlayerDatabase;create=true";
    private static final String USERNAME = "entity";
    private static final String PASSWORD = "entity";

    /**
     * Connects to the player database if it still hasn't yet.
     */
    public static void connectToPlayerDatabase()
    {
        try
        {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates the PlayerSaves table if it didn't exist yet.
     */
    public static void createPlayerSaveDatabase()
    {
        try
        {
            DatabaseMetaData dbmd = conn.getMetaData();
            Statement statement = conn.createStatement();
            // Check if the table already exists
            ResultSet tables = dbmd.getTables(null, null, "PLAYERSAVES", null);
            if (!tables.next())
            {
                // statement.executeUpdate("DROP TABLE PLAYERSAVES");
                String sqlCreateTable = "CREATE TABLE PLAYERSAVES (PLAYERNAME VARCHAR(20), CURRENTSTAGE INT, HASBLINDFOLD INT, ITEM_ID INT, SAVEDATE TIMESTAMP)";
                statement.executeUpdate(sqlCreateTable);
            }
        }
        catch (SQLException ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    /**
     * Clears the database of the players.
     */
    public static void clearPlayerDatabase() {
        try
        {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DROP TABLE PLAYERSAVES");
        }
        catch (SQLException ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    /**
     * Returns a result set about all the players in the database.
     * 
     * @return a result set of the database.
     */
    public static ResultSet getAllPlayers()
    {
        ResultSet rs = null;
        try
        {
            Statement statement = conn.createStatement();
            String sqlQuery = "SELECT * FROM PLAYERSAVES ORDER BY SAVEDATE";
            rs = statement.executeQuery(sqlQuery);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rs);
    }

    /**
     * Converts a database string version to a player object version.
     * 
     * @param playerName the name of the player object.
     * @param stageLevel current stage level of the player object.
     * @param hasBlindfold does the player have a blindfold.
     * @param itemID what weapon is the player currently have.
     * @return the generated player.
     */
    public static Player databaseToPlayer(String playerName, int stageLevel, boolean hasBlindfold, int itemID)
    {
        Player generatedPlayer = new Player(playerName);

        generatedPlayer.hasBlindfold = hasBlindfold;

        // Set Item from Database to Player
        switch (itemID)
        {
            case 1:
                generatedPlayer.pickupItem(new BarbedBat());
                break;
            case 2:
                generatedPlayer.pickupItem(new Daggers());
                break;
            case 3:
                generatedPlayer.pickupItem(new Machete());
                break;
            default:
                break;
        }
        
        // Set Stage from database to the Player
        switch (stageLevel)
        {
            case 1:
                generatedPlayer.setCurrentStageLevel(new Stage_1(generatedPlayer));
                break;
            case 2:
                generatedPlayer.setCurrentStageLevel(new Stage_2(generatedPlayer));
                break;
            case 3:
                generatedPlayer.setCurrentStageLevel(new Stage_3(generatedPlayer));
                break;
            case 4:
                generatedPlayer.setCurrentStageLevel(new Stage_4(generatedPlayer));
                break;
            default:
                generatedPlayer.setCurrentStageLevel(new Stage_1(generatedPlayer));
                break;
        }

        return (generatedPlayer);
    }

    /**
     * Converts the player object into a string, that can be used in the database.
     * 
     * @param player to be converted to a string.
     * @return string version of the player object.
     */
    public static String playerToDatabase(Player player)
    {
        String playerName = "\'" + player.getName() + "\'";
        int stageLevel = player.getCurrentStage().determineStageLevel(player.getCurrentStage());
        // 1 - True, 0 - False
        int hasBlindFold = player.hasBlindfold ? 1 : 0;
        // 0 - No Items, 1 - Barbed Bat, 2 - Dual Daggers, 3 - Machete
        int itemID = player.getWeapon() == null ? 0 : player.getWeapon().getItemID();

        return (playerName + ", " + stageLevel + ", " + hasBlindFold + ", " + itemID);
    }

    /**
     * Loads player from the database based on their name.
     * 
     * @param playerName the name of the player to be retrieved.
     * @return the player object from the database.
     */
    public static Player loadPlayerFromDatabase(String playerName)
    {
        Player loadedPlayer = new Player("");
        ResultSet rs = null;
        try
        {
            String loadPlayerSQL = "SELECT * FROM PLAYERSAVES WHERE PLAYERNAME = ?";
            PreparedStatement pstmt = conn.prepareStatement(loadPlayerSQL);
            pstmt.setString(1, playerName);
            rs = pstmt.executeQuery();            
            while (rs.next()) {
                String loadedName = rs.getString("PLAYERNAME");
                int stageLevel = rs.getInt("CURRENTSTAGE");
                boolean hasBlindFold = rs.getBoolean("HASBLINDFOLD");
                int itemID = rs.getInt("ITEM_ID");
                
                loadedPlayer = databaseToPlayer(loadedName, stageLevel, hasBlindFold, itemID);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return loadedPlayer;
    }

    /**
     * Saves the player to the PlayerDatabase.
     * 
     * @param player which player to save.
     * @param newPlayer whether it's a new player or not.
     */
    public static void savePlayerToDatabase(Player player, boolean newPlayer)
    {
        try
        {
            // Get current System time
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String getPlayerDetails;
            PreparedStatement pstmt;
            
            // If it's a new player
            if (newPlayer)
            {
                getPlayerDetails = "INSERT INTO PLAYERSAVES VALUES (" + playerToDatabase(player) + ", ?)";
                pstmt = conn.prepareStatement(getPlayerDetails);
                pstmt.setTimestamp(1, timestamp);
            }
            // Update the player database
            else
            {
                int hasBlindFold = player.hasBlindfold ? 1 : 0;
                int itemID = player.getWeapon() == null ? 0 : player.getWeapon().getItemID();
                int stageLevel = player.getCurrentStage().determineStageLevel(player.getCurrentStage());
                getPlayerDetails
                        = "UPDATE PLAYERSAVES SET CURRENTSTAGE = ?, HASBLINDFOLD = ?, ITEM_ID = ?, SAVEDATE = ? WHERE PLAYERNAME = \'" + player.getName() + "\'";
                pstmt = conn.prepareStatement(getPlayerDetails);
                pstmt.setInt(1, stageLevel);
                pstmt.setInt(2, hasBlindFold);
                pstmt.setInt(3, itemID);
                pstmt.setTimestamp(4, timestamp);
            }
            pstmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Deletes a specified player from the database.
     * 
     * @param playerName name of the player to be deleted.
     */
    public static void deletePlayerFromDatabase(String playerName)
    {
        try
        {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM PLAYERSAVES WHERE PLAYERNAME = ?");
            pstmt.setString(1, playerName);

            pstmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

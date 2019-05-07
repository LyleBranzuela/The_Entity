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
     *
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
     * 
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
     *
     * @return
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
     *
     * @param playerName
     * @param stageLevel
     * @param hasBlindfold
     * @param itemID
     * @return the generated player.
     */
    public static Player databaseToPlayer(String playerName, int stageLevel, boolean hasBlindfold, int itemID)
    {
        Player generatedPlayer = new Player(playerName);

        // Set Stage from database to the Player
        switch (stageLevel)
        {
            case 1:
                generatedPlayer.setCurrentStageLevel(new Stage_1());
                break;
            case 2:
                generatedPlayer.setCurrentStageLevel(new Stage_2());
                break;
            case 3:
                generatedPlayer.setCurrentStageLevel(new Stage_3());
                break;
            case 4:
                generatedPlayer.setCurrentStageLevel(new Stage_4());
                break;
            default:
                generatedPlayer.setCurrentStageLevel(new Stage_1());
                break;
        }

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

        return (generatedPlayer);
    }

    /**
     *
     * @param player
     * @return
     */
    public static String playerToDatabase(Player player)
    {
        // CREATE
        // A 
        // NULL 
        // STATEMENT
        // FOR TURNING PLAYER TO A DATABASE
        String playerName = "\'" + player.getName() + "\'";
        int stageLevel = player.getCurrentStage().determineStageLevel(player.getCurrentStage());
        boolean blindfoldCheck = player.hasBlindfold;
        int hasBlindFold = blindfoldCheck ? 1 : 0;
        int itemID = player.getWeapon() == null ? 0 : player.getWeapon().getItemID();

        return (playerName + ", " + stageLevel + ", " + hasBlindFold + ", " + itemID);
    }

    /**
     *
     * @param playerName
     * @return
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
     *
     * @param player
     */
    public static void savePlayerToDatabase(Player player)
    {
        try
        {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            
            
            java.util.Date uDate = new java.util.Date();
            // Convert Jave Date to SQL Date
            java.sql.Date sDate = new java.sql.Date(uDate.getTime());
            String getPlayerDetails
                    = "INSERT INTO PLAYERSAVES VALUES (" + playerToDatabase(player) + ", ?)";

            System.out.println(getPlayerDetails);
            PreparedStatement pstmt = conn.prepareStatement(getPlayerDetails);
            //pstmt.setDate(1, sDate);
            pstmt.setTimestamp(1, timestamp);
            pstmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param playerName
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

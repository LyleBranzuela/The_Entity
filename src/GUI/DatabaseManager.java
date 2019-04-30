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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lyleb and khoap
 */
public class DatabaseManager
{
    private Connection conn = null;
    private final String url = "jdbc:derby://localhost:1527/TheEntityDB; create=true"; 
    private final String username = "entity"; 
    private final String password = "entity"; 

    /**
     * 
     */
    public void connectToPlayerDatabase()
    {
        try
        {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 
     */
    public void createPlayerSaveDatabase()
    {
        try
        {
            DatabaseMetaData dbmd = this.conn.getMetaData();
            Statement statement = this.conn.createStatement();
            ResultSet tables = dbmd.getTables(null, null, "PLAYERSAVES", null);
            if (tables.next())
            {
                statement.executeUpdate("DROP TABLE PLAYERSAVES");
            }
            String sqlCreateTable = "CREATE TABLE PLAYERSAVES (PLAYERNAME VARCHAR(20), CURRENTSTAGE INT, HASBLINDFOLD INT, ITEM_ID INT, SAVEDATE DATE)";
            statement.executeUpdate(sqlCreateTable);
        }
        catch (SQLException ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    public Player loadPlayerFromDatabase() {
        Player loadedPlayer = new Player(url);

        return loadedPlayer;
    }
    
        

    /**
     *
     * @param playerName
     * @param stageLevel
     * @param hasBlindfold
     * @param itemID
     * @return the generated player.
     */
    public Player databaseToPlayer(String playerName, int stageLevel, boolean hasBlindfold, int itemID)
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
        switch(itemID)
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
        
        return(generatedPlayer);
    }

    /**
     * 
     * @param player
     * @return 
     */
    public String playerToDatabase(Player player)
    {
        String playerName = "\'" + player.getName() + "\'";
        int stageLevel = player.getCurrentStage().determineStageLevel(player.getCurrentStage());
        boolean blindfoldCheck = player.hasBlindfold;
        int hasBlindFold = blindfoldCheck ? 1:0;
        int itemID = player.getWeapon().getItemID();
 
        return "("+ playerName + ", " + stageLevel + ", " + hasBlindFold + ", " + itemID + ")";
    }
    /**
     * 
     * @param player
     */
    public void savePlayerToDatabase(Player player) {
        
    }
}

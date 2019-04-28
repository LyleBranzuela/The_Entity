/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GameEntities.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lyleb and khoap
 */
public class DatabaseManager
{
    Connection conn = null;
    String url = "jdbc:derby://localhost:1527/TheEntityDB; create=true"; 
    String username = "entity"; 
    String password = "entity"; 

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
     * Loads the player from the database based on the index.
     * 
     * @param index
     * @return 
     */
    public Player loadPlayerFromDatabase(String player) {
        return null;
    }
    
    /**
     * 
     * @param player
     */
    public void savePlayerToDatabase(Player player) {
        
    }
}

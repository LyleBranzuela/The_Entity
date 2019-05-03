/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuPanels;

import GUI.DatabaseManager;
import GUI.DesignAttributes;
import GUI.UtilityMethods;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lyleb and khoap
 */
public class LoadMenu extends JPanel implements ActionListener, ListSelectionListener
{
    private JTable playerJTable;
    private JLabel loadGameLabel;
    private JPanel loadMenuListPanel, buttonPanel;
    private JButton loadSaveButton, deleteSaveButton, backButton;
    private DesignAttributes designAttributes;
    private DefaultTableModel tableModel;
    private Object[][] data;
    
    public LoadMenu(){
        super(new BorderLayout());
        this.designAttributes = new DesignAttributes();
        
        // Text of Load Screen on the Top
        this.loadGameLabel = new JLabel("Load Game");
        this.loadGameLabel.setFont(new Font("Tahoma", Font.BOLD, 64));
        this.loadGameLabel.setForeground(this.designAttributes.primaryColor);
        
        // Loads the Selected Save
        this.loadSaveButton = UtilityMethods.generateButton("Load Save", 32, 
                this.designAttributes.secondaryColor, null, true);
        this.loadSaveButton.setEnabled(false);
        this.loadSaveButton.addActionListener(this);
        
        // Deletes the Selected Save
        this.deleteSaveButton = UtilityMethods.generateButton("Delete Save", 32, 
                this.designAttributes.secondaryColor, null, true);
        this.deleteSaveButton.addActionListener(this);
        this.deleteSaveButton.setEnabled(false);
        
        // Goes back to the main menu
        this.backButton = UtilityMethods.generateButton("Back", 32, 
                this.designAttributes.secondaryColor, null, true);
        this.backButton.addActionListener(this);
        
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.loadSaveButton);
        buttonGroup.add(this.deleteSaveButton);
        buttonGroup.add(this.backButton);
        
        // Adding the Buttons into a panel so that they dont become vertical
        this.buttonPanel = new JPanel();
        this.buttonPanel.add(this.loadSaveButton);
        this.buttonPanel.add(this.deleteSaveButton);
        this.buttonPanel.add(this.backButton);
        
        // Creating the Save List Table
        String[] columnNames = {"Player Name", "Stage Level", "Date"};
        this.tableModel = new DefaultTableModel(this.data, columnNames);
        this.playerJTable = new JTable(this.tableModel);
        JScrollPane tableScrollPane = new JScrollPane();
        tableScrollPane.setViewportView(this.playerJTable);
        updatePlayerSaves();
        
        this.loadMenuListPanel = new JPanel();
        this.loadMenuListPanel.add(this.loadGameLabel);
        this.loadMenuListPanel.add(tableScrollPane);
        this.loadMenuListPanel.add(this.buttonPanel);
        this.loadMenuListPanel.setLayout(new BoxLayout(this.loadMenuListPanel, BoxLayout.Y_AXIS));
        this.loadMenuListPanel.setBorder(this.designAttributes.marginBorder);
        
        add(this.loadMenuListPanel, BorderLayout.CENTER);
    }

    /**
     * 
     */
    public void updatePlayerSaves()  
    {
        // Clear Row
        int rowCount = this.tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            this.tableModel.removeRow(i);
        }
        
        // Connect to the Database
        ResultSet rs = DatabaseManager.getAllPlayers();
        // Add all the rows back
        try
        {
            while (rs.next())
            {
                 String playerName = rs.getString("PLAYERNAME");
                 int stageLevel = rs.getInt("CURRENTSTAGE");
                 Date saveDate = new Date(rs.getDate("SAVEDATE").getTime());
                 
                 Object[] rowData = {playerName, stageLevel, saveDate};
                 this.tableModel.addRow(rowData);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(LoadMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

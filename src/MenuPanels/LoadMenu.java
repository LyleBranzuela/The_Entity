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
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * JPanel for the load game screen.
 * 
 * @author lyleb and khoap
 */
public class LoadMenu extends JPanel implements ActionListener, ListSelectionListener
{

    private JTable playerJTable;
    private JPanel loadMenuListPanel, buttonPanel;
    private JButton loadSaveButton, deleteSaveButton, refreshSavesButton, backButton;
    private DesignAttributes designAttributes;
    private DefaultTableModel tableModel;
    private Object[][] data;

    /**
     * Constructor for the load game screen panel.
     */
    public LoadMenu()
    {
        super(new BorderLayout());
        this.designAttributes = new DesignAttributes();

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

        // Refreshes the Save List
        this.refreshSavesButton = UtilityMethods.generateButton("Refresh", 32,
                this.designAttributes.secondaryColor, null, true);
        this.refreshSavesButton.addActionListener(this);

        // Goes back to the main menu
        this.backButton = UtilityMethods.generateButton("Back", 32,
                this.designAttributes.secondaryColor, null, true);
        this.backButton.addActionListener(this);
        this.backButton.setRolloverEnabled(true);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.loadSaveButton);
        buttonGroup.add(this.deleteSaveButton);
        buttonGroup.add(this.refreshSavesButton);
        buttonGroup.add(this.backButton);

        // Adding the Buttons into a panel so that they dont become vertical
        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(Color.BLACK);
        this.buttonPanel.add(this.loadSaveButton);
        this.buttonPanel.add(this.deleteSaveButton);
        this.buttonPanel.add(this.refreshSavesButton);
        this.buttonPanel.add(this.backButton);
        this.buttonPanel.setBorder(this.designAttributes.createMarginBorder(8, 0, 8, 16));

        // Creating the Save List Table
        String[] columnNames =
        {
            "Player Name", "Stage Level", "Date"
        };
        this.tableModel = new DefaultTableModel(this.data, columnNames)
        {
            /**
             * Makes all the cells not editable.
             *
             * @param row which row.
             * @param column which column.
             * @return makes all cells not editable, no matter row or column.
             */
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        this.playerJTable = new JTable(this.tableModel);
        this.playerJTable.getSelectionModel().addListSelectionListener(this);
        this.playerJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Set the columns of the table to be unmovable or unorderable
        this.playerJTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane tableScrollPane = new JScrollPane();
        tableScrollPane.setViewportView(this.playerJTable);
        updatePlayerSaves();

        // Making The Title Border
        Border titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Load Screen", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 58), this.designAttributes.primaryColor);

        // Add them all together
        this.loadMenuListPanel = new JPanel();
        this.loadMenuListPanel.setBorder(new CompoundBorder(titledBorder, this.designAttributes.marginBorder));
        this.loadMenuListPanel.setBackground(Color.BLACK);
        this.loadMenuListPanel.add(tableScrollPane);
        this.loadMenuListPanel.setLayout(new BoxLayout(this.loadMenuListPanel, BoxLayout.Y_AXIS));

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Escape");
        
        // Customized Action for pressing Escape
        Action escapeAction = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
                if (PanelManager.backToMainMenu)
                {
                    cl.show(PanelManager.menuCardPanel, "MAINMENU");
                }
                else
                {
                    cl.show(PanelManager.menuCardPanel, "MIDGAMEMENU");
                }
            }
        };
        // Adding an action map to the input map
        this.getActionMap().put("Escape", escapeAction);

        add(this.loadMenuListPanel, BorderLayout.CENTER);
        add(this.buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Updates the player save table based on the database
     */
    public void updatePlayerSaves()
    {
        // Clear Row
        int rowCount = this.tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--)
        {
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
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy [hh:mm:ss a]");
                Timestamp saveDate = rs.getTimestamp("SAVEDATE");

                Object[] rowData =
                {
                    playerName, stageLevel, sdf.format(saveDate)
                };
                this.tableModel.addRow(rowData);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(LoadMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(this.tableModel);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(2, SortOrder.DESCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
        this.playerJTable.setRowSorter(sorter);
    }

    /**
     * Listens for actions for the JPanel's buttons.
     *
     * @param e source of the event.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
        int selectedRow = this.playerJTable.getSelectedRow();
        // Loads the Selected Save
        if (source == this.loadSaveButton)
        {
            String playerName = (String) this.tableModel.getValueAt(this.playerJTable.convertRowIndexToModel(selectedRow), 0);
            PanelManager.setCurrentPlayer(DatabaseManager.loadPlayerFromDatabase(playerName));
            PanelManager.changeToStagePanel();
        }
        // Deletes the Selected Save
        else if (source == this.deleteSaveButton)
        {
            String playerName = (String) this.tableModel.getValueAt(this.playerJTable.convertRowIndexToModel(selectedRow), 0);
            DatabaseManager.deletePlayerFromDatabase(playerName);
        }
        // Updates the Player List
        else if (source == this.refreshSavesButton)
        {
            updatePlayerSaves();
        }
        // Goes back to the Main Menu / Mid Game Menu
        else if (source == this.backButton)
        {
            if (PanelManager.backToMainMenu)
            {
                cl.show(PanelManager.menuCardPanel, "MAINMENU");
            }
            else
            {
                cl.show(PanelManager.menuCardPanel, "MIDGAMEMENU");
            }
        }
        updatePlayerSaves();
    }

    /**
     * Listens to changes in the values of the JTable it's assigned to.
     *
     * @param e source of the event.
     */
    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        // Determines whether there is a row selected before enabling load and delete buttons.
        boolean isRowSelected = this.playerJTable.getSelectionModel().isSelectionEmpty();
        this.loadSaveButton.setEnabled(!isRowSelected);
        this.deleteSaveButton.setEnabled(!isRowSelected);
    }
}

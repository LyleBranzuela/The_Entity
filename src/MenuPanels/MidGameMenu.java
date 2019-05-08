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
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author lyleb and khoap
 */
public class MidGameMenu extends JPanel implements ActionListener, ListSelectionListener
{
    private JLabel pausedScreen;
    private JScrollPane menuScrollPane;
    private JList menuList;
    private JPanel menuListPanel;
    private DefaultListModel model;
    private DesignAttributes designAttributes;

    /**
     *
     */
    public MidGameMenu()
    {
        super(new BorderLayout());
        this.designAttributes = new DesignAttributes();

        this.pausedScreen = new JLabel("PAUSED");
        this.pausedScreen.setFont(new Font("Tahoma", Font.BOLD, 64));
        this.pausedScreen.setForeground(this.designAttributes.primaryColor);

        // Setting up the Menu List
        String[] labels =
        {
            "Resume", "Save Checkpoint", "Load Checkpoint", "Options", "Main Menu", "Exit"
        };
        this.model = new DefaultListModel();
        for (Object p : labels)
        {
            this.model.addElement(p);
        }

        this.menuList = new JList(this.model);
        this.menuList.setLayoutOrientation(JList.VERTICAL);
        this.menuList.addListSelectionListener(this);
        this.menuList.setForeground(this.designAttributes.secondaryColor);
        this.menuList.setOpaque(false);
        this.menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.menuList.setFont(new Font("Tahoma", Font.BOLD, 32));
        this.menuList.setCellRenderer(new OpaqueCellRenderer());
        this.menuList.setMaximumSize(this.pausedScreen.getMaximumSize());
        
        this.menuScrollPane = new JScrollPane(this.menuList);
        this.menuScrollPane.setOpaque(false);
        this.menuScrollPane.setBorder(this.designAttributes.emptyBorder);
        this.menuScrollPane.getViewport().setOpaque(false);

        this.menuListPanel = new JPanel();
        this.menuListPanel.add(this.pausedScreen);
        this.menuListPanel.add(this.menuScrollPane);
        this.menuListPanel.setLayout(new BoxLayout(this.menuListPanel, BoxLayout.Y_AXIS));
        this.menuListPanel.setBorder(designAttributes.marginBorder);
        
        add(this.menuListPanel, BorderLayout.CENTER);
    }

    public class OpaqueCellRenderer extends DefaultListCellRenderer
    {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            setOpaque(isSelected);
            return this;
        }

    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {

        // Initializes a New Game
    }

    /**
     * Listens to any activities that happens in the list, and act on it.
     *
     * @param e event in the list that changed or called.
     */
    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
        if (this.menuList.getSelectedValue() == "Resume")
        {
            
        }
        // Saves the player name
        else if (this.menuList.getSelectedValue() == "Save Game")
        {
            // Storing all the player's names in the Arraylist to check for duplicates later
            ResultSet rs = DatabaseManager.getAllPlayers();
            ArrayList<String> playersInDatabase = new ArrayList<>();
            try
            {
                while (rs.next())
                {
                     String playerName = rs.getString("PLAYERNAME");
                     playersInDatabase.add(playerName);
                }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(LoadMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Checks if the current player's name is in the Array List, meaning it's in the database.
            if (playersInDatabase.contains(PanelManager.getCurrentPlayer().getName()))
            {
                DatabaseManager.savePlayerToDatabase(PanelManager.getCurrentPlayer(), true);
            }
            else
            {
                int confirmDialog = JOptionPane.showConfirmDialog (null, "Found Duplicate: Overwrite Save?", "Warning", JOptionPane.YES_NO_OPTION);
                if (confirmDialog == JOptionPane.YES_OPTION)
                {
                    DatabaseManager.savePlayerToDatabase(PanelManager.getCurrentPlayer(), false);
                }
            }
        }
        // Goes to the list of Saved files that can be loaded
        else if (this.menuList.getSelectedValue() == "Load Game")
        {
            PanelManager.setBackToMainMenu(false);
            cl.show(PanelManager.menuCardPanel, "LOADSCREEN");
        }
        else if (this.menuList.getSelectedValue() == "Options")
        {
            PanelManager.setBackToMainMenu(false);
            cl.show(PanelManager.menuCardPanel, "OPTIONSCREEN");
        }
        else if (this.menuList.getSelectedValue() == "Main Menu")
        {
            cl.show(PanelManager.menuCardPanel, "MAINMENU");
        }
        // Exits the GUI
        else if (this.menuList.getSelectedValue() == "Exit")
        {
            UtilityMethods.exitConfirmation();
        }
    }
}

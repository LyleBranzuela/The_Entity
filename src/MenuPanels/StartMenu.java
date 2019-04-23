/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuPanels;

import GUI.DesignAttributes;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author lyleb and khoap
 */
public class StartMenu extends JPanel implements ActionListener, ListSelectionListener
{
    private JLabel gameName;
    private JScrollPane menuScrollPane;
    private JList menuList;
    private JPanel menuListPanel;
    private DefaultListModel model;
    private DesignAttributes designAttributes;

    /**
     *
     */
    public StartMenu()
    {
        this.designAttributes = new DesignAttributes();

        this.gameName = new JLabel("The Entity");
        this.gameName.setFont(new Font("Tahoma", Font.BOLD, 64));
        this.gameName.setForeground(this.designAttributes.primaryColor);

        // Setting up the Menu List
        String[] labels =
        {
            "New Game", "Load Game", "Credits", "Options", "Exit"
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
        this.menuList.setMaximumSize(this.gameName.getMaximumSize());
        
        this.menuScrollPane = new JScrollPane(this.menuList);
        this.menuScrollPane.setOpaque(false);
        this.menuScrollPane.setBorder(this.designAttributes.emptyBorder);
        this.menuScrollPane.getViewport().setOpaque(false);

        this.menuListPanel = new JPanel();
        this.menuListPanel.add(this.gameName);
        this.menuListPanel.add(this.menuScrollPane);
        this.menuListPanel.setLayout(new BoxLayout(this.menuListPanel, BoxLayout.Y_AXIS));
        
        add(this.menuListPanel, BorderLayout.SOUTH);
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
        if (this.menuList.getSelectedValue() == "New Game")
        {
            get().remove(klanten);
            getContentPane().add(text, BorderLayout.CENTER);
        }
        else if (this.menuList.getSelectedValue() == "Load Game")
        {
            getContentPane().remove(klanten);
            getContentPane().add(text, BorderLayout.CENTER);
        }
        // Goes to the Credits Panel
        else if (this.menuList.getSelectedValue() == "Credits")
        {
            Credits creditsPanel = new Credits();
        }
        // Exits the GUI
        else if (this.menuList.getSelectedValue() == "Exit")
        {
            System.exit(0);
        }
    }
}

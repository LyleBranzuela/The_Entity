/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuPanels;

import GUI.DesignAttributes;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
    private JPanel menuListPanel, creditsPanel, optionsPanel;
    public static JPanel menuCardPanel;
    private DefaultListModel model;
    private DesignAttributes designAttributes;

    /**
     *
     */
    public StartMenu()
    {
        super(new BorderLayout());
        this.designAttributes = new DesignAttributes();
        setBorder(designAttributes.marginBorder);

        this.gameName = new JLabel("The Entity");
        this.gameName.setFont(new Font("Tahoma", Font.BOLD, 64));
        this.gameName.setForeground(this.designAttributes.primaryColor);

        // Setting up the Menu List
        String[] labels =
        {
            "New Game", "Load Game", "Options", "Credits", "Exit"
        };
        this.model = new DefaultListModel();
        for (Object p : labels)
        {
            this.model.addElement(p);
        }

        // Making the Menu List
        this.menuList = new JList(this.model);
        this.menuList.setLayoutOrientation(JList.VERTICAL);
        this.menuList.addListSelectionListener(this);
        this.menuList.setForeground(this.designAttributes.secondaryColor);
        this.menuList.setOpaque(false);
        this.menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.menuList.setFont(new Font("Tahoma", Font.BOLD, 32));
        this.menuList.setCellRenderer(new OpaqueCellRenderer());

        this.menuScrollPane = new JScrollPane(this.menuList);
        this.menuScrollPane.setOpaque(false);
        this.menuScrollPane.setBorder(this.designAttributes.emptyBorder);
        this.menuScrollPane.getViewport().setOpaque(false);

        this.menuListPanel = new JPanel();
        this.menuListPanel.add(this.gameName);
        this.menuListPanel.add(this.menuScrollPane);
        this.menuListPanel.setLayout(new BoxLayout(this.menuListPanel, BoxLayout.Y_AXIS));

        // Making the Card Menu Panel
        this.creditsPanel = new Credits();
        this.optionsPanel = new Options();
        menuCardPanel = new JPanel(new CardLayout());
        // menuCardPanel.add(this.stage1Panel, "STARTNEWGAME");
        // menuCardPanel.add(this.loadScreenPanel, "LOADSCREEN");
        menuCardPanel.add(this.menuListPanel, "MAINMENU");
        menuCardPanel.add(this.optionsPanel, "OPTIONSCREEN");
        menuCardPanel.add(this.creditsPanel, "CREDITSCREEN");

        add(menuCardPanel, BorderLayout.WEST);
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
        CardLayout cl = (CardLayout) (menuCardPanel.getLayout());
        if (this.menuList.getSelectedValue() == "New Game")
        {
            cl.show(menuCardPanel, "STARTNEWGAME");
        }
        else if (this.menuList.getSelectedValue() == "Load Game")
        {
            cl.show(menuCardPanel, "LOADSCREEN");
        }
        // Goes to the Options Panel
        else if (this.menuList.getSelectedValue() == "Options")
        {
            cl.show(menuCardPanel, "OPTIONSCREEN");
        }
        // Goes to the Credits Panel
        else if (this.menuList.getSelectedValue() == "Credits")
        {
            cl.show(menuCardPanel, "CREDITSCREEN");
        }
        // Exits the GUI
        else if (this.menuList.getSelectedValue() == "Exit")
        {
            System.exit(0);
        }
        this.menuList.clearSelection();
    }
}

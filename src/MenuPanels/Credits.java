/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuPanels;

import GUI.DesignAttributes;
import GUI.UtilityMethods;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author lyleb and khoap
 */
public class Credits extends JPanel implements ActionListener
{

    private JButton backButton;
    private JPanel creditsPanel;
    private DesignAttributes designAttributes;
    private DefaultListModel model;
    private JList creditList;
    private JScrollPane creditScrollPane;

    /**
     *
     */
    public Credits()
    {
        super(new BorderLayout());
        this.designAttributes = new DesignAttributes();

        // Goes back to the main menu
        this.backButton = UtilityMethods.generateButton("Back", 32,
                designAttributes.secondaryColor, null, true);
        this.backButton.addActionListener(this);

        // Setting up the Credits List
        String[] labels =
        {
            "\"Desplastico\" by Lyle Branzuela", "New Game", "Load Game", "Options", "Credits", "Exit"
        };
        this.model = new DefaultListModel();
        for (Object p : labels)
        {
            this.model.addElement(p);
        }

        // Making the Credits List
        this.creditList = new JList(this.model);
        this.creditList.setLayoutOrientation(JList.VERTICAL);
        this.creditList.setForeground(this.designAttributes.secondaryColor);
        this.creditList.setOpaque(false);
        this.creditList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.creditList.setFont(new Font("Tahoma", Font.BOLD, 32));
        this.creditList.setCellRenderer(new OpaqueCellRenderer());
        
        // Makes the JList unselectable
        this.creditList.setSelectionModel(new DefaultListSelectionModel()
        {
            @Override
            public void setSelectionInterval(int index0, int index1)
            {
                super.setSelectionInterval(-1, -1);
            }
        });

        this.creditScrollPane = new JScrollPane(this.creditList);
        this.creditScrollPane.setOpaque(false);
        this.creditScrollPane.setBorder(this.designAttributes.emptyBorder);
        this.creditScrollPane.getViewport().setOpaque(false);

        // Making The Title Border
        Border titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Credits", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 58), this.designAttributes.primaryColor);

        
        this.creditsPanel = new JPanel();
        this.creditsPanel.add(this.creditList);
        this.creditsPanel.add(this.backButton);
        this.creditsPanel.setBorder(new CompoundBorder(titledBorder,
                this.designAttributes.createMarginBorder(16, 0, 8, 8)));
        this.creditsPanel.setBackground(Color.BLACK);
        this.creditsPanel.setLayout(new BoxLayout(this.creditsPanel, BoxLayout.Y_AXIS));

        add(this.creditsPanel);
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
        Object source = (Object) e.getSource();
        if (source == this.backButton)
        {
            CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
            cl.show(PanelManager.menuCardPanel, "MAINMENU");
        }
    }

}

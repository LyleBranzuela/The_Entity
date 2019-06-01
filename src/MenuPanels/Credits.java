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
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

/**
 * JPanel for the credits screen.
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
    private JLabel creditsLabel;

    /**
     * Constructor for the Credits Panel.
     */
    public Credits()
    {
        super(new BorderLayout());
        this.designAttributes = new DesignAttributes();

         // Text of Options on the Top
        this.creditsLabel = new JLabel("Credits");
        this.creditsLabel.setFont(new Font("Tahoma", Font.BOLD, 64));
        this.creditsLabel.setForeground(this.designAttributes.primaryColor);
        this.creditsLabel.setBorder(designAttributes.createMarginBorder(8, 16, 0, 16));
        
        // Goes back to the main menu
        this.backButton = UtilityMethods.generateButton("Back", 32,
                designAttributes.secondaryColor, null, true);
        this.backButton.addActionListener(this);
        this.backButton.setBorder(designAttributes.createMarginBorder(16, 0, 16, 0));

        // Setting up the Credits List
        String[] labels =
        {
            "[PROGRAMMERS]",
            "> Lyle Anthony M. Branzuela - 17982811",
            "> Khoa Anh Pham - 17998774",
            " ",
            "[SOUNDS USED]",
            "> Monster_Howl_1 : Alex Bird - CC BY 3.0",
            "> Lockpick_Failed : Ross Bell - CC0 (No copyright)",
            "> Lockpick_Success : tmlappelt - CC BY Sampling+ (Attribution, non-commerial)",
            "> Ambient_Music: FoolBoyMedia - CC BY-NC 3.0 (Attribution, non-commercial)",
            "> Spooky_Music: thebosedeity - CC BY-NC 3.0 (Attribution, non-commercial)",
            "> MetalDoor_Opening: Free for Public Use",
            " ",
            "[IMAGES USED]",
            "> Stage 1: Prison Cell - AstroDev (CC0 Public Domain)",
            "> Stage 4: Dead Monster - Ratoca (Vector Stock License, Attribution)",
            "> Stage 4: Ruins Background - ArtCoreStudios - (CC0 Public Domain)",
            "> Menu Screen: Ruins Background - ArtCoreStudios - (CC0 Public Domain)",
            "> Pause Screen: Scary Hallway - robinsonk26 from Pixabay (Free for commercial use)",
            "> Door Wallpaper: Door Background - Frantisek Krejci - (Free for commercial use)"
        };
        // Adding the credits to the JList
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
        this.creditList.setFont(new Font("Tahoma", Font.BOLD, 14));
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
        this.creditScrollPane.getViewport().setOpaque(false);

        // Putting them all together
        this.creditsPanel = new JPanel();
        this.creditsPanel.setLayout(new BoxLayout(this.creditsPanel, BoxLayout.Y_AXIS));
        this.creditsPanel.setBorder(this.designAttributes.createMarginBorder(16, 16, 0, 8));
        this.creditsPanel.add(this.creditList);
        this.creditsPanel.setBackground(Color.BLACK);

        setBackground(Color.BLACK);
        add(this.creditsLabel, BorderLayout.NORTH);
        add(this.creditsPanel, BorderLayout.WEST);
        add(this.backButton, BorderLayout.SOUTH);
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Escape");
        // Customized Action for pressing Escape
        Action escapeAction = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
                cl.show(PanelManager.menuCardPanel, "MAINMENU");
            }
        };
        // Adding an action map to the input map
        this.getActionMap().put("Escape", escapeAction);
    }

    /**
     * Makes the JList into opaque.
     */
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

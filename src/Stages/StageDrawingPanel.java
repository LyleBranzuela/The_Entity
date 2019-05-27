/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stages;

import GameEntities.Entity;
import GameEntities.EntityMovement;
import GameEntities.Player;
import MenuPanels.PanelManager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * A separate JPanel meant to handle all the painting and drawing of the
 * component.
 *
 * @author lyleb and khoap
 */
public class StageDrawingPanel extends JPanel
{
    public ArrayList<Entity> entityList;
    public Player currentPlayer;
    private TileSetPanel tileSetPanel;
    private static final int WIFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    private static final String MOVE_FORWARD = "Move Up";
    private static final String MOVE_BACKWARD = "Move Down";
    private static final String MOVE_LEFT = "Move Left";
    private static final String MOVE_RIGHT = "Move Right";
    
    /**
     *
     * @param entityList
     * @param player
     */
    public StageDrawingPanel()
    {
        super(new BorderLayout());
        this.currentPlayer = PanelManager.getCurrentPlayer();
        this.currentPlayer.entityMovement.setLocation(50, 50);
        this.entityList = this.currentPlayer.getCurrentStage().entityList;

        this.tileSetPanel = new TileSetPanel();
        this.tileSetPanel.setBackground(Color.BLACK);
        this.tileSetPanel.setFocusable(true);

        // Setup input map
        this.getInputMap(WIFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Escape");
        this.getInputMap(WIFW).put(KeyStroke.getKeyStroke("UP"), MOVE_FORWARD);
        this.getInputMap(WIFW).put(KeyStroke.getKeyStroke("W"), MOVE_FORWARD);
        this.getInputMap(WIFW).put(KeyStroke.getKeyStroke("DOWN"), MOVE_BACKWARD);
        this.getInputMap(WIFW).put(KeyStroke.getKeyStroke("S"), MOVE_BACKWARD);
        this.getInputMap(WIFW).put(KeyStroke.getKeyStroke("LEFT"), MOVE_LEFT);
        this.getInputMap(WIFW).put(KeyStroke.getKeyStroke("A"), MOVE_LEFT);
        this.getInputMap(WIFW).put(KeyStroke.getKeyStroke("RIGHT"), MOVE_RIGHT);
        this.getInputMap(WIFW).put(KeyStroke.getKeyStroke("D"), MOVE_RIGHT);
        
        Action escapeAction = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
                cl.show(PanelManager.menuCardPanel, "MIDGAMEMENU");
            }
        };
        this.getActionMap().put("Escape", escapeAction); 
        this.getActionMap().put(MOVE_FORWARD, new MoveAction(EntityMovement.FORWARD));
        this.getActionMap().put(MOVE_BACKWARD, new MoveAction(EntityMovement.BACKWARD));
        this.getActionMap().put(MOVE_LEFT, new MoveAction(EntityMovement.LEFT));
        this.getActionMap().put(MOVE_RIGHT, new MoveAction(EntityMovement.RIGHT));
       
        add(this.tileSetPanel);
    }
    
    /**
     * Updates the entity list of the stage drawing panel.
     *
     * @param entityList the entity list to be updated.
     */
    public void updateEntityList(ArrayList<Entity> entityList)
    {
        this.entityList = entityList;
    }

    /**
     * Clears the entity list of the Stage JPanel.
     */
    public void clearEntityList()
    {
        this.entityList.clear();
    }

    /**
     * A separate JPanel meant to handle all the painting and drawing of the
     * component.
     */
    private class TileSetPanel extends JPanel
    {

        public TileSetPanel()
        {
            super();
        }

        /**
         * Draws all the components of the drawing panel
         *
         * @param g specifies the current graphic space the panel is using.
         */
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            if (!entityList.isEmpty())
            {
                for (Entity e : entityList)
                {
                    e.draw(g);
                }
            }
        }
    }

    /**
     *
     */
    private class MoveAction extends AbstractAction 
    {

        public boolean inGame, isMoving;
        public int direction;

        /**
         *
         */
        public MoveAction(int direction)
        {
            this.inGame = true;
            this.isMoving = false;
            this.direction = direction;
        }
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            currentPlayer.entityMovement.moveEntity(this.direction);
            repaint();
        }

        /**
         *
         * @return
         */
        public boolean checkPlayerInBoundary()
        {
            return true;
        }
    }

    public static void main(String[] args)
    {
        ArrayList<Entity> entityList = new ArrayList<>();
        Player player = new Player("Ja Yeet");
        entityList.add(player);

        // Instantiate Panel Manager
        StageDrawingPanel myPanel = new StageDrawingPanel();
        JFrame frame = new JFrame("The Entity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(myPanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(new Point((d.width / 2) - (frame.getWidth() / 2), (d.height / 2) - (frame.getHeight() / 2)));
        frame.setVisible(true);
    }
}

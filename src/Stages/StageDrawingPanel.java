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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

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
    public JPanel tileGrid;
    private DrawingPanel drawingPanel;
    private JButton[][] tileSet;
    private static final int WIFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    private static final String MOVE_FORWARD = "Move Up";
    private static final String MOVE_BACKWARD = "Move Down";
    private static final String MOVE_LEFT = "Move Left";
    private static final String MOVE_RIGHT = "Move Right";
    private final int GRID_AMOUNT = 10;
   
    /**
     *
     */
    public StageDrawingPanel()
    {
        super(new BorderLayout());

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

        // Customized Action for pressing Escape
        Action escapeAction = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
                cl.show(PanelManager.menuCardPanel, "MIDGAMEMENU");
            }
        };

        // Adding an action map to the input map
        this.getActionMap().put("Escape", escapeAction);
        this.getActionMap().put(MOVE_FORWARD, new MoveAction(EntityMovement.FORWARD));
        this.getActionMap().put(MOVE_BACKWARD, new MoveAction(EntityMovement.BACKWARD));
        this.getActionMap().put(MOVE_LEFT, new MoveAction(EntityMovement.LEFT));
        this.getActionMap().put(MOVE_RIGHT, new MoveAction(EntityMovement.RIGHT));

        this.tileSet = new JButton[GRID_AMOUNT][GRID_AMOUNT];
//        this.tileGrid = new JPanel(new GridLayout(gridAmount, gridAmount));
        this.drawingPanel = new DrawingPanel();
        this.drawingPanel.setLayout(new GridLayout(GRID_AMOUNT, GRID_AMOUNT));
        for (int row = 0; row < GRID_AMOUNT; row++)
        {
            for (int col = 0; col < GRID_AMOUNT; col++)
            {
                this.tileSet[row][col] = new JButton();
                this.tileSet[row][col].setBackground(Color.DARK_GRAY);
                this.tileSet[row][col].setVisible(false);
                this.tileSet[row][col].setEnabled(false);
                this.drawingPanel.add(this.tileSet[row][col]);
            }
        }
        updateStagePlayer();
        this.drawingPanel.setBackground(Color.BLACK);
        this.drawingPanel.setFocusable(true);

        add(this.drawingPanel);
    }

    public void updateStagePlayer()
    {
        this.currentPlayer = PanelManager.getCurrentPlayer();
        this.entityList = this.currentPlayer.getCurrentStage().entityList;
        // Starting Tile
        int x = this.tileSet[(GRID_AMOUNT / 2) - 1][(GRID_AMOUNT / 2) - 1].getX();
        int y = this.tileSet[GRID_AMOUNT - 1][GRID_AMOUNT - 1].getY() + 10;
        this.currentPlayer.entityMovement.setLocation(x, y);
        repaint();
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
    private class DrawingPanel extends JPanel implements ActionListener
    {
        Timer timer = new Timer(4000, this);
        int i = 0;
        public String Stage1_story[] =  { "An evil entity appeared 10 years ago, anyone seeing this Entity becomes either...",
                        "...a mindless slave or an insane person who would commit suicide immediately.",
                        "You were rescued by a group of blind survivors when you ten years old.",
                        "Taught by the group to see with sounds, you managed to survive the apocalypse...",
                        "Until one day you were captured by a group of slaves...",
                        "Waking up in a prison cell, you must now look for a way to escape..."};

        public DrawingPanel()
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
            if(currentPlayer.getCurrentStage() instanceof Stage_1)
            {
                if(this.i < 5)
                {
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    g.drawString(Stage1_story[this.i], 30, 280);
                }
                else
                {
                    g.setColor(Color.BLACK);
                    g.clearRect(0, 0, 1000, 600);
                    ImageIcon image = new ImageIcon("c:\\Users\\Khoa Pham\\Desktop\\Entity\\background\\Stage1_Prison.png");
                    
                    image.paintIcon(this, g, 0, 0);
                }
            }
            timer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(currentPlayer.getCurrentStage() instanceof Stage_1)
            { 
                if(this.i < 5)
                {
                    this.i++;
                    repaint();
                }
                else
                {
                    //Do nothing
                }
            }
        }
    }

    /**
     * An abstract action that moves the player.
     */
    private class MoveAction extends AbstractAction
    {

        public boolean inGame, isMoving;
        public int direction;

        /**
         * Depends what direction the player should move to.
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
            if (checkPlayerInBoundary())
            {
                currentPlayer.entityMovement.moveEntity(this.direction);
                repaint();
            }
        }

        /**
         * Checks whether the player is in boundary of the JPanel.
         * 
         * @return true if they're in the boundary, false if they're not.
         */
        public boolean checkPlayerInBoundary()
        {
            int playerX = currentPlayer.entityMovement.getXMovement();
            int playerY = currentPlayer.entityMovement.getYMovement();
            int panelWidth = drawingPanel.getWidth();
            int panelHeight = drawingPanel.getHeight();
            boolean check = true;
            if ((playerX - 5) <= 0 && this.direction == EntityMovement.LEFT)
            {
                check = this.direction != EntityMovement.LEFT;
            }
            if ((playerX + 10) >= panelWidth - 60 && this.direction == EntityMovement.RIGHT)
            {
                check = this.direction != EntityMovement.RIGHT;
            }
            if ((playerY - 5) <= 0 && this.direction == EntityMovement.FORWARD)
            {
                check = this.direction != EntityMovement.FORWARD;
            }
            if ((playerY + 10) >= panelHeight - 35 && this.direction == EntityMovement.BACKWARD)
            {
                check = this.direction != EntityMovement.BACKWARD;
            }

            return check;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stages;

import GUI.UtilityMethods;
import GameEntities.EnemyPatrol;
import GameEntities.EntityMovement;
import GameEntities.Player;
import MenuPanels.PanelManager;
import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * A separate JPanel meant to handle all the painting and drawing of the
 * component.
 *
 * @author lyleb and khoap
 */
public class Stage_2 extends Stage
{

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
    private ArrayList<EnemyPatrol> enemyPatrol;
    
    /**
     * Constructor for creating Stage 2.
     */
    public Stage_2()
    {
        super();
        super.stageLevel = 2;

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

        // Initiate the Patrols
        this.enemyPatrol = new ArrayList<>();
        this.enemyPatrol.add(new EnemyPatrol("Patrol 1", 0, EntityMovement.LEFT));
        this.enemyPatrol.get(0).entityMovement.setMovementSpeed(2);
        this.enemyPatrol.add(new EnemyPatrol("Patrol 2", 0, EntityMovement.BACKWARD));
        this.enemyPatrol.get(1).entityMovement.setMovementSpeed(1);
        this.enemyPatrol.add(new EnemyPatrol("Patrol 3", 0, EntityMovement.RIGHT));
        this.enemyPatrol.get(2).entityMovement.setMovementSpeed(2);
        this.enemyPatrol.add(new EnemyPatrol("Patrol 4", 0, EntityMovement.FORWARD));
        this.enemyPatrol.get(3).entityMovement.setMovementSpeed(1);
        this.enemyPatrol.add(new EnemyPatrol("Patrol 5", 0, EntityMovement.RIGHT));
        this.enemyPatrol.get(4).entityMovement.setMovementSpeed(2);

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

        // Create the tile set in the drawing panel
        this.tileSet = new JButton[GRID_AMOUNT][GRID_AMOUNT];
        this.drawingPanel = new DrawingPanel();
        this.drawingPanel.setLayout(new GridLayout(GRID_AMOUNT, GRID_AMOUNT));
        this.drawingPanel.setBackground(Color.BLACK);
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
        add(this.drawingPanel);
    }

    /**
     * Updates the stage player to initialize stage.
     */
    @Override
    public void updateStagePlayer()
    {
        this.currentPlayer = PanelManager.getCurrentPlayer();
        
        // Starting Player Tile - Center of X at the bottom of Y
        int x = this.tileSet[(GRID_AMOUNT / 2) - 1][(GRID_AMOUNT / 2) - 1].getX() + 10;
        int y = this.tileSet[GRID_AMOUNT - 1][GRID_AMOUNT - 1].getY() + 10;
        this.currentPlayer.entityMovement.setLocation(x, y);
        setEnemyPositions();
        repaint();
    }

    /**
     * Sets the initial positions of the enemy patrols
     */
    public void setEnemyPositions()
    {
        // Top Right
        int x = this.tileSet[GRID_AMOUNT - 1][GRID_AMOUNT - 1].getX() - 10;
        int y = this.tileSet[0][1].getY() + 10;
        this.enemyPatrol.get(0).entityMovement.setLocation(x, y);

        // Middle Top
        x = this.tileSet[(GRID_AMOUNT / 2) - 1][7].getX() + 10;
        y = this.tileSet[(GRID_AMOUNT / 2) - 1][7].getY();
        this.enemyPatrol.get(1).entityMovement.setLocation(x, y);
        
        // Middle 
        x = this.tileSet[(GRID_AMOUNT / 2) - 1][(GRID_AMOUNT / 2) - 1].getX() + 10;
        y = this.tileSet[(GRID_AMOUNT / 2) - 1][(GRID_AMOUNT / 2) - 1].getY();
        this.enemyPatrol.get(2).entityMovement.setLocation(x, y);
        
        // Middle Bottom
        x = this.tileSet[(GRID_AMOUNT / 2) - 3][(GRID_AMOUNT / 2) - 3].getX() + 10;
        y = this.tileSet[(GRID_AMOUNT / 2) - 3][(GRID_AMOUNT / 2) - 3].getY();
        this.enemyPatrol.get(3).entityMovement.setLocation(x, y);
        
        // Bottom Left
        x = this.tileSet[GRID_AMOUNT - 4][0].getX() + 5;
        y = this.tileSet[GRID_AMOUNT - 4][GRID_AMOUNT - 1].getY()
                + this.tileSet[GRID_AMOUNT - 4][GRID_AMOUNT - 1].getHeight() + 10;
        this.enemyPatrol.get(4).entityMovement.setLocation(x, y);
    }

    /**
     * Moves the enemy patrols.
     * 
     * @param ep which enemy patrol to move.
     */
    public void moveEnemyPatrol(EnemyPatrol ep)
    {
        int epX = ep.entityMovement.getXMovement();
        int epY = ep.entityMovement.getYMovement();
        int panelWidth = drawingPanel.getWidth();
        int panelHeight = drawingPanel.getHeight();

        if ((epX - 5) <= 0 && ep.initialDirection == EntityMovement.LEFT)
        {
            ep.initialDirection = EntityMovement.RIGHT;
        }
        if ((epX + 10) >= panelWidth - 70 && ep.initialDirection  == EntityMovement.RIGHT)
        {
            ep.initialDirection = EntityMovement.LEFT;
        }
        if ((epY - 5) <= 0 && ep.initialDirection  == EntityMovement.FORWARD)
        {
            ep.initialDirection = EntityMovement.BACKWARD;
        }
        if ((epY + 10) >= panelHeight - 40 && ep.initialDirection == EntityMovement.BACKWARD)
        {
            ep.initialDirection = EntityMovement.FORWARD;
        }
        ep.entityMovement.moveEntity(ep.initialDirection);
        repaint();
    }
        
    /**
     * Checks whether the player is detected or not in the JPanel.
     *
     * @return true if they're in the boundary, false if they're not.
     */
    public boolean checkIfPlayerDetected()
    {
        boolean detected = false;
        if (!enemyPatrol.isEmpty())
        {
            for (EnemyPatrol ep : enemyPatrol)
            {
                int playerX = currentPlayer.entityMovement.getXMovement();
                int playerY = currentPlayer.entityMovement.getYMovement();
                int patrolX = ep.entityMovement.getXMovement();
                int patrolY = ep.entityMovement.getYMovement();

                // Check Detection and what direction is the entity patrolling to.
                if (ep.initialDirection == EntityMovement.LEFT || 
                        ep.initialDirection == EntityMovement.RIGHT)
                {
                    if (playerX >= patrolX && playerY >= patrolY
                            && playerX <= (patrolX + 30) && playerY <= (patrolY + 75))
                    {
                        detected = true;
                    }
                }
                else
                {
                    if (playerX >= patrolX && playerY >= patrolY
                            && playerX <= (patrolX + 75) && playerY <= (patrolY + 30))
                    {
                        detected = true;
                    }
                }
            }
        }
        return detected;
    }
    
    /**
     * A separate JPanel meant to handle all the painting and drawing of the
     * component.
     */
    private class DrawingPanel extends JPanel
    {
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
            
            // Draw the floor
            for (int row = 0; row < GRID_AMOUNT; row++)
            {
                for (int col = 0; col < GRID_AMOUNT; col++)
                {
                    int x = tileSet[row][col].getX();
                    int y = tileSet[row][col].getY();
                    int width = tileSet[row][col].getWidth();
                    int height = tileSet[row][col].getHeight();
                    g.setColor(new Color(56, 56, 56)); 
                    g.fillRect(x, y, width, height);
                    g.drawString("OBJECTIVE: Search the room and find a way to escape!", 250, 70);
                }
            }

            // Draw The Entities
            currentPlayer.draw(g);
            if (!enemyPatrol.isEmpty())
            {
                for (EnemyPatrol ep : enemyPatrol)
                {
                    moveEnemyPatrol(ep);
                    ep.draw(g);
                }
            }

            // The Walls
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.BLACK); 
            g2.setStroke(new BasicStroke(6));
            g2.drawRect(0, 0, drawingPanel.getWidth() - 1, drawingPanel.getHeight());
            
            // The Doors
                // Top Door
            g2.setColor(new Color(222,184,135)); 
            g2.setStroke(new BasicStroke(6));
            int doorX = tileSet[0][(GRID_AMOUNT / 2) - 1].getX();
            int doorY = tileSet[0][(GRID_AMOUNT / 2) - 1].getY();
            int doorWidth = tileSet[0][(GRID_AMOUNT / 2) - 1].getWidth();
            g2.drawRect(doorX, doorY, doorWidth, 0);
                // Bottom Door
            doorX = tileSet[GRID_AMOUNT - 1][(GRID_AMOUNT / 2) - 1].getX();
            doorY = tileSet[GRID_AMOUNT - 1][(GRID_AMOUNT / 2) - 1].getY() 
                    + tileSet[GRID_AMOUNT - 1][(GRID_AMOUNT / 2) - 1].getHeight();
            doorWidth = tileSet[GRID_AMOUNT - 1][(GRID_AMOUNT / 2) - 1].getWidth();
            g2.drawRect(doorX, doorY, doorWidth, 0);
            
            if (checkIfPlayerDetected())
            {
                CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
                cl.show(PanelManager.menuCardPanel, "GAMEOVERSCREEN");
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

        /**
         * Waits for an action to be received and act upon it.
         *
         * @param e action event to be received.
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (checkPlayerInBoundary())
            {
                currentPlayer.entityMovement.moveEntity(this.direction);
                repaint();
            }
            if (checkIfPlayerEscaped())
            {
                currentPlayer.setCurrentStageLevel(new Stage_3());
                PanelManager.setCurrentPlayer(currentPlayer);
                PanelManager.changeToStagePanel();
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
            if ((playerX + 10) >= panelWidth - 70 && this.direction == EntityMovement.RIGHT)
            {
                check = this.direction != EntityMovement.RIGHT;
            }
            if ((playerY - 5) <= 0 && this.direction == EntityMovement.FORWARD)
            {
                check = this.direction != EntityMovement.FORWARD;
            }
            if ((playerY + 10) >= panelHeight - 40 && this.direction == EntityMovement.BACKWARD)
            {
                check = this.direction != EntityMovement.BACKWARD;
            }

            return check;
        }
        
        /**
         * Checks whether the player has escaped or not in the JPanel.
         *
         * @return true if they've escaped, false if they haven't.
         */
        public boolean checkIfPlayerEscaped()
        {
            int playerX = currentPlayer.entityMovement.getXMovement();
            int playerY = currentPlayer.entityMovement.getYMovement();
            int doorX = tileSet[0][(GRID_AMOUNT / 2) - 1].getX();
            int doorY = tileSet[0][(GRID_AMOUNT / 2) - 1].getY();
            int doorWidth = tileSet[0][(GRID_AMOUNT / 2) - 1].getWidth();
            
            return playerX >= (doorX  - 30) && playerX <= doorX + (doorWidth - 40)
                    && playerY <= doorY+5;
        }
    }
}

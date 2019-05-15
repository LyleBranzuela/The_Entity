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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

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

    /**
     *
     * @param entityList
     * @param player
     */
    public StageDrawingPanel(ArrayList<Entity> entityList, Player player)
    {
        super(new BorderLayout());
        this.entityList = entityList;
        this.currentPlayer = player;

        this.tileSetPanel = new TileSetPanel();
        this.tileSetPanel.setBackground(Color.BLACK);
        this.tileSetPanel.addKeyListener(new KeyListenerHandler());
        this.tileSetPanel.setFocusable(true);

        add(this.tileSetPanel, BorderLayout.CENTER);
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
    private class KeyListenerHandler implements KeyListener
    {

        public boolean inGame, isMoving;

        /**
         *
         */
        public KeyListenerHandler()
        {
            this.inGame = false;
            this.isMoving = false;
            System.out.println("Yeet");
        }

        /**
         *
         * @return
         */
        public boolean checkPlayerInBoundary()
        {
            return true;
        }

        /**
         *
         * @param e
         */
        @Override
        public void keyTyped(KeyEvent e)
        {
        }

        /**
         *
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e)
        {
            int KeyCode = e.getKeyCode();
            if (KeyCode == KeyEvent.VK_ESCAPE)
            {
                this.inGame = false;
                CardLayout cl = (CardLayout) (PanelManager.menuCardPanel.getLayout());
                cl.show(PanelManager.menuCardPanel, "MIDGAMEMENU");
            }
            else
            {
                if (checkPlayerInBoundary() && this.inGame)
                {
                    // TOP LEFT Movement
                    if (KeyCode == KeyEvent.VK_W && KeyCode == KeyEvent.VK_A)
                    {
                        currentPlayer.entityMovement.moveEntity(EntityMovement.TOP_LEFT);
                    }
                    // TOP RIGHT Movement
                    else if (KeyCode == KeyEvent.VK_W && KeyCode == KeyEvent.VK_D)
                    {
                        currentPlayer.entityMovement.moveEntity(EntityMovement.TOP_RIGHT);
                    }
                    // BOTTOM LEFT Movement
                    else if (KeyCode == KeyEvent.VK_W && KeyCode == KeyEvent.VK_A)
                    {
                        currentPlayer.entityMovement.moveEntity(EntityMovement.BOTTOM_LEFT);
                    }
                    // BOTTOM RIGHT Movement
                    else if (KeyCode == KeyEvent.VK_S && KeyCode == KeyEvent.VK_D)
                    {
                        currentPlayer.entityMovement.moveEntity(EntityMovement.BOTTOM_RIGHT);
                    }
                    // FORWARD Movement
                    else if (KeyCode == KeyEvent.VK_W)
                    {
                        currentPlayer.entityMovement.moveEntity(EntityMovement.FORWARD);
                    }
                    // LEFT Movement
                    else if (KeyCode == KeyEvent.VK_A)
                    {
                        currentPlayer.entityMovement.moveEntity(EntityMovement.LEFT);
                    }
                    // BACKWARD Movement
                    else if (KeyCode == KeyEvent.VK_S)
                    {
                        currentPlayer.entityMovement.moveEntity(EntityMovement.BACKWARD);
                    }
                    // RIGHT Movement
                    else if (KeyCode == KeyEvent.VK_D)
                    {
                        currentPlayer.entityMovement.moveEntity(EntityMovement.RIGHT);
                    }
                    this.isMoving = true;
                    repaint();
                }
                else
                {
                    this.isMoving = false;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
        }
    }

    public static void main(String[] args)
    {
        ArrayList<Entity> entityList = new ArrayList<>();
        Player player = new Player("Ja Yeet");
        entityList.add(player);

        // Instantiate Panel Manager
        StageDrawingPanel myPanel = new StageDrawingPanel(entityList, player);
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

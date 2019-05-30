/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stages;

import GUI.MainDriver;
import GameEntities.Entity;
import GameEntities.Player;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Abstract parent class for all the stages.
 *
 * @author lyleb and khoap
 */
public abstract class Stage extends JPanel implements Serializable
{
    public StageDrawingPanel stageJPanel;
    public JFrame stageFrame = MainDriver.frame;
    public ArrayList<Entity> entityList;
    protected int stageLevel;
    public boolean isCompleted;

    /**
     * 
     * @param player 
     */
    public Stage(Player player) {
        this.entityList = new ArrayList<>();
        this.entityList.add(player);
    }
    
    /**
     * Method to determine a stage level.
     *
     * @param stage
     * @return
     */
    public int determineStageLevel(Stage stage)
    {
        if (stage instanceof Stage_1)
        {
            this.stageLevel = 1;
        }
        else if (stage instanceof Stage_2)
        {
            this.stageLevel = 2;
        }
        else if (stage instanceof Stage_3)
        {
            this.stageLevel = 3;
        }
        else if (stage instanceof Stage_4)
        {
            this.stageLevel = 4;
        }
        else
        {
            this.stageLevel = 1;
        }
        return this.stageLevel;
    }

    /**
     * Returns the stage level of the stage.
     *
     * @return the stage level of the current stage.
     */
    public int getStageLevel()
    {
        return this.stageLevel;
    }

    /**
     * Abstract function to be filled with to initiate the stage rooms.
     *
     */
    abstract public void initiateStage(Player player, Graphics g);
    
    abstract public void draw(Graphics g);
}

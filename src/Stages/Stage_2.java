/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stages;

import GameEntities.Player; 
import GUI.UtilityMethods;
import GameEntities.EnemyPatrol;
import java.awt.Graphics;
import java.util.Scanner;

/**
 * Class for the Stage 1 of the Game.
 *
 * @author lyleb and khoap
 */
public class Stage_2 extends Stage
{
    private String userInput;
    private String guardMovement;
    private boolean isCompleted;
    private EnemyPatrol enemyPatrol;
    private EnemyPatrol enemyPatrol2;
    private EnemyPatrol enemyPatrol3;

    /**
     * 
     * @param player 
     */
    public Stage_2(Player player)
    {
        super(player);
        this.enemyPatrol = new EnemyPatrol("Enemy Patrol 1", 0);
        this.enemyPatrol2 = new EnemyPatrol("Enemy Patrol 2", 0);
        this.enemyPatrol3 = new EnemyPatrol("Enemy Patrol 3", 0);

        super.entityList.add(this.enemyPatrol);
        super.entityList.add(this.enemyPatrol2);
        super.entityList.add(this.enemyPatrol3);
        super.stageJPanel.updateEntityList(super.entityList);
    }

    @Override
    public void initiateStage(Player player, Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics g) {
       
    }
}
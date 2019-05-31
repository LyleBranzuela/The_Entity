/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stages;

import GameEntities.*;
import Items.Blindfold;
import Items.Item;
import GUI.UtilityMethods;
import Items.Weapon;
import MenuPanels.PanelManager;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
/**
 * Class for the Stage 4 of the Game.
 *
 * @author lyleb and khoap
 */
public class Stage_4 extends Stage
{
    private Player currentPlayer;
    
    /**
     * Constructor for creating Stage 4.
     */
    public Stage_4()
    {
        super();
        super.stageLevel = 4;
    }
    
    @Override
    public void updateStagePlayer()
    {
       this.currentPlayer = PanelManager.getCurrentPlayer();
    }
}

package Worlds;

import Game.Entities.Dynamic.Player;
import Game.Entities.Dynamic.Tail;
import Game.Entities.Static.Apple;
import Main.Handler;

import java.awt.*;
import java.util.LinkedList;

import java.awt.Color;
/**
 * Created by AlexVR on 7/2/2018.
 */
public abstract class WorldBase {
	
    //How many pixels are from left to right
    //How many pixels are from top to bottom
    //Must be equal
    public int GridWidthHeightPixelCount;

    //automatically calculated, depends on previous input.
    //The size of each box, the size of each box will be GridPixelsize x GridPixelsize.
    public int GridPixelsize;

    public Player player;

    protected Handler handler;


    public Boolean appleOnBoard;
    protected Apple apple;
    public Boolean[][] appleLocation;


    public Boolean[][] playerLocation;

    public LinkedList<Tail> body = new LinkedList<>();


    public WorldBase(Handler handler){
        this.handler = handler;

        appleOnBoard = false;


    }
    public void tick(){



    }

    public void render(Graphics Grid){
        for (int i = 0; i <= 800; i = i + GridPixelsize) {
			Grid.setColor(new Color(102 ,0 ,153));					
            Grid.drawLine(0, i, handler.getWidth() , i);
            Grid.drawLine(i,0,i,handler.getHeight());
        }
    }

    
}

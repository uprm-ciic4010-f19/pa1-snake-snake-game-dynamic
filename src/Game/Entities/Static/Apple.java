package Game.Entities.Static;

import java.awt.Color;


import Game.Entities.Dynamic.Tail;
import Main.Handler;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Apple {

    private Handler handler;

    public int xCoord;
    public int yCoord;
    private boolean appleGood = true;

    public Apple(Handler handler,int x, int y){
        this.handler=handler;
        this.xCoord=x;
        this.yCoord=y;
    }
    

    
    //state of the apple
	public boolean isGood(){
	return appleGood;
	}
}
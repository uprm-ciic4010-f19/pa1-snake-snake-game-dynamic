package Game.GameStates;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.font.*;

import Main.Handler;

public  class Gameover //extends State { 
{
//public Gameover(Handler handler) {
	//	super(handler);
		
	//}
	private final static int Width = 800 ;
	private final static  int Height = 800;
void GameEnd(Graphics h) {
    
    String message = "Game over";

    Font font = new Font("Comic Sans", Font.BOLD, 14);


  
    h.setColor(Color.red);
    h.setFont(font);
    
    
    h.drawString(message, ( Width ) / 2,
            Height / 2);
}

}	

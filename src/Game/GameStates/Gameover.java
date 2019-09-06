package Game.GameStates;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.font.*;

public  class Gameover { 
	private final static int Width = 800 ;
	private final static  int Height = 800;
void GameEnd(Graphics h) {
    // Create a message telling the player the game is over
    String message = "Game over";

    // Create a new font instance
    Font font = new Font("Comic Sans", Font.BOLD, 14);
    FontMetrics metrics = getFontMetrics(font);

    // Set the color of the text to red, and set the font
    h.setColor(Color.red);
    h.setFont(font);

    // Draw the message to the board
    h.drawString(message, (Width - metrics.stringWidth(message)) / 2,
            Height / 2);
}

	private static FontMetrics getFontMetrics(Font font) {
		// TODO Auto-generated method stub
		return null;
	}
}

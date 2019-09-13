package Game.GameStates;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.font.*;

import Main.Handler;
import Resources.Images;
import UI.ClickListlener;
import UI.UIImageButton;
import UI.UIManager;
import Game.Entities.Dynamic.Player;



public class GameOver extends State {

	private UIManager uiManager;
	public GameOver(Handler handler) {
		 super(handler);
	        uiManager = new UIManager(handler);
	        handler.getMouseManager().setUimanager(uiManager);

	        
	        
	        ///IMAGENES DEL WINDOW DE GAME OVER
	        uiManager.addObjects(new UIImageButton(handler.getWidth()/33 + 192, handler.getHeight() - 150, 128, 64, Images.butstart, new ClickListlener() {
	     
	        	@Override
	            public void onClick() {
	                handler.getMouseManager().setUimanager(null);
	                handler.getGame().reStart();
	                State.setState(handler.getGame().gameState);
	            }
	        }));

	        uiManager.addObjects(new UIImageButton(33 + 192 * 2,  handler.getGame().getHeight() - 150, 128, 64, Images.BTitle, () -> {
	            handler.getMouseManager().setUimanager(null);
	            State.setState(handler.getGame().menuState);
	        }));
	        
	        uiManager.addObjects(new UIImageButton(33 + 192 * 3,  handler.getGame().getHeight() - 300, 128, 64, Images.Score, () -> {
	            handler.getMouseManager().setUimanager(null);
	            State.setState(handler.getGame().menuState);
	        }));
//	        
	        
	}
	

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(0,0,handler.getWidth(),handler.getHeight());
        g.drawImage(Images.GameOver,0,0,handler.getGame().getWidth(),handler.getGame().getHeight(),null);
               
        uiManager.Render(g);

    }

}
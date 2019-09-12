package Game.Entities.Dynamic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;
import Game.GameStates.GameOver;

import Main.Handler;
import Resources.Images;
import UI.UIImageButton;
import UI.UIManager;
import Game.GameStates.*;;
/**
 * Created by AlexVR on 7/2/2018.
 */
public class Player {

    private static final int Tail = 0;
	public int lenght;
    public boolean justAte;
    private Handler handler;
    public  State PauseState ;

    public int xCoord;
    public int yCoord;
    public int speed ;
    public double moveCounter;
    public  double Score;						//variable that stores size
    public String direction;//is your first name one?

    public Player(Handler handler){
        this.handler = handler;
        xCoord = 0;
        yCoord = 0;
        moveCounter = 0;
        direction= "Right";
        justAte = false;
        lenght= 1;
        speed= 0 ;// added speed and Score variables
        Score= 0;	//Initializes score to 0
        
    }
    public void tick(){
    	
        moveCounter++;
        // changed 5 for 10
        if  (moveCounter>=  10) {
     
            checkCollisionAndMove();
            // changed 0 for speed
            moveCounter= speed;
        }
        
        
    
     
      
        int x = xCoord;
        int y = yCoord;
        // added no backtracking commands to all direction keys
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)){
        	if(this.direction != "Down")
        	direction="Up";
        	
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)||handler.getKeyManager().keyJustPressed(KeyEvent.VK_S) ){
        	if(this.direction != "Up")
        	direction="Down";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)||handler.getKeyManager().keyJustPressed(KeyEvent.VK_A)){
        	if (this.direction!="Right")
        	direction="Left";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)||handler.getKeyManager().keyJustPressed(KeyEvent.VK_D)){
        	if(this.direction!="Left")
        	direction="Right"; }
       
        	// Press N key to increase the tail
        	if( handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)) { 
        		checkCollisionAndMove();
        		handler.getWorld().body.addFirst(new Tail(x, y,handler));}
        	// press esc key to pause the game
        	if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE))
        		 { 
        		
//        	State.getState();
//			State.setState(PauseState);
			State.setState(handler.getGame().pauseState);

           		 
        	}    // press 1 key to reStart the world
        		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_1)) {
        			handler.getGame().reStart();
        		}
        	// press plus key to increase in speed
        	if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_EQUALS)){ 
                checkCollisionAndMove();
                speed= speed +1;
        	
        	}
          
        
            // press minus key to decrease in speed
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_MINUS)) {
            	checkCollisionAndMove();
              speed=speed-1;
            }
          }
        
       
    
     
    

	public void checkCollisionAndMove(){
        handler.getWorld().playerLocation[xCoord][yCoord]=false;
        int x = xCoord;
        int y = yCoord;

       
        switch (direction){
            case "Left":
                if(xCoord==0){
                	xCoord =handler.getWorld().GridWidthHeightPixelCount-1;
                }else{
                    xCoord--;
                }
                break;
            case "Right":
                if(xCoord==handler.getWorld().GridWidthHeightPixelCount-1){
                	xCoord=0  ;
                }else{
                    xCoord++;
                }
                break;
            case "Up":
                if(yCoord==0){
                	yCoord=handler.getWorld().GridWidthHeightPixelCount-1;
                }else{
                    yCoord--;
                }
                break;
            case "Down":
                if(yCoord==handler.getWorld().GridWidthHeightPixelCount-1){
                	yCoord=0 ;
        			State.setState(handler.getGame().gameOver);
        			

                }else{
                    yCoord++;
                }
                break;
        }
        
        handler.getWorld().playerLocation[xCoord][yCoord]=true;
    
   
        if (handler.getWorld().appleLocation[xCoord][yCoord]){
            Eat();
          
        } 
        
       
        if(!handler.getWorld().body.isEmpty()) {
            handler.getWorld().playerLocation[handler.getWorld().body.getLast().x][handler.getWorld().body.getLast().y] = false;
            handler.getWorld().body.removeLast();
            handler.getWorld().body.addFirst(new Tail(x, y,handler));}
    // code for collision with body
//       switch(direction) {
//       case "Up":
    	///   if (handler.getWorld().player.xCoord= handler.getWorld().body.add(Tail x)) {
//          
    // }

	}

	public void render(Graphics g  ,Boolean[][] playeLocation){
        Random r = new Random();
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {
          g.setColor(Color.GREEN);
          
      
                if(playeLocation[i][j]||handler.getWorld().appleLocation[i][j]){
                	
                g.fillRect((i*handler.getWorld().GridPixelsize),
                         (j*handler.getWorld().GridPixelsize),
                          handler.getWorld().GridPixelsize,
                          handler.getWorld().GridPixelsize);
                
           
               }
               
               }
                }
        
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		g.setColor(Color.WHITE);
		g.drawString("Score: "+Score,64*5, 64);

        
        
            }
        

	
    

    public void Eat(){
        lenght++;
        Tail tail= null;
        handler.getWorld().appleLocation[xCoord][yCoord]=false;
        handler.getWorld().appleOnBoard=false;
        switch (direction){
            case "Left":
                if( handler.getWorld().body.isEmpty()){
                    if(this.xCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail = new Tail(this.xCoord+1,this.yCoord,handler);
                    }else{
                        if(this.yCoord!=0){
                            tail = new Tail(this.xCoord,this.yCoord-1,handler);
                        }else{
                            tail =new Tail(this.xCoord,this.yCoord+1,handler);
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler);
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler);
                        }else{
                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler);

                        }
                    }

                }
                break;
            case "Right":
                if( handler.getWorld().body.isEmpty()){
                    if(this.xCoord!=0){
                        tail=new Tail(this.xCoord-1,this.yCoord,handler);
                    }else{
                        if(this.yCoord!=0){
                            tail=new Tail(this.xCoord,this.yCoord-1,handler);
                        }else{
                            tail=new Tail(this.xCoord,this.yCoord+1,handler);
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().x!=0){
                        tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                        }
                    }

                }
                break;
            case "Up":
                if( handler.getWorld().body.isEmpty()){
                    if(this.yCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(this.xCoord,this.yCoord+1,handler));
                    }else{
                        if(this.xCoord!=0){
                            tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                        }
                    }

                }
                break;
            case "Down":
                if( handler.getWorld().body.isEmpty()){
                    if(this.yCoord!=0){
                        tail=(new Tail(this.xCoord,this.yCoord-1,handler));
                    }else{
                        if(this.xCoord!=0){
                            tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                        } System.out.println("Tu biscochito");
                    }
                }else{
                    if(handler.getWorld().body.getLast().y!=0){
                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                        }
                    }

                }
                break;
        }
        handler.getWorld().body.addLast(tail);
        handler.getWorld().playerLocation[tail.x][tail.y] = true;
       // added code to Eat() to increase speed and calculate Score
        checkCollisionAndMove();
         speed= speed +1;
        Score = Math.sqrt(2*Score +1); 
        
      	System.out.println(Score);
        
    }
 
    
	 public void kill(){
        lenght = 0;
       
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

                handler.getWorld().playerLocation[i][j]= true;
            

            }
        }
    }

    public boolean isJustAte() {
   
        return justAte;
    }

    public void setJustAte(boolean justAte) {
        this.justAte = justAte;
    }

	
		
	}

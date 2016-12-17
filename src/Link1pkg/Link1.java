package Link1pkg;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Link1 extends Application {
	// named constants
	static final int CANVAS_WIDTH = 1900;
	static final int CANVAS_HEIGHT = 998;
	public static final String TITLE = "Animated Frame Demo";

	// declare variables
 private Image image = new Image("images/ZeldaMaze_Room_01_1900x998.png");
 private Image room2 = new Image("images/ZeldaRoom2.png");
 private Image room3 = new Image("images/Zelda Room3.png");
 private Image linkFE = new Image("images/zelda2.png");
 private Image swords = new Image("images/swords.png");
 private Image swordsn = new Image("images/swordsn.png");
 private Image swordsw = new Image("images/swordsw.png");
 private Image swordss = new Image("images/swordss.png");
 private Image hearts = new Image("images/hearts.png");
 private Image crab = new Image("images/crab.png");
 final ImageView FE = new ImageView(linkFE);
 public ImageView cr = new ImageView(crab);
 public ImageView cr2 = new ImageView(crab);
 public ImageView cr3 = new ImageView(crab);
 final ImageView health = new ImageView(hearts);
 final ImageView sr = new ImageView(swords);
 final ImageView sn = new ImageView(swordsn);
 final ImageView sw = new ImageView(swordsw);
 final ImageView ss = new ImageView(swordss);
 	public int dir = 1; //1 is right, 2 is down, 3 is left, 4 is up 
 	private int width = 60;		// width of the sprite
    private int height = 55;	// height of the sprite
    public int xloc = 12;
    public int yloc = 620;
    public int vpx = -1;
    public int vpy = -1;
    private int h = 30;
    public int healthY=0;
    public boolean cralive = true;
    public boolean cr2alive = true;
    public boolean cr3alive = true;
    public int currentroom =1;
    public Circle linkcollision = new Circle(xloc+30, yloc+30, 5);
    public ArrayList<ImageView> enemies = new ArrayList<ImageView>();
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// create scene and panes and add nodes

		Pane pane = new Pane();
		Pane pane2 = new Pane();
		Pane pane3 = new Pane();
		final Animation animation = new LinkAnimator(
                FE,
                Duration.millis(700),
                4, 8,
                0, 160,
                width, height
        );
        animation.setCycleCount(Animation.INDEFINITE);

		
        //pane2.getChildren().add(FE);
        //pane2.getChildren().add(text);
       // pane2.getChildren().add(cr);
       // pane2.getChildren().add(health);
       // pane2.getChildren().add(cr3);
       // pane2.getChildren().add(cr2);
       // pane.getChildren().add(sw);
        //pane.getChildren().add(linkcollision);
        BackgroundImage myBI= new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                  BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));
        BackgroundImage myBI2= new BackgroundImage(room2,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                  BackgroundSize.DEFAULT);
        pane2.setBackground(new Background(myBI2));
        BackgroundImage myBI3= new BackgroundImage(room3,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                  BackgroundSize.DEFAULT);
        pane3.setBackground(new Background(myBI3));
        Scene scene = new Scene(pane, CANVAS_WIDTH, CANVAS_HEIGHT); 
        Scene scene2 = new Scene(pane2, CANVAS_WIDTH, CANVAS_HEIGHT);
        Scene scene3 = new Scene(pane3, CANVAS_WIDTH, CANVAS_HEIGHT);
       
        
        firstRoom(pane);
        linkAnimator(pane, scene,animation);
      
	    
	 	

		// set focus
	   // FE.requestFocus(); 
	    
        // place into scene
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Start animation 
        AnimationTimer gameLoop = new AnimationTimer() {

			@Override
			public void handle(long now) {
				chase(FE,cr,cralive);
				chase(FE,cr2,cr2alive);
				chase(FE,cr3,cr3alive);
				boolean cont = healthTracker(cr, linkcollision);
				boolean cont2 = healthTracker(cr2, linkcollision);
				boolean cont3 = healthTracker(cr3, linkcollision);
				if (cont == false){pane.getChildren().remove(FE);}
				if (cont2 == false){pane.getChildren().remove(FE);}
				if (cont3 == false){pane.getChildren().remove(FE);}
				
				if (checkRoom()!=currentroom){
					if (checkRoom()==1&&currentroom ==2){
						primaryStage.setScene(scene);
						firstRoom(pane);
				        linkAnimator(pane, scene,animation);
				        currentroom = 1;
					}
					if (checkRoom()==2){
						primaryStage.setScene(scene2);
						firstRoom(pane2);
				        linkAnimator(pane2, scene2,animation);
				        currentroom = 2;
					}
					if (checkRoom()==3){
						primaryStage.setScene(scene3);
						firstRoom(pane3);
				        linkAnimator(pane3, scene3,animation);
				        currentroom = 3;
					}
				}
			}
        };
        gameLoop.start();

	} 
	
	public int checkRoom(){
		if (currentroom ==1){
			if (FE.getX()>1845){
				return 2;
			}
		}
		if (currentroom ==2){
			if (FE.getX()>1845){
				return 3;
			}
			if (FE.getX()<11){
				return 1;
			}
		}
		if (currentroom ==2){
			if (FE.getX()>1845){
				return 3;
			}
			if (FE.getX()<11){
				return 1;
			}
		}
		if (currentroom==3){
			if (FE.getX()<11){
				return 2;
			}
		}
		return currentroom;
	}
	public void firstRoom(Pane pane){
		pane.getChildren().clear();
		health.setViewport(new Rectangle2D(0,0,210,62));
        health.setX(1275);
        health.setY(165);
        if (vpx<0 && vpy<0){
            FE.setViewport(new Rectangle2D(0, 160, width, height));
        	vpx = 0; vpy =160;
        }
        sr.setViewport(new Rectangle2D(0,0,60,20));
        sr.setX(xloc+25);
        sr.setY(yloc+21);
        sn.setViewport(new Rectangle2D(0,0,20,60));
        sn.setX(xloc+25);
        sn.setY(yloc-35);
        ss.setViewport(new Rectangle2D(20,0,20,60));
        ss.setX(xloc+25);
        ss.setY(yloc+25);
        sw.setViewport(new Rectangle2D(0,20,60,20));
        sw.setX(xloc-21);
        sw.setY(yloc+25);
        
        cr.setViewport(new Rectangle2D(0, 0, 80, 80));
        cr.setX(800);
        cr.setY(800);
        cr2.setViewport(new Rectangle2D(0, 0, 80, 80));
        cr2.setX(400);
        cr2.setY(800);
        cr3.setViewport(new Rectangle2D(0, 0, 80, 80));
        cr3.setX(800);
        cr3.setY(400);
        // sets the animation of the sprite
        
        final Animation animCrab = new LinkAnimator(
                cr,
                Duration.millis(700),
                2, 1,
                0, 0,
                80, 80
        );
        animCrab.setCycleCount(Animation.INDEFINITE);
        animCrab.play();
        final Animation animCrab2 = new LinkAnimator(
                cr2,
                Duration.millis(700),
                2, 1,
                0, 0,
                80, 80
        );
        animCrab2.setCycleCount(Animation.INDEFINITE);
        animCrab2.play();
        final Animation animCrab3 = new LinkAnimator(
                cr3,
                Duration.millis(700),
                2, 1,
                0, 0,
                80, 80
        );
        animCrab3.setCycleCount(Animation.INDEFINITE);
        animCrab3.play();
        final Animation animation = new LinkAnimator(
                FE,
                Duration.millis(700),
                4, 8,
                0, 160,
                width, height
        );
        animation.setCycleCount(Animation.INDEFINITE);	// sets animation to run indefinitely
        								// plays the animation
         
        // add nodes into the pane	
        pane.getChildren().add(FE);
        pane.getChildren().add(cr);
        pane.getChildren().add(health);
        pane.getChildren().add(cr3);
        pane.getChildren().add(cr2);
        FE.setX(xloc);
	    FE.setY(yloc);
	    cralive = true;
	     cr2alive = true;
	     cr3alive = true;
	}
	// method for chasing the circle
	
	
    public void chase(ImageView link, ImageView sprite, boolean cont) {
    	// if above or below
	    if(cont){	
    		if (link.getY() > sprite.getY()) {
	    		sprite.setY(sprite.getY() + 1);
	    	}
	    	else {
	    		sprite.setY(sprite.getY() - 1);
	    	}
	    	// if to right or left
	    	if (link.getX() > sprite.getX()) {
	    		sprite.setX(sprite.getX() + 1);
	    	}
	    	else {
	    		sprite.setX(sprite.getX() - 1);
	    	}
	    }
    }

    // method thats checks for collision
    private boolean checkCollision(Node node1, Node node2) {
    	boolean collisionDetected = false;
        if (node1.getBoundsInParent().intersects(node2.getBoundsInParent())) {
            collisionDetected = true;
          }
        else {
        	collisionDetected = false;
        }
        return collisionDetected;
    }
    private boolean healthTracker(Node node1, Node node2){
    	if (checkCollision(node1,node2))
    	{
    		h--;
    		if (h <1){
    			h = 30;
    			boolean check = healthAnimator(); 
    			if (check){return true;}
    			else{return false;}
    			
    		}
    	}return true;
    }
    private boolean healthAnimator(){
    	health.setViewport(new Rectangle2D(0,healthY,210,62));
    	healthY = healthY +62;
    	if (healthY > 372){
    		health.setViewport(new Rectangle2D(0,372,210,62));
    		return false;
    	}
    	return true;
    }
    public void linkAnimator(Pane pane, Scene scene, Animation animation)
    {
	    

        animation.setCycleCount(Animation.INDEFINITE);
	    
		// register the KeyPressed event handler with line 
	    scene.setOnKeyPressed(e -> {          
	    	switch (e.getCode()) {
	    	case UP:  
	    		if (FE.getY()>250){
	    			vpx = 0; vpy = 0;
	    			dir = 4;
	    			((LinkAnimator) animation).setOffset(FE, vpx,vpy);
	    			animation.play();
	    			FE.setX(FE.getX());
		    		FE.setY(FE.getY()-5);
		    		linkcollision.setCenterX(linkcollision.getCenterX());
		    		linkcollision.setCenterY(linkcollision.getCenterY()-5);
		    		sr.setX(sr.getX());
		            sr.setY(sr.getY()-5);
		            ss.setX(ss.getX());
		            ss.setY(ss.getY()-5);
		            sn.setX(sn.getX());
		            sn.setY(sn.getY()-5);
		            sw.setX(sw.getX());
		            sw.setY(sw.getY()-5);
		    		animation.play();
	    		}
	    	
	    		
	    		break; 
	    	case DOWN: 	
	    		if (vpx == 0 && vpy ==105){
	    			FE.setX(FE.getX());
		    		FE.setY(FE.getY()+5);
		    		linkcollision.setCenterX(linkcollision.getCenterX());
		    		linkcollision.setCenterY(linkcollision.getCenterY()+5);
		    		sr.setX(sr.getX());
		            sr.setY(sr.getY()+5);
		            ss.setX(ss.getX());
		            ss.setY(ss.getY()+5);
		            sn.setX(sn.getX());
		            sn.setY(sn.getY()+5);
		            sw.setX(sw.getX());
		            sw.setY(sw.getY()+5);
		    		animation.play();
	    		}
	    		else{
	    			vpx = 0; vpy = 105;
	    			dir =2;
	    			((LinkAnimator) animation).setOffset(FE, vpx,vpy);
	    			animation.play();
	    			
	    			
	    		}
	    		
	    		break; 
	    	case LEFT: 
	    		if (vpx == 0 && vpy ==52&& FE.getX()>10){
	    			FE.setX(FE.getX() - 5);
		    		FE.setY(FE.getY());
		    		linkcollision.setCenterX(linkcollision.getCenterX()-5);
		    		linkcollision.setCenterY(linkcollision.getCenterY());
		    		sr.setX(sr.getX()-5);
		            sr.setY(sr.getY());
		            ss.setX(ss.getX()-5);
		            ss.setY(ss.getY());
		            sn.setX(sn.getX()-5);
		            sn.setY(sn.getY());
		            sw.setX(sw.getX()-5);
		            sw.setY(sw.getY());
		    		animation.play();
	    		}
	    		else{
	    			vpx = 0; vpy = 52;
	    			dir = 3;
	    			((LinkAnimator) animation).setOffset(FE, vpx,vpy);
	    			animation.play();
	    			
	    			
	    		}
	    		
	    		break; 
	    	case RIGHT: 
	    		//FE.setViewport(new Rectangle2D(0, 160, width, height));
	    		if (vpx == 0 && vpy ==160 && FE.getX()<1850){
	    			FE.setX(FE.getX() + 5);
		    		FE.setY(FE.getY());
		    		linkcollision.setCenterX(linkcollision.getCenterX()+5);
		    		linkcollision.setCenterY(linkcollision.getCenterY());
		    		sr.setX(sr.getX()+5);
		            sr.setY(sr.getY());
		            ss.setX(ss.getX()+5);
		            ss.setY(ss.getY());
		            sn.setX(sn.getX()+5);
		            sn.setY(sn.getY());
		            sw.setX(sw.getX()+5);
		            sw.setY(sw.getY());
		    		animation.play();
	    		}
	    		else{
	    			vpx = 0; vpy = 160;
	    			dir = 1;
	    			((LinkAnimator) animation).setOffset(FE, vpx,vpy);
	    			animation.play();
	    			
	    			
	    		}
	    		
	    		break; 
	    	case Z:
	    		animation.pause();
	    		if (dir == 1){
	    			pane.getChildren().add(sr);
		    		if (checkCollision(cr,sr)){
		    			pane.getChildren().remove(cr);
		    			cralive = false;
		    			cr.setX(0);
		    			cr.setY(0);
		    		}
		    		if (checkCollision(cr2,sr)){
		    			pane.getChildren().remove(cr2);
		    			cr2alive=false;
		    			cr2.setX(0);
		    			cr2.setY(0);
		    		}
		    		if (checkCollision(cr3,sr)){
		    			pane.getChildren().remove(cr3);
		    			cr3alive = false;
		    			cr3.setX(0);
		    			cr3.setY(0);
		    		}
		    	}
	    		if (dir == 2){
	    			pane.getChildren().add(ss);
		    		if (checkCollision(cr,ss)){
		    			pane.getChildren().remove(cr);
		    			cralive = false;
		    			cr.setX(0);
		    			cr.setY(0);
		    		}
		    		if (checkCollision(cr2,ss)){
		    			pane.getChildren().remove(cr2);
		    			cr2alive=false;
		    			cr2.setX(0);
		    			cr2.setY(0);
		    		}
		    		if (checkCollision(cr3,ss)){
		    			pane.getChildren().remove(cr3);
		    			cr3alive = false;
		    			cr3.setX(0);
		    			cr3.setY(0);
		    		}
	    		}
	    		if (dir == 3){
	    			pane.getChildren().add(sw);
		    		if (checkCollision(cr,sw)){
		    			pane.getChildren().remove(cr);
		    			cralive = false;
		    			cr.setX(0);
		    			cr.setY(0);
		    		}
		    		if (checkCollision(cr2,sw)){
		    			pane.getChildren().remove(cr2);
		    			cr2alive=false;
		    			cr2.setX(0);
		    			cr2.setY(0);
		    		}
		    		if (checkCollision(cr3,sw)){
		    			pane.getChildren().remove(cr3);
		    			cr3alive = false;
		    			cr3.setX(0);
		    			cr3.setY(0);
		    		}
		    	}
	    		if (dir == 4){
	    			pane.getChildren().add(sn);
		    		if (checkCollision(cr,sn)){
		    			pane.getChildren().remove(cr);
		    			cralive = false;
		    			cr.setX(0);
		    			cr.setY(0);
		    		}
		    		if (checkCollision(cr2,sn)){
		    			pane.getChildren().remove(cr2);
		    			cr2alive=false;
		    			cr2.setX(0);
		    			cr2.setY(0);
		    		}
		    		if (checkCollision(cr3,sn)){
		    			pane.getChildren().remove(cr3);
		    			cr3alive = false;
		    			cr3.setX(0);
		    			cr3.setY(0);
		    		}
	    		}
	    	default:
	    		
	    		break;
	    	}
	    });
	    scene.setOnKeyReleased(e -> {          
	    	switch (e.getCode()) {
	    	default: FE.setViewport(new Rectangle2D(0, 160, width, height));
	    	case UP: animation.pause();vpx = -1; vpy =-1;
	    	case DOWN: animation.pause(); vpx = -1; vpy =-1;
	    	case RIGHT: animation.pause();vpx = -1; vpy =-1;
	    	case LEFT: animation.pause();vpx = -1; vpy =-1;
	    	case Z: 
	    		pane.getChildren().remove(sr);
	    		pane.getChildren().remove(ss);
	    		pane.getChildren().remove(sw);
	    		pane.getChildren().remove(sn);
	    	}
	    });
    }
    
    

    // main method
    public static void main(String[] args) {
        launch(args);
    } // main
}
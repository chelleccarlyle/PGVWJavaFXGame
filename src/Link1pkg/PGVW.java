package Link1pkg;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.lang.Math;

public class PGVW extends Application {
	// named constants
	static final int CANVAS_WIDTH = 1250;
	static final int CANVAS_HEIGHT = 657;
	public static final String TITLE = "Animated Frame Demo";

	// declare variables
	
	 private Image forestLevel1 = new Image("images/Forrest.png");
	 private Image facilityLevel2 = new Image("images/rsz_govt-facility.jpg");
	 private Image udLevel1 = new Image("images/upside-down-level1.png");
	 private Image udLevel2 = new Image("images/upside-down-level2.jpg");
	 private Image udLevel3 = new Image("images/upside-down-final.png");
	 private Image snow = new Image("images/snow-resized.gif");
	 private Image linkFE = new Image("images/main-player.png");
	 private Image swords = new Image("images/stick-right.png");
	 private Image swordsn = new Image("images/stick-north.png");
	 private Image swordsw = new Image("images/stick-left.png");
	 private Image swordss = new Image("images/stick-south.png");
	 private Image hearts = new Image("images/hearts.png");
	 private Image crab = new Image("images/crab.png");
	 private Image mainMenu = new Image("images/mainmenu.jpg");
	 private Image bully1 = new Image("images/bully1.png");
	 private Image bully2 = new Image("images/bully2.png");
	 private Image eleven = new Image("images/eleven.png");
	 private Image linkFE2 = new Image("images/main-player2.png");
	 private Image portal = new Image("images/portal-spritesheet.png");
	 public Image boss = new Image("images/boss.png");
	 public Image will = new Image("images/will-sprite (1).png");
	 
 private Image fbi = new Image("images/FBI_walk_cycle.png");
 final ImageView FE = new ImageView(linkFE);
 final ImageView health = new ImageView(hearts);
 final ImageView sr = new ImageView(swords);
 final ImageView sn = new ImageView(swordsn);
 final ImageView sw = new ImageView(swordsw);
 final ImageView ss = new ImageView(swordss);
 final ImageView e = new ImageView(eleven);
 final ImageView b = new ImageView(boss);
 final ImageView w = new ImageView(will);


 	public int dir = 1; //1 is right, 2 is down, 3 is left, 4 is up 
 	private int width = 56;		// width of the sprite
    private int height = 56;	// height of the sprite
    public int xloc = 12;
    public int yloc = 320;
    public int vpx = -1;
    public int vpy = -1;
    private int h = 30;
    public int healthY=0;
    public int bossHealth = 30;
    
    //Number of crabs to be spawned in final level
    public int numOfCrabs = 5;
    
    public int currentroom =1;
    public int level = 1;
    public int speed=8;
    public Circle linkcollision = new Circle(xloc+30, yloc+30, 5);
    public ArrayList<ImageView> enemies = new ArrayList<ImageView>();
    public ArrayList<Boolean> enemyHealth = new ArrayList<Boolean>();
    public ArrayList<Animation> enemyAnimation = new ArrayList<Animation>();
    private int start = 0;
    private int playerC = 0;
    
    //Music
    File themeMusic = new File("/Users/chellecruz/Documents/workspace/A_12_BernardBollinger/src/stranger-things.mp3");
	Media themeTrack = new Media(themeMusic.toURI().toString());
	MediaPlayer mediaPlayerTheme = new MediaPlayer(themeTrack);
	
	//Demogorgon sound
	File demoSound = new File("/Users/chellecruz/Documents/workspace/A_12_BernardBollinger/src/Demogorgon.m4a");
	Media demoTrack = new Media(demoSound.toURI().toString());
	MediaPlayer mediaPlayerDemo = new MediaPlayer(demoTrack);
	
	//Stick sound
	File stickSound = new File("/Users/chellecruz/Documents/workspace/A_12_BernardBollinger/src/stick-sound.mp3");
	Media stickTrack = new Media(stickSound.toURI().toString());
	MediaPlayer mediaPlayerStick = new MediaPlayer(stickTrack);
    
   	@Override
   	public void start(Stage primaryStage) throws Exception {
   		
   		//PLAY THEME MUSIC
   		mediaPlayerTheme.setCycleCount(MediaPlayer.INDEFINITE);
   		mediaPlayerTheme.play();
   		
   		
   		BorderPane  border = new BorderPane();
   		BorderPane innerBorder = new BorderPane();
   		
   		//Buttons
   		VBox sp1 = new VBox();
   		
   		Button startGame = new Button("Start Game");
   		startGame.setStyle("-fx-text-fill: #efefef; -fx-background-color: #d91f26; "
	    		+ "-fx-border-radius: 0; -fx-padding: 10 20 10 20;");
   		Button characterSelect = new Button("Select Character");
   		characterSelect.setStyle("-fx-text-fill: #efefef; -fx-background-color: #d91f26; "
	    		+ "-fx-border-radius: 0; -fx-padding: 10 20 10 20;");
   		Button loadGame = new Button("Load Game");
   		loadGame.setStyle("-fx-text-fill: #efefef; -fx-background-color:#d91f26; "
	    		+ "-fx-border-radius: 0; -fx-padding: 10 20 10 20;");
   		
   		Pane pane = new Pane();
   		
   		//Vertical Box for buttons
   		sp1.getChildren().addAll(startGame, characterSelect, loadGame);
   		sp1.setAlignment(Pos.CENTER);
   		sp1.setSpacing(10);
   		
   		innerBorder.setBottom(sp1);
   		
   		border.setCenter(innerBorder);
   		BackgroundImage mainBI= new BackgroundImage(mainMenu,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
   		border.setBackground(new Background(mainBI));
        Scene scene1 = new Scene(border, CANVAS_WIDTH, CANVAS_HEIGHT);

   		
   		
   		/*
   		 * Character select follow format to add more players
   		 */
        characterSelect.setOnAction(e->{
        	if(playerC == 0){
        		playerC = 1;
        		//Sets image to B/W image
        		FE.setImage(linkFE2);
        		Label bwCharacter = new Label("You've selected Dustin (B&W)");
        		bwCharacter.setStyle("-fx-text-fill: #efefef;");
        		border.setTop(bwCharacter);
        		
        	}else if(playerC == 1){
        		playerC = 0;
        		//Sets image back to regular
        		FE.setImage(linkFE);
        		Label cCharacter = new Label("You've selected Dustin (Color)");
        		border.setTop(cCharacter);
        		cCharacter.setStyle("-fx-text-fill: #efefef;");
        	}	
        	
        });
        
        	// create scene and panes and add nodes
        	final Animation animation = new LinkAnimator(
        			FE,
		            Duration.millis(700),
		            4, 8,
		            0, 160,
		            width, height
		    );
   		
           animation.setCycleCount(Animation.INDEFINITE);
           Scene scene2 = new Scene(pane, CANVAS_WIDTH, CANVAS_HEIGHT); 
           BackgroundImage myBI= checkBackground(currentroom, level);
           pane.setBackground(new Background(myBI));
           newRoom(pane);
           linkAnimator(pane, scene2,animation);
           health.setViewport(new Rectangle2D(0,0,210,62));
           health.setX(0);
           health.setY(0);
           if (vpx<0 && vpy<0){
               FE.setViewport(new Rectangle2D(0, 160, width, height));
           	vpx = 0; vpy =160;
           }

           //Start Game on button press
           startGame.setOnAction(e->{
           	if(start == 0){
   		    primaryStage.close();
   	        primaryStage.setTitle(TITLE);
   	        primaryStage.setScene(scene2);
   	        primaryStage.show();
   	        
   	        AnimationTimer gameLoop = new AnimationTimer() {

   				@Override
   				
   				public void handle(long now) {
   					
   					if(currentroom ==6 || currentroom ==7||currentroom ==8){chase(FE,enemies,enemyHealth);}
   					else{chase2(FE, enemies, enemyHealth,enemyAnimation);}
   					if(currentroom ==2&&checkPowerUp()){speed =15;
   					Text a = new Text();
   					a.setText("I have increased your speed! Go find Will.");
   					pane.getChildren().add(a);
   					a.setX(800);
   					a.setY(190);}
   					if(currentroom ==8&&checkCollision(FE, w)){
   					Text gameOver = new Text();
   					gameOver.setText("YOU FOUND WILL! GOOD JOB! GAME OVER");
   					gameOver.setFill(Color.WHITE);
   					pane.getChildren().add(gameOver);
   					gameOver.setX(800);
   					gameOver.setY(190);}
   					
   					boolean cont = healthTracker(enemies, linkcollision);
   					//changeRoom(pane);
   			       //linkAnimator(pane, scene,animation);
   					
   					if (cont == false){pane.getChildren().remove(FE);
   					CleanUp();
   					
   					primaryStage.close();
   					primaryStage.setTitle(TITLE);
   		   	        primaryStage.setScene(scene1);
   		   	        primaryStage.show();
   					}		
   					if (checkRoom()!=currentroom){
   						int oldroom = currentroom;
   						currentroom = checkRoom();
   						changeRoom(pane,scene2,animation, oldroom);
   						
   					}
   					
   					
   				}
   				
   	        };
   	        
   	        gameLoop.start();
           	}else{
           		start = 0;
           	}
   		    
   });
         
          
           
           //If button is not pressed start Game Menu
           primaryStage.setTitle(TITLE);
           primaryStage.setScene(scene1);
           primaryStage.show();

   	}
   	public boolean checkPowerUp(){
   		return checkCollision(FE, e);
   	}
   	public void CleanUp(){
		dir = 1; //1 is right, 2 is down, 3 is left, 4 is up 
	 	width = 56;		// width of the sprite
	 	height = 56;	// height of the sprite
	    xloc = 12;
	    yloc = 320;
	    vpx = -1;
	    vpy = -1;
	    h = 30;
	    healthY=0;
	    
	    currentroom =1;
	    level = 1;
	    speed=5;
	    linkcollision = new Circle(xloc+30, yloc+30, 5);
	    enemies.clear();
	    enemyHealth.clear();
	    enemyAnimation.clear();
	    
	    
	}

	public void changeRoom(Pane pane, Scene scene, Animation animation, int oldroom){
		BackgroundImage myBI= checkBackground(currentroom, level);
        pane.setBackground(new Background(myBI));
        //if (currentroom == 6 ||currentroom ==7) {
	 //       pane.setBackground(new Background(myBI, new BackgroundImage(snow,  BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
	 //           BackgroundSize.DEFAULT)));
	//	}
	//	else {
	//		pane.setBackground(new Background(myBI));
	//	}

        newRoom(pane);
	    linkAnimator(pane, scene,animation);
	    if (oldroom>currentroom){
	    	FE.setX(1240);
	    	linkcollision = new Circle(FE.getX()+30, FE.getY()+30, 5);
	    	placeWeapons();
	    	
	    }
	}
	public BackgroundImage checkBackground(int room, int lvl){
		BackgroundImage myBI = new BackgroundImage(forestLevel1,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
		if (room ==1 ||room ==2 ||room ==3){ myBI= new BackgroundImage(forestLevel1,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);}
		else if (room ==4 || room ==5){
			myBI= new BackgroundImage(facilityLevel2,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
		}
		else if (room ==6){
			myBI= new BackgroundImage(udLevel1,
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
		}
		else if (room ==7){
			myBI= new BackgroundImage(udLevel2,
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
		}
		else if (room ==8){
			myBI= new BackgroundImage(udLevel3,
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
		}
		
		
		return myBI;
	}
	public int checkRoom(){
		if (currentroom ==1){
			if (FE.getX()>1240){
				return 2;
			}
			return 1;
		}
		
		else if (currentroom==8){
			if (FE.getX()<11){
				return 7;
			}
		return 8;
		}
		else{
			if (FE.getX()>1240){
				return currentroom+1;
			}
			if (FE.getX()<11){
				return currentroom-1;
			}
			return currentroom;
		}
	}
	public void newRoom(Pane pane){
		pane.getChildren().clear();
        if (currentroom ==1){
        	enemies.clear();
        	enemyHealth.clear();
        	enemyAnimation.clear();
        	final ImageView b = new ImageView(bully1);
        	final ImageView b1 = new ImageView(bully1);
        	final ImageView b2 = new ImageView(bully1);
        	enemies.add(0, b);
       		enemies.add(1, b1);
       		enemies.add(2, b2);
       		for(int i = 0; i<enemies.size(); i++){
                enemies.get(i).setViewport(new Rectangle2D(0, 0, 64, 64));
                enemies.get(i).setX(500-(100*i));
                enemies.get(i).setY(200+100*i);
                enemyHealth.add(true);
                pane.getChildren().add(enemies.get(i));
                }
       	 final Animation animB = new LinkAnimator(
                 enemies.get(0),
                 Duration.millis(700),
                 4, 4,
                 0, 64,
                 63, 63
         );
         animB.setCycleCount(Animation.INDEFINITE);
         animB.play();
         final Animation animB2 = new LinkAnimator(
         		enemies.get(1),
                 Duration.millis(700),
                 4, 4,
                 0, 64,
                 63, 64
         );
         animB2.setCycleCount(Animation.INDEFINITE);
         animB2.play();
         final Animation animB3 = new LinkAnimator(
         		enemies.get(2),
                 Duration.millis(700),
                 4, 4,
                 0, 64,
                 64, 63
         );
         animB3.setCycleCount(Animation.INDEFINITE);
         animB3.play();
         enemyAnimation.add(0, animB);
         enemyAnimation.add(1, animB2);
         enemyAnimation.add(2, animB3);
           
        }
        if (currentroom == 2){
        	enemies.clear();
        	enemyHealth.clear();
        	enemyAnimation.clear();
        	e.setViewport(new Rectangle2D(0,0,89,66));
        	e.setX(800);
        	e.setY(200);
        	 final Animation animE = new LinkAnimator(
                     e,
                     Duration.millis(700),
                     1, 4,
                     0, 0,
                    88, 66
             );
             animE.setCycleCount(Animation.INDEFINITE);
             animE.play();
             pane.getChildren().add(e);
        }
        if (currentroom == 3){
        	enemies.clear();
        	enemyHealth.clear();
        	enemyAnimation.clear();
        	final ImageView b = new ImageView(bully2);
        	final ImageView b1 = new ImageView(bully2);
        	final ImageView b2 = new ImageView(bully2);
        	enemies.add(0, b);
       		enemies.add(1, b1);
       		enemies.add(2, b2);
       		for(int i = 0; i<enemies.size(); i++){
                enemies.get(i).setViewport(new Rectangle2D(0, 0, 64, 64));
                enemies.get(i).setX(500-(100*i));
                enemies.get(i).setY(300+100*i);
                enemyHealth.add(true);
                pane.getChildren().add(enemies.get(i));
                }
       	 final Animation animB = new LinkAnimator(
                 enemies.get(0),
                 Duration.millis(700),
                 4, 9,
                 0, 64,
                 64, 64
         );
         animB.setCycleCount(Animation.INDEFINITE);
         animB.play();
         final Animation animB2 = new LinkAnimator(
         		enemies.get(1),
                 Duration.millis(700),
                 4, 9,
                 0, 64,
                 64, 64
         );
         animB2.setCycleCount(Animation.INDEFINITE);
         animB2.play();
         final Animation animB3 = new LinkAnimator(
         		enemies.get(2),
                 Duration.millis(700),
                 4, 9,
                 0, 64,
                 64, 64
         );
         animB3.setCycleCount(Animation.INDEFINITE);
         animB3.play();
         enemyAnimation.add(0, animB);
         enemyAnimation.add(1, animB2);
         enemyAnimation.add(2, animB3);
        }
        if (currentroom == 4){
        	enemies.clear();
        	enemyHealth.clear();
        	enemyAnimation.clear();
        	final ImageView fbiBadGuy = new ImageView(fbi);
        	final ImageView fbiBadGuy1 = new ImageView(fbi);
        	final ImageView fbiBadGuy2 = new ImageView(fbi);
        	enemies.add(0, fbiBadGuy);
       		enemies.add(1, fbiBadGuy1);
       		enemies.add(2, fbiBadGuy2);
       		for(int i = 0; i<enemies.size(); i++){
                enemies.get(i).setViewport(new Rectangle2D(0, 0, 64, 64));
                enemies.get(i).setX(500-(100*i));
                enemies.get(i).setY(300+100*i);
                enemyHealth.add(true);
                pane.getChildren().add(enemies.get(i));
                }
       	 final Animation animFBI = new LinkAnimator(
                 enemies.get(0),
                 Duration.millis(700),
                 4, 9,
                 0, 64,
                 64, 64
         );
         animFBI.setCycleCount(Animation.INDEFINITE);
         animFBI.play();
         final Animation animFBI2 = new LinkAnimator(
         		enemies.get(1),
                 Duration.millis(700),
                 4, 9,
                 0, 64,
                 64, 64
         );
         animFBI2.setCycleCount(Animation.INDEFINITE);
         animFBI2.play();
         final Animation animFBI3 = new LinkAnimator(
         		enemies.get(2),
                 Duration.millis(700),
                 4, 9,
                 0, 64,
                 64, 64
         );
         animFBI3.setCycleCount(Animation.INDEFINITE);
         animFBI3.play();
         enemyAnimation.add(0, animFBI);
         enemyAnimation.add(1, animFBI2);
         enemyAnimation.add(2, animFBI3);
        	
        	
        }
        if (currentroom == 5){
        	enemies.clear();
        	enemyHealth.clear();
        	enemyAnimation.clear();
        	final ImageView fbiBadGuy = new ImageView(fbi);
        	final ImageView fbiBadGuy1 = new ImageView(fbi);
        	final ImageView fbiBadGuy2 = new ImageView(fbi);
        	enemies.add(0, fbiBadGuy);
       		enemies.add(1, fbiBadGuy1);
       		enemies.add(2, fbiBadGuy2);
       		for(int i = 0; i<enemies.size(); i++){
                enemies.get(i).setViewport(new Rectangle2D(0, 0, 64, 64));
                enemies.get(i).setX(500-(100*i));
                enemies.get(i).setY(300+100*i);
                enemyHealth.add(true);
                pane.getChildren().add(enemies.get(i));
                }
       	 final Animation animFBI = new LinkAnimator(
                 enemies.get(0),
                 Duration.millis(700),
                 4, 9,
                 0, 64,
                 64, 64
         );
         animFBI.setCycleCount(Animation.INDEFINITE);
         animFBI.play();
         final Animation animFBI2 = new LinkAnimator(
         		enemies.get(1),
                 Duration.millis(700),
                 4, 9,
                 0, 64,
                 64, 64
         );
         animFBI2.setCycleCount(Animation.INDEFINITE);
         animFBI2.play();
         final Animation animFBI3 = new LinkAnimator(
         		enemies.get(2),
                 Duration.millis(700),
                 4, 9,
                 0, 64,
                 64, 64
         );
         animFBI3.setCycleCount(Animation.INDEFINITE);
         animFBI3.play();
         enemyAnimation.add(0, animFBI);
         enemyAnimation.add(1, animFBI2);
         enemyAnimation.add(2, animFBI3);
         final ImageView por = new ImageView(portal);
         por.setViewport(new Rectangle2D(0,0,49,60 ));
         por.setX(1190);
         por.setY(350);
         final Animation p = new LinkAnimator(
          		por,
                  Duration.millis(700),
                  1, 9,
                  0, 0,
                  49, 60
          );
          p.setCycleCount(Animation.INDEFINITE);
          p.play();
          pane.getChildren().add(por);
        	
        }
        
        if(currentroom == 6){
        	enemies.clear();
        	enemyHealth.clear();
        	enemyAnimation.clear();
        	final ImageView cr = new ImageView(crab);
        	final ImageView cr2 = new ImageView(crab);
        	final ImageView cr3 = new ImageView(crab);
        	enemies.add(0, cr);
       		enemies.add(1, cr2);
       		enemies.add(2, cr3);
       		for(int i = 0; i<enemies.size(); i++){
                enemies.get(i).setViewport(new Rectangle2D(0, 0, 80, 80));
                enemies.get(i).setX(500-(100*i));
                enemies.get(i).setY(300+100*i);
                enemyHealth.add(true);
                pane.getChildren().add(enemies.get(i));
                }
       	 final Animation animFBI = new LinkAnimator(
                 enemies.get(0),
                 Duration.millis(700),
                 2, 1,
                 0, 0,
                 80, 80
         );
         animFBI.setCycleCount(Animation.INDEFINITE);
         animFBI.play();
         final Animation animFBI2 = new LinkAnimator(
         		enemies.get(1),
                 Duration.millis(700),
                 2, 1,
                 0, 0,
                 80, 80
         );
         animFBI2.setCycleCount(Animation.INDEFINITE);
         animFBI2.play();
         final Animation animFBI3 = new LinkAnimator(
         		enemies.get(2),
                 Duration.millis(700),
                 2, 1,
                 0, 0,
                 80, 80
         );
         animFBI3.setCycleCount(Animation.INDEFINITE);
         animFBI3.play();
         enemyAnimation.add(0, animFBI);
         enemyAnimation.add(1, animFBI2);
         enemyAnimation.add(2, animFBI3);
        }
        if (currentroom == 7){
        	enemies.clear();
        	enemyHealth.clear();
        	enemyAnimation.clear();
        	final ImageView cr = new ImageView(crab);
        	final ImageView cr2 = new ImageView(crab);
        	final ImageView cr3 = new ImageView(crab);
        	enemies.add(0, cr);
       		enemies.add(1, cr2);
       		enemies.add(2, cr3);
       		for(int i = 0; i<enemies.size(); i++){
                enemies.get(i).setViewport(new Rectangle2D(0, 0, 80, 80));
                enemies.get(i).setX(500-(100*i));
                enemies.get(i).setY(300+100*i);
                enemyHealth.add(true);
                pane.getChildren().add(enemies.get(i));
                }
       	 final Animation animFBI = new LinkAnimator(
                 enemies.get(0),
                 Duration.millis(700),
                 2, 1,
                 0, 0,
                 80, 80
         );
         animFBI.setCycleCount(Animation.INDEFINITE);
         animFBI.play();
         final Animation animFBI2 = new LinkAnimator(
         		enemies.get(1),
                 Duration.millis(700),
                 2, 1,
                 0, 0,
                 80, 80
         );
         animFBI2.setCycleCount(Animation.INDEFINITE);
         animFBI2.play();
         final Animation animFBI3 = new LinkAnimator(
         		enemies.get(2),
                 Duration.millis(700),
                 2, 1,
                 0, 0,
                 80, 80
         );
         animFBI3.setCycleCount(Animation.INDEFINITE);
         animFBI3.play();
         enemyAnimation.add(0, animFBI);
         enemyAnimation.add(1, animFBI2);
         enemyAnimation.add(2, animFBI3);
        }
        if (currentroom ==8){
        	enemies.clear();
        	enemyHealth.clear();
        	enemyAnimation.clear();
        	
        	numOfCrabs = 5;
        	
        	b.setViewport(new Rectangle2D(5,0,132,123 ));
            b.setX(1100);
            b.setY(350);
            pane.getChildren().add(b);
            
            //PLay demogorgon sound
            mediaPlayerDemo.play();
       		
       		//Add number of enemies you want
       		for (int i = 0; i < numOfCrabs; i++) {
       			
       		   enemies.add(i, new ImageView(crab));
       			
       		   enemies.get(i).setViewport(new Rectangle2D(0, 0, 80, 80));
               enemies.get(i).setX(500-(100*i));
               enemies.get(i).setY(300+100*i);
               enemyHealth.add(i, true);
               pane.getChildren().add(enemies.get(i));
               
               final Animation animFBI = new LinkAnimator(
                       enemies.get(i),
                       Duration.millis(700),
                       2, 1,
                       0, 0,
                       80, 80
               );
               
               animFBI.setCycleCount(Animation.INDEFINITE);
               animFBI.play();
               
               enemyAnimation.add(i, animFBI);
               
       		}
       		
        }
        
        // sets the animation of the sprite
        
       
       	// sets animation to run indefinitely
        								// plays the animation
         
        // add nodes into the pane	
        pane.getChildren().add(FE);
        pane.getChildren().add(health);
        
        FE.setX(xloc);
	    FE.setY(yloc);
	    placeWeapons();
	    linkcollision = new Circle(xloc+30, yloc+30, 5);
	   
	}
	// method for chasing the circle
public void placeWeapons(){
	sr.setViewport(new Rectangle2D(0,0,60,20));
    sr.setX(FE.getX()+25);
    sr.setY(FE.getY()+21);
    sn.setViewport(new Rectangle2D(0,0,20,60));
    sn.setX(FE.getX()+25);
    sn.setY(FE.getY()-35);
    ss.setViewport(new Rectangle2D(20,0,20,60));
    ss.setX(FE.getX()+25);
    ss.setY(FE.getY()+25);
    sw.setViewport(new Rectangle2D(0,20,60,20));
    sw.setX(FE.getX()-21);
    sw.setY(FE.getY()+25);
	
}
	
public void chase(ImageView link, ArrayList<ImageView> sprite, ArrayList<Boolean> cont) {
    	
      	for (int i = 0; i<sprite.size(); i++){
        	
        	double chaseY = link.getY() - (sprite.get(i).getY());
    		double chaseX = link.getX() - (sprite.get(i).getX());
    		
    		//Finds fastest route to player
    		double enemyChase = Math.sqrt(chaseX * chaseX + chaseY * chaseY);
    		
    		//1.5 == enemy speed
    		chaseX *= 2.5 / enemyChase;
    		chaseY *= 2.5 / enemyChase;
    		
      		if(cont.get(i)){	
        				sprite.get(i).setY(sprite.get(i).getY() + chaseY);
        				sprite.get(i).setX(sprite.get(i).getX() + chaseX);
        		}
    	}

    	
    }
public void chase2(ImageView link, ArrayList<ImageView> sprite, ArrayList<Boolean> cont, ArrayList<Animation> animation){
	for (int i = 0; i<sprite.size(); i++){
		double chaseY = link.getY() - (sprite.get(i).getY());
		double chaseX = link.getX() - (sprite.get(i).getX());
		chaseX = chaseX*20;
		//up
		if (chaseY<0 && Math.abs(chaseY)>=Math.abs(chaseX) && cont.get(i)){
			((LinkAnimator) animation.get(i)).setOffset(sprite.get(i), 0,0);
			sprite.get(i).setY(sprite.get(i).getY() - 2);
			sprite.get(i).setX(sprite.get(i).getX());
		}
		//down
		else if (chaseY>=0 && Math.abs(chaseY)>=Math.abs(chaseX) && cont.get(i)){
			((LinkAnimator) animation.get(i)).setOffset(sprite.get(i), 0,128);
			sprite.get(i).setY(sprite.get(i).getY() + 2);
			sprite.get(i).setX(sprite.get(i).getX());
		}
		//right
		else if (chaseX>=0 && Math.abs(chaseY)<Math.abs(chaseX) && cont.get(i)){
			((LinkAnimator) animation.get(i)).setOffset(sprite.get(i), 0,192);
			sprite.get(i).setY(sprite.get(i).getY());
			sprite.get(i).setX(sprite.get(i).getX()+2);
			
		}
		//left
		else if (chaseX<0 && Math.abs(chaseY)<Math.abs(chaseX) && cont.get(i)){
			((LinkAnimator) animation.get(i)).setOffset(sprite.get(i), 0,64);
			sprite.get(i).setY(sprite.get(i).getY());
			sprite.get(i).setX(sprite.get(i).getX()-2);
			
		}
		animation.get(i).play();
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
    private boolean healthTracker(ArrayList<ImageView> node1, Node node2){
    	for (int i = 0; i<node1.size(); i++){
    		if (checkCollision(node1.get(i),node2))
    		{
    			h--;
    			if (h <1){
    				h = 30;
    				boolean check = healthAnimator(); 
    				if (check){return true;}
    				else{return false;}
    			
    			}
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
	    		Circle boundaryu = new Circle(FE.getX()+30, FE.getY()+30-speed, 5);
	    		if (!isBoundary(boundaryu)){
	    			vpx = 0; vpy = 0;
	    			dir = 4;
	    			((LinkAnimator) animation).setOffset(FE, vpx,vpy);
	    			animation.play();
	    			FE.setX(FE.getX());
		    		FE.setY(FE.getY()-speed);
		    		linkcollision.setCenterX(linkcollision.getCenterX());
		    		linkcollision.setCenterY(linkcollision.getCenterY()-speed);
		    		sr.setX(sr.getX());
		            sr.setY(sr.getY()-speed);
		            ss.setX(ss.getX());
		            ss.setY(ss.getY()-speed);
		            sn.setX(sn.getX());
		            sn.setY(sn.getY()-speed);
		            sw.setX(sw.getX());
		            sw.setY(sw.getY()-speed);
		    		animation.play();
	    		}
	    	
	    		
	    		break; 
	    	case DOWN: 	
	    		Circle boundaryd = new Circle(FE.getX()+30, FE.getY()+30+speed, 5);
	    		if (vpx == 0 && vpy ==105 && !isBoundary(boundaryd)){
	    			FE.setX(FE.getX());
		    		FE.setY(FE.getY()+speed);
		    		linkcollision.setCenterX(linkcollision.getCenterX());
		    		linkcollision.setCenterY(linkcollision.getCenterY()+speed);
		    		sr.setX(sr.getX());
		            sr.setY(sr.getY()+speed);
		            ss.setX(ss.getX());
		            ss.setY(ss.getY()+speed);
		            sn.setX(sn.getX());
		            sn.setY(sn.getY()+speed);
		            sw.setX(sw.getX());
		            sw.setY(sw.getY()+speed);
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
	    		Circle boundaryl = new Circle(FE.getX()+30-speed, FE.getY()+30, 5);
	    		if (vpx == 0 && vpy ==52 && !isBoundary(boundaryl)){
	    			FE.setX(FE.getX() - speed);
		    		FE.setY(FE.getY());
		    		linkcollision.setCenterX(linkcollision.getCenterX()-speed);
		    		linkcollision.setCenterY(linkcollision.getCenterY());
		    		sr.setX(sr.getX()-speed);
		            sr.setY(sr.getY());
		            ss.setX(ss.getX()-speed);
		            ss.setY(ss.getY());
		            sn.setX(sn.getX()-speed);
		            sn.setY(sn.getY());
		            sw.setX(sw.getX()-speed);
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
	    		Circle boundaryr = new Circle(FE.getX()+30+speed, FE.getY()+30, 5);
	    		//FE.setViewport(new Rectangle2D(0, 160, width, height));
	    		if (vpx == 0 && vpy ==160 && !isBoundary(boundaryr)){
	    			FE.setX(FE.getX() + speed);
		    		FE.setY(FE.getY());
		    		linkcollision.setCenterX(linkcollision.getCenterX()+speed);
		    		linkcollision.setCenterY(linkcollision.getCenterY());
		    		sr.setX(sr.getX()+speed);
		            sr.setY(sr.getY());
		            ss.setX(ss.getX()+speed);
		            ss.setY(ss.getY());
		            sn.setX(sn.getX()+speed);
		            sn.setY(sn.getY());
		            sw.setX(sw.getX()+speed);
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
	    		//Play stick sound
	    		mediaPlayerStick.stop();
	    		mediaPlayerStick.play();
	    		
	    		animation.pause();
	    		if (dir == 1){
	    			pane.getChildren().add(sr);
	    			checkEnemyCollision(sr,pane);
	    			if (currentroom ==8){
	    				checkBossCollision(sr,pane);
	    			}
		    	}
	    		if (dir == 2){
	    			pane.getChildren().add(ss);
	    			checkEnemyCollision(ss,pane);
	    			if (currentroom ==8){
	    				checkBossCollision(ss,pane);
	    			}
	    		}
	    		if (dir == 3){
	    			pane.getChildren().add(sw);
	    			checkEnemyCollision(sw,pane);
	    			if (currentroom ==8){
	    				checkBossCollision(sw,pane);
	    			}
		    	}
	    		if (dir == 4){
	    			pane.getChildren().add(sn);
	    			checkEnemyCollision(sn,pane);
	    			if (currentroom ==8){
	    				checkBossCollision(sn,pane);
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
    public void checkBossCollision(ImageView a, Pane pane){
    	
    	if(checkCollision(b,a) && bossHealth>0){
    		bossHealth = bossHealth - 10;
    		//Increase number of crabs as boss gets hit
    		numOfCrabs = numOfCrabs * 2;
    		addCrabs(pane, numOfCrabs);
    		FE.setX(xloc);
    		FE.setY(yloc);
    		linkcollision = new Circle(FE.getX()+30, FE.getY()+30, 5);
    		placeWeapons();
    		System.out.println(bossHealth);
    	}
    	if (bossHealth <=0){
    		pane.getChildren().remove(b);
    		w.setViewport(new Rectangle2D(0,0,56,56 ));
            w.setX(1100);
            w.setY(350);
            pane.getChildren().add(w);
    		
    	}
    }
    public void checkEnemyCollision(ImageView a, Pane pane){
    	for(int i = 0; i<enemies.size(); i++){
    		if (checkCollision(enemies.get(i),a)){
    			pane.getChildren().remove(enemies.get(i));
    			enemyHealth.set(i,false);
    			enemies.get(i).setX(0);
    			enemies.get(i).setY(0);
    		}
    		
    	}
    }
    
    public void addCrabs(Pane pane, int numOfCrabs){
    	enemies.clear();
    	enemyHealth.clear();
    	enemyAnimation.clear();
    
    	//Number of enemies (crabs)
   		for(int i = 0; i < numOfCrabs; i++){
   			
   			enemies.add(i, new ImageView(crab));
   			
            enemies.get(i).setViewport(new Rectangle2D(0, 0, 80, 80));
            //Random position
            enemies.get(i).setX(new Random().nextInt(1220 - 10 + 1) + 10);
            enemies.get(i).setY(new Random().nextInt(640 - 10 + 1) + 10);
            enemyHealth.add(true);
            pane.getChildren().add(enemies.get(i));
            
            final Animation animFBI = new LinkAnimator(
                    enemies.get(i),
                    Duration.millis(700),
                    2, 1,
                    0, 0,
                    80, 80
            );
            animFBI.setCycleCount(Animation.INDEFINITE);
            animFBI.play();
            
            enemyAnimation.add(i, animFBI);
            
        }

    }
    public boolean isBoundary(Node a){
    	if (currentroom ==1 || currentroom == 2 ||currentroom ==3){
    		Node[] walls = new Node[3];
    		//top line
    		walls[0] = makeLine(0,140,1250, 140);
    		//triangle of trees
    		walls[1] =makeLine(0,140,1250, 140);
    		//cant go backwards(first room)
    		walls[2] = makeLine(10,0,10,657 );
    		
    		for(int i =0; i<walls.length;i++){
    			if (checkCollision(walls[i], a )){
    				return true;
    			}
    		}
    	}
    	if (currentroom == 8){
    		Node[] walls = new Node[1];
    		walls[0] = makeLine(1100,0,1100,657);
    		if (checkCollision(walls[0],a)){
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    
    public Line makeLine(int x1, int y1, int x2, int y2){
    	@SuppressWarnings("deprecation")
		Line line = LineBuilder.create()
                .startX(x1)
                .startY(y1)
                .endX(x2)
                .endY(y2)
                .fill(Color.RED)
                .strokeWidth(10.0f)
                .build();
    	return line;
    }

    // main method
    public static void main(String[] args) {
        launch(args);
    } // main
}
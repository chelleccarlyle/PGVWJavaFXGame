package Link1pkg;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class LinkAnimator extends Transition { //spriteAnimation extends part of the Animation class making new objects "Animations"

    private ImageView imageView;
    private final int count;
    private final int columns;
    private int offsetX;
    private int offsetY;
    private final int width;
    private final int height;

    private int lastIndex;

    public LinkAnimator(
            ImageView imageView, 
            Duration duration, 
            int count,   int columns,
            int offsetX, int offsetY,
            int width,   int height) {
    	//instantiates all variables and objects passed in to constructor
        this.imageView = imageView; 
        this.count     = count;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = width;
        this.height    = height;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }
    
    public void setOffset(ImageView I, int x, int y){
    	this.imageView = I;
    	this.offsetX = x;
    	this.offsetY = y;
    }

    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {//if indexed frame is different than previous animation frame
            final int x = (index % columns) * width  + offsetX;//indicates xval of new frame
            final int y = (index / columns) * height + offsetY;//indicates yval of new frame
            imageView.setViewport(new Rectangle2D(x, y, width, height)); //sets the view of the animation to a 2D rectangle and x,y location
            lastIndex = index;
        }
    }
    
} // SpriteAnimation

/* REFERENCES:
 * 
 * SpriteAnimation.java borrowed from the following site:
 * Creating a Sprite Animation with JavaFX | Mike's Blog
 * http://blog.netopyr.com/2012/03/09/creating-a-sprite-animation-with-javafx/
 */
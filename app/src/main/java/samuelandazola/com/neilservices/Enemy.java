package samuelandazola.com.neilservices;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.Random;

/** The type Enemy. */
public class Enemy extends GameObject {

  private int score;
  private int speed;
  private Random random = new Random();
  private Animation animation = new Animation();
  private Bitmap spritesheet;


  /**
   * Instantiates a new Enemy.
   *
   * @param res the res
   * @param x the x position
   * @param y the y position
   * @param w the width
   * @param h the height
   * @param s the score
   * @param numFrames the num frames
   *
   * Creates the chance of an enemy being faster as the game continues and the score increases.
   * Loops through the enemy bitmap
   * Sends the enemy array to the {@link Animation} class.
   */
  Enemy(Bitmap res, int x, int y, int w, int h, int s, int numFrames){
    super.x = x;
    super.y = y;
    width = w;
    height = h;
    score = s;

    //the enemy has a higher chance of being faster as the game goes on and the score increases (the base plus a random number)
    speed = 7 + (int) (random.nextDouble()*score/30);

    //cap the enemy speed
    if(speed>70)speed = 70;

    Bitmap[] image = new Bitmap[numFrames];

    spritesheet = res;

//loops through enemy image
    for(int i = 0; i<image.length; i++){
      image[i] = Bitmap.createBitmap(spritesheet, 0, i*height, width, height);
    }
//sends the array to the animation class
    animation.setFrames(image);
    animation.setDelay(100-speed);
  }

  /** Update Initiates speed of enemy along the x axis. */
  public void update(){
  x-=speed;
  animation.update();
  }

  /**
   * Draw.
   * Draws the Enemy to the Canvas
   * @param canvas the canvas
   */
  void draw(Canvas canvas){

    try{
      canvas.drawBitmap(animation.getImage(), x, y, null);
    }catch (Exception e){}
  }
  @Override
  public int getWidth(){

    //offset slightly for more realistic collision detection
    return width-10;
  }

  @Override
  public int getHeight(){

    return height -10;
  }
}

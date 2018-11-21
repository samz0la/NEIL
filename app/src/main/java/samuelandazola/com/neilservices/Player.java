package samuelandazola.com.neilservices;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * The type Player extends {@link GameObject} it initializes the score, playing,
 * and startTime fields.
 */
public class Player extends GameObject{
  private Bitmap spritesheet;
  private int score;
  private boolean up;
  private boolean playing;
  private Animation animation = new Animation();
  private long startTime; //initiates the score

  /**
   * Instantiates a new Player.
   *
   * @param res the res
   * @param w the w
   * @param h the h
   * @param numFrames the num frames
   */
  Player(Bitmap res, int w, int h, int numFrames){
    x = 100;
    y = GamePanel.HEIGHT/2;
    dy = 0; //acceleration on the y axis
    score = 0;
    height = h;
    width = w;

    Bitmap[] image = new Bitmap[numFrames];
    spritesheet = res;


    for(int i = 0; i<image.length; i++) {
      image[i] = Bitmap.createBitmap(spritesheet, i * width, 0, width, height);
    }
    animation.setFrames(image);
    animation.setDelay(10);
    startTime = System.nanoTime();
  }

  /**
   * Set up.
   * called by the motion event, when you press down the character will go up
   * @param b the b
   */
  public void setUp(boolean b){up=b;}

  /**
   * Update.
   * Increases the score every 1/10 of a second.
   * Accelerates player along the y axis
   * Caps the speed of the player
   */
  public void update(){
    long elapsed = (System.nanoTime()-startTime)/1000000;
    if(elapsed>100){ //every 1/10 of a second the score increase by one
      score++;
      startTime = System.nanoTime();
    }
    animation.update();

    if(up){ //acceleration of the player along the y axis
      dy -=1.1;
    }else{
      dy +=1.1;
    }

    if(dy>14) dy=14; //caps the speed of player
    if(dy<-14)dy= -14;

    y += dy*2;
  }

  /**
   * Draw.
   * draws character to <code>Surface View</code>
   * @param canvas the canvas
   */
  void draw(Canvas canvas){
    canvas.drawBitmap(animation.getImage(),x,y,null);
  }

  /** Get score int.
   * @return the int
   */
  public int getScore(){return score;}

  /** Get playing boolean.
   * @return the boolean
   */
  boolean getPlaying(){return playing;}

  /** Set playing.
   * @param b the b
   */
  void setPlaying(boolean b){playing = b;}

  /**
   * Reset dy.
   * Resets Player position
   */
  void  resetDY(){dy = 0;}

  /**
   * Reset score.
   * Resets score
   */
  void resetScore(){score = 0;}

}

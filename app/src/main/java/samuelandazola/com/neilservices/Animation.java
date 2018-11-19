package samuelandazola.com.neilservices;

import android.graphics.Bitmap;

/**
 * The type Animation.
 * This sets up the animations that are shown by {@link GamePanel}
 */
public class Animation {
  private Bitmap[] frames;
  private int currentFrame;
  private long startTime;
  private long delay;
  private boolean playedOnce;

  /**
   * Set frames.
   *
   * @param frames initiates the frame and sets current frame.
   */
  void setFrames(Bitmap[] frames){
    this.frames = frames;
    currentFrame = 0;
    startTime = System.nanoTime();
  }

  /**
   * Set the delay of every animation
   * @param d the d
   */
  void setDelay(long d){delay = d;}

  /**
   * Set frame.
   * @param i
   */
  public void setFrame(int i){currentFrame = i;}

  /**
   * Update.
   * This starts the frame count and updates the frame every time.
   */
  public void update(){
    long elapsed = (System.nanoTime()-startTime)/1000000;

    if (elapsed>delay){
      currentFrame++;
      startTime = System.nanoTime();
    }
    if(currentFrame == frames.length){
      currentFrame = 0;
      playedOnce = true;
    }
  }

  /**
   * Get image bitmap.
   * Determines what the player class will draw
   * @return the bitmap
   */
  public Bitmap getImage(){
    return frames[currentFrame];
  }

  /**
   * Get frames gets the current frame.
   *
   * @return the current frame
   */
  public int getFrames(){return currentFrame;}

  /**
   * Played once boolean.
   * Returns if the Animation has gone through one iteration
   * @return the boolean
   */
  boolean playedOnce(){return playedOnce;}
}

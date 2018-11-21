package samuelandazola.com.neilservices;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * The type Explosion.
 */
public class Explosion {

  private int x;
  private int y;
  private int width;
  private int height;
  private int row;
  private Animation animation = new Animation();
  private Bitmap spritesheet;

  /**
   * Instantiates a new Explosion.
   *
   * @param res the res
   * @param x the x position
   * @param y the y position
   * @param w the width
   * @param h the height
   * @param numFrames the num frames
   */
  Explosion(Bitmap res, int x, int y, int w, int h, int numFrames){

    this.x = x;
    this.y = y;
    this.width = w;
    this.height = h;

    Bitmap[] image = new Bitmap[numFrames];

    spritesheet = res;

    for (int i = 0; i <image.length; i++) {
      if (i%5  == 0 && i > 0)row++;
      image[i] = Bitmap.createBitmap(spritesheet, (i- (5*row)) *width, row*height, width, height);
    }
    animation.setFrames(image);
    animation.setDelay(10);
  }

  /**
   * Draw.
   * draws the explosion to the canvas
   * @param canvas the canvas
   */
  void draw(Canvas canvas){

    if (!animation.playedOnce()){
      canvas.drawBitmap(animation.getImage(), x, y, null);
    }
  }

  /**
   * Update.
   * Controls the iteration of the explosion to only one time if it hasn't been initiated
   */
  public void update(){

    if (!animation.playedOnce()){
      animation.update(); //only want the explosion to go once and we will only update if it hasn't completed its first round
    }
  }

  /**
   * Get height.
   * @return the int height
   */
  public int getHeight(){return height;}
}

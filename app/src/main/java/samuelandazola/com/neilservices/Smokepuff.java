package samuelandazola.com.neilservices;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * The type Smokepuff.
 */
public class Smokepuff extends GameObject {
  private int radius;

  /**
   * Instantiates a new Smokepuff.
   * sets the x & y in the super class game object to the x & y being
   * passed into the constructor
   * @param x the x
   * @param y the y
   */
  Smokepuff(int x, int y){
    radius = 5;
    super.x = x;
    super.y = y;
  }

  /**
   * Update.
   */
  public void update(){
    x-=10;
  }

  /**
   * Draw.
   * draws smoke puff to <code>Surface View</code>
   * @param canvas the canvas
   */
  void draw(Canvas canvas){
    Paint paint = new Paint();
    paint.setColor(Color.GRAY);
    paint.setStyle(Paint.Style.FILL);

    canvas.drawCircle(x-radius, y-radius, radius, paint);
    canvas.drawCircle(x-radius+2, y-radius+2, radius, paint);
    canvas.drawCircle(x-radius+4, y-radius+1, radius, paint);
  }
}

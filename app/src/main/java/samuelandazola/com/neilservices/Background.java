package samuelandazola.com.neilservices;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {

  private Bitmap image;
  public int x, y, dx;

  //Background has Bitmap passed in and equals image
  public Background(Bitmap res) {
    image = res;
    dx = GamePanel.MOVESPEED;
  }

  public void update() {
    //image will slowly move off screen
    x += dx; //dx is the vector
    //after image is off the screen we reset it
    if (x <-GamePanel.WIDTH) {
      x = 0;
    }
  }

  public void draw(Canvas canvas) {
    canvas.drawBitmap(image, x, y, null);
    //draws second image if part of the first image is off screen
    if (x < 0) {
      canvas.drawBitmap(image, x + GamePanel.WIDTH, y, null);
    }
  }
}


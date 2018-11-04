package samuelandazola.com.neilservices;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Smokepuff extends GameObject {
  public int radius;
  public Smokepuff(int x, int y){
    //sets the x&y in the super class game object to the x&y being passed into the constructor
    radius = 5;
    super.x = x;
    super.y = y;
  }
  public void update(){
    x-=10;
  }
  public void draw(Canvas canvas){
    Paint paint = new Paint();
    paint.setColor(Color.GRAY);
    paint.setStyle(Paint.Style.FILL);

    canvas.drawCircle(x-radius, y-radius, radius, paint);
    canvas.drawCircle(x-radius+2, y-radius+2, radius, paint);
    canvas.drawCircle(x-radius+4, y-radius+1, radius, paint);
  }
}

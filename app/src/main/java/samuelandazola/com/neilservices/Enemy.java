package samuelandazola.com.neilservices;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.Random;

public class Enemy extends GameObject {

  private int score;
  private int speed;
  private Random random = new Random();
  private Animation animation = new Animation();
  private Bitmap spritesheet;


  public Enemy(Bitmap res, int x, int y, int w, int h, int s, int numFrames){
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
  public void update(){
  x-=speed;
  animation.update();
  }
  public void draw(Canvas canvas){

    try{
      canvas.drawBitmap(animation.getImage(), x, y, null);
    }catch (Exception e){}
  }
  @Override
  public int getWidth(){

    //offset slightly for more realistic collision detection
    return width-10;
  }
}

package samuelandazola.com.neilservices;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

  public static final int WIDTH = 1921;
  public static final int HEIGHT = 1081;
  public static final int MOVESPEED = -5;
  private long smokeStartTimer;
  private MainThread thread;
  private Background bg;
  private Player player;
  private ArrayList<Smokepuff> smoke;

//automatically called when the object is called
  public GamePanel(Context context) {
    super(context);
//add the callback to the surfaceholder to intercept events
    getHolder().addCallback(this);

    thread = new MainThread(getHolder(), this);
//make gamepanel focusable so it can handle events
    setFocusable(true);
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    boolean retry = true;
    int counter = 0;
    while(retry && counter<1000)
    {
      counter++;
      try{thread.setRunning(false);
        thread.join();
        retry = false;

      }catch(InterruptedException e) {e.printStackTrace();}

    }
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.background2));
    player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.neil_character2), 171, 151, 1);
    smoke = new ArrayList<>();

    smokeStartTimer = System.nanoTime();

    //we can safely start the game loop
    thread.setRunning(true);
    thread.start();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {

    if(event.getAction() == MotionEvent.ACTION_DOWN){
      if (!player.getPlaying()){
        player.setPlaying(true);
      }else{
        player.setUp(true);
      }
      return true;
    }
    if (event.getAction() == MotionEvent.ACTION_UP){
      player.setUp(false);
      return true;
    }
    return super.onTouchEvent(event);
  }

  public void update() {
    if (player.getPlaying()) {
      bg.update();
      player.update();
//time elapsed from now and when smokeStartTime started
      long elapsed = (System.nanoTime() - smokeStartTimer)/1000000;
      if(elapsed > 120){
        smoke.add(new Smokepuff(player.getX()+50, player.getY()+150));
        smokeStartTimer = System.nanoTime();
      }
//going to itterated through every smokepuff object in the array list smoke
      for (int i = 0; i<smoke.size(); i++){
        smoke.get(i).update(); //going to update every smoke object
        if(smoke.get(i).getX()<-10){
          smoke.remove(i);
        }
      }
    }
  }
  @Override
  public void draw(Canvas canvas) {
    //TODO fix screen scroll
    //super.draw(canvas);
    //scales to entire phone scren
    final float scaleFactorX = getWidth() / (WIDTH * 1.f);
    final float scaleFactorY = getHeight() / (HEIGHT * 1.f);
    if (canvas != null) {
      final int savedState = canvas.save();
      canvas.scale(scaleFactorX, scaleFactorY);
      bg.draw(canvas);
      player.draw(canvas);
      for(Smokepuff sp: smoke){ //sp equals every value in the smoke array and when it itterates through each one it will draw the smoke
        sp.draw(canvas);
      }

      canvas.restoreToCount(savedState);
    }
  }
}

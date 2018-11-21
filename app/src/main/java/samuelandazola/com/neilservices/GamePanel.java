package samuelandazola.com.neilservices;

import static samuelandazola.com.neilservices.MainThread.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.Random;
import samuelandazola.com.neilservices.controller.MainActivity;
import samuelandazola.com.neilservices.model.db.GameDatabase;
import samuelandazola.com.neilservices.model.entity.GameEntity;


/**
 * The type Game panel creates the game. It extends {@link SurfaceView} and
 * implements {@link SurfaceHolder}.
 * It overrides {@link SurfaceHolder} in surface destroyed and surface created methods.
 *
 * It also overrides {@link MotionEvent} for onTouchEvent that enables the game Panel to
 * listens to touch events on the screen/surfaceview.
 *
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {


  static final int WIDTH = 1921;
  static final int HEIGHT = 1081;
  static final int MOVESPEED = -5;
  private long smokeStartTimer;
  private long enemyStartTime;
  private MainThread thread;
  private Background bg;
  private Player player;
  private ArrayList<Smokepuff> smoke;
  private ArrayList<Enemy> enemy;
  private Random random = new Random();
  private boolean newGameCreated;
  private Explosion explosion;
  private long startReset;
  private boolean reset;
  private boolean disappear;
  private boolean started;
  private int best;
  private long playerId;


  /**
   * Instantiates a new Game panel.
   * Game Panel is called whe the object is called
   * adds a callback to the surfaceholder to intercept events.
   * makes gamepanel focusable so it can handle events
   *
   *
   * @param context the context
   * @param playerId the registers a game played to the player
   */
//automatically called when the object is called
  GamePanel(Context context, long playerId) {

    super(context);
    this.playerId = playerId;

//add the callback to the surfaceholder to intercept events
    getHolder().addCallback(this);

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
      try{thread.setRunning(false); //tries to stop the thread, then goes to the catch. if it doesn't catch it will run thread again
        thread.join();
        retry = false;
        thread = null;

      }catch(InterruptedException e) {e.printStackTrace();}

    }
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    //passing the image into the constructor
    bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.background2));
    player = new Player(BitmapFactory.decodeResource(getResources(),
        R.drawable.neil_character2), 171, 151, 1);
    enemy = new ArrayList<>();
    smoke = new ArrayList<>();
    smokeStartTimer = System.nanoTime();
    enemyStartTime = System.nanoTime();

    thread = new MainThread(getHolder(), this);

    //starts the game loop
    thread.setRunning(true);
    thread.start();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
//listens to touch events on the screen/surfaceview
    if(event.getAction() == MotionEvent.ACTION_DOWN){
      if (!player.getPlaying() && newGameCreated && reset){ //if its the first time pressing down then the game starts.
        player.setPlaying(true);
        player.setUp(true);
      }
      if (player.getPlaying()){

        if (!started)started = true; //if its not the first time pressing the game doesn't reset
        reset = false;
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

  /**
   * Update.
   *
   * Updates the {@link Player} and {@link Background} only when player is playing.
   *
   * Sets {@link Enemy} on timer, creates random enemy position, loops through enemy
   * and checks for collision and removes.
   *
   * Sets {@link Smokepuff} and itterates through every smokepuff object in the arraylist.
   */
  public void update() {
    //updates player and background only with the player is playing
    if (player.getPlaying()) {

      bg.update();
      player.update();

      //check top/bottom collision
      if (player.y < 0 || player.y > HEIGHT - 100){
        player.setPlaying(false);
      }

      //add enemy on timer
      long enemyElapsed = (System.nanoTime()-enemyStartTime)/1000000;
      if (enemyElapsed > (4000 - player.getScore()/4)){

        //first enemy always goes down the middle
        if (enemy.size()==0){

          enemy.add(new Enemy(BitmapFactory.decodeResource(getResources(),
              R.drawable.enemy1), WIDTH + 10, HEIGHT/2, 281, 161, player.getScore(), 1));

        }else{

          //random enemy position
          enemy.add(new Enemy(BitmapFactory.decodeResource(getResources(), R.drawable.enemy1),
              WIDTH+10, (int) (random.nextDouble() * (HEIGHT)), 281, 161, player.getScore(), 1));
          enemy.add(new Enemy(BitmapFactory.decodeResource(getResources(), R.drawable.enemy2),
              WIDTH+10, (int) (random.nextDouble() * (HEIGHT)), 281, 161, player.getScore(), 1));
          enemy.add(new Enemy(BitmapFactory.decodeResource(getResources(), R.drawable.enemy3),
              WIDTH+10, (int) (random.nextDouble() * (HEIGHT)), 201, 121, player.getScore(), 1));
        }

        //resets the timer
        enemyStartTime = System.nanoTime();
      }
      //loops through every enemy and check collision and remove
      for (int i = 0; i<enemy.size();i++){

        //update enemy
        enemy.get(i).update();

        if (collision(enemy.get(i),player)){
          enemy.remove(i);
          player.setPlaying(false);

          break;
        }
        //removes enemy if it is way off the screen
        if (enemy.get(i).getX()<-100){
          enemy.remove(i);
          break;
        }
      }

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
    }else{
      player.resetDY();
      if (!reset){
        newGameCreated = false;
        startReset = System.nanoTime();
        reset = true;
        disappear = true;
        explosion = new Explosion(BitmapFactory.decodeResource(getResources(),
            R.drawable.explosion), player.getX()+20,
            player.getY(), 100,100,25);
      }


      explosion.update();
      long resetElapsed = (System.nanoTime() -startReset)/1000000;

      if (resetElapsed > 2500 && !newGameCreated){ //how long for player to reset
        newGame();
      }

    }
  }

  private boolean collision(GameObject a, GameObject b){
    if (Rect.intersects(a.getRectangle(), b.getRectangle())){
      return true;
    }
    return false;
  }

  @Override
  public void draw(Canvas canvas) {
    //super.draw(canvas); (if i call the super it splits the scrolling thread)

    //scales background to entire phone screen
    final float scaleFactorX = getWidth() / (WIDTH * 1.f);
    final float scaleFactorY = getHeight() / (HEIGHT * 1.f);


    if (canvas != null) {
      final int savedState = canvas.save();
      canvas.scale(scaleFactorX, scaleFactorY);
      bg.draw(canvas);

      if (!disappear) {
        player.draw(canvas);
      }
      //draws smoke
      for(Smokepuff sp: smoke){ //sp equals every value in the smoke array and when it itterates through each one it will draw the smoke
        sp.draw(canvas);
      }
      //draws enemy
      for(Enemy e: enemy){
        e.draw(canvas);
      }

      //draw explosion
      if (started){
        explosion.draw(canvas);
      }

      drawText(canvas);

      canvas.restoreToCount(savedState);
    }

  }

  private void newGame(){

    disappear = false;

    enemy.clear();
    smoke.clear();

    if(player.getScore()>best){
      best = player.getScore();
    }

    player.resetDY();
    player.setY(HEIGHT/2);

    new AddTask().execute(player.getScore());

    player.resetScore();

    newGameCreated = true;

  }

  private class AddTask extends AsyncTask<Integer, Void, Long>{

    @Override
    protected Long doInBackground(Integer...score) {
      GameEntity game = new GameEntity();
      game.setScore(score[0]);
      game.setPlayerId(playerId);
      return GameDatabase.getInstance(getContext()).getGameDao().insert(game);
    }
  }

  private void drawText(Canvas canvas){
    //shows current and best distance
    Paint paint = new Paint();
    paint.setColor(Color.WHITE);
    paint.setTextSize(70);
    paint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/space_font.ttf"));
    canvas.drawText("DISTANCE: " + (player.getScore()), 10, HEIGHT - 10, paint);
    canvas.drawText("BEST: " + best, WIDTH - 500, HEIGHT - 10, paint);

    if (!player.getPlaying() && newGameCreated && reset){
      Paint paint1 = new Paint();
      paint1.setColor(Color.WHITE);
      paint1.setTextSize(100);
      paint1.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/space_font.ttf"));
      canvas.drawText("PRESS TO START", WIDTH/2 - 400, HEIGHT/2, paint1);

      paint1.setTextSize(80);
      canvas.drawText("PRESS AND HOLD TO GO UP", WIDTH/2-600, HEIGHT/2 + 100, paint1);
      canvas.drawText("RELEASE TO GO DOWN", WIDTH/2-400, HEIGHT/2 + 180, paint1);

    }
  }

}

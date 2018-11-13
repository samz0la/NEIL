package samuelandazola.com.neilservices;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

//This is the main game loop
public class MainThread extends Thread {

  private int FPS = 30;
  private double averageFPS;
  private SurfaceHolder surfaceHolder;
  private GamePanel gamePanel;
  private boolean running;
  public static Canvas canvas;

  public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
    super();
    this.surfaceHolder = surfaceHolder;
    this.gamePanel = gamePanel;
  }

  @Override //everytime you run through the game loop we want it to run 1000milliseconds/FPS
  public void run() {
    long startTime;
    long timeMillis;
    long waitTime;
    long totalTime = 0;
    int frameCount = 0;
    long targetTime = 1000 / FPS; //how many millis you want the game loop to run

    while (running) {
      startTime = System.nanoTime();
      canvas = null;
//locking the canvas for pixel editing
      try {
        canvas = this.surfaceHolder.lockCanvas();
        synchronized (surfaceHolder) {
          this.gamePanel.update(); //updates the game once
          this.gamePanel.draw(canvas); //draws the game once
        }
      } catch (Exception e) {
      }
      finally {
        if(canvas != null) {
          try {surfaceHolder.unlockCanvasAndPost(canvas);
          }
          catch (Exception e) {e.printStackTrace();}
        }
      }

      //creating the time in milliseconds
      timeMillis = (System.nanoTime() - startTime) / 1000000;
      //how long we wait to go through the loop again
      waitTime = targetTime - timeMillis;

      try {
        this.sleep(waitTime);
      } catch (Exception e) {}
      totalTime += System.nanoTime() - startTime;
      frameCount++;
      if (frameCount == FPS) {
        averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
        frameCount = 0;
        totalTime = 0;
        System.out.println(averageFPS);
      }
    }
  }

  public void setRunning(boolean b) {
    running = b;
  }

}

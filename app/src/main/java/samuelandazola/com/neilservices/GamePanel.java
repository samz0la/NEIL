package samuelandazola.com.neilservices;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

  private MainThread thread;

  public GamePanel(Context context) {
    super(context);

    getHolder().addCallback(this);

    thread = new MainThread(getHolder(), this);

    setFocusable(true);
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    boolean retry = true;
    while(retry)
    {
      try{
        thread.setRunning(false);
        thread.join();
      }catch(InterruptedException e) {
        e.printStackTrace();
        retry = false;
      }
    }
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    thread.setRunning(true);
    thread.start();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    return super.onTouchEvent(event);
  }
  public void update() {

  }
}

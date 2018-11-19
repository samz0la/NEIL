package samuelandazola.com.neilservices;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * The type Game activity implements the title screen and sets entire game to full screen.
 * It extends {@link Activity} to build the screens of this application and it also
 * has all the lifecycle callbacks expected by the Android Framework.
 *
 */
public class GameActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //turn title off
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    //set to full screen
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(new GamePanel(this, getIntent().getLongExtra(getString(R.string.playerIdKey), 0)));

  }
}

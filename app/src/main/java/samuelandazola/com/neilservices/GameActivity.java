package samuelandazola.com.neilservices;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

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

package samuelandazola.com.neilservices.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;
import samuelandazola.com.neilservices.Game;
import samuelandazola.com.neilservices.R;
import samuelandazola.com.neilservices.model.db.GameDatabase;


public class MainActivity extends Activity {


  private GameDatabase database;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);


    CircleMenu circleMenu = (CircleMenu)findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.mipmap.takeoff_launcher, R.mipmap.exit_launcher)
        .addSubMenu(Color.parseColor("#CDCDCD"), R.mipmap.facebook)
            .addSubMenu(Color.parseColor("#CDCDCD"), R.mipmap.takeoff_launcher)
            .addSubMenu(Color.parseColor("#CDCDCD"), R.mipmap.google)

        .setOnMenuSelectedListener(new OnMenuSelectedListener() {

          @Override
          public void onMenuSelected(int index) {
            switch (index){
              case 0:
                break;
              case 1:
                openGame();
                break;
              case 2:
                break;
            }
          }

        }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

      @Override
      public void onMenuOpened() {}

      @Override
      public void onMenuClosed() {}

    });
  }

  public void openGame(){
    Intent intent = new Intent(this, Game.class);
    startActivity(intent);
  }

  @Override
  protected void onStart() {
    super.onStart();
    initDB();
  }

  private void initDB() {
    database = GameDatabase.getInstance(this);
  }

  @Override
  protected void onStop() {
    database = null;
    GameDatabase.forgetInstance();
    super.onStop();
  }

  @Override
  protected void onPause() {
    super.onPause();
  }

  @Override
  protected void onResume() {
    super.onResume();
  }
}

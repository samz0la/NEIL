package samuelandazola.com.neilservices.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;
import java.util.LinkedList;
import java.util.List;
import samuelandazola.com.neilservices.Game;
import samuelandazola.com.neilservices.R;
import samuelandazola.com.neilservices.model.db.GameDatabase;
import samuelandazola.com.neilservices.model.entity.Player;


public class MainActivity extends Activity {


  private Player player;
  private GameDatabase database;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);

    player = new Player();


    CircleMenu circleMenu = (CircleMenu)findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#00FFFFFF"), R.mipmap.takeoff_launcher, R.mipmap.exit_launcher)
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



  private class AddTask extends AsyncTask<Player, Void, Long>{
    @Override
    protected Long doInBackground(Player...players){
      database.getPlayerDao().insert(player);
      return player.getId();
    }

    @Override
    protected void onPostExecute(Long playerId){
      //TODO save player id in field variable to reference later
    }
  }

  private class QueryTask extends AsyncTask<Long, Void, Player>{
    @Override
    protected Player doInBackground(Long...playerIds){
      List<Player> players = new LinkedList<>();
      players = database.getPlayerDao().select(playerIds[0]);
      return players.get(0);
    }

    @Override
    protected void onPostExecute(Player player){
      //TODO save player in field variable to reference later
    }
  }

  private class UpdateTask extends AsyncTask<Player, Void, Void>{
    @Override
    protected Void doInBackground(Player...players){
       database.getPlayerDao().update(players[0]);
      return null;
    }
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

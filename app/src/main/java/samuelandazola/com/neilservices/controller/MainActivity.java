package samuelandazola.com.neilservices.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.gms.common.SignInButton;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;
import java.util.LinkedList;
import java.util.List;
import samuelandazola.com.neilservices.GameActivity;
import samuelandazola.com.neilservices.R;
import samuelandazola.com.neilservices.model.db.GameDatabase;
import samuelandazola.com.neilservices.model.entity.PlayerEntity;


public class MainActivity extends Activity {


  private TextView bestScore;
  private PlayerEntity player;
  private GameDatabase database;
  private long playerId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_login);

    bestScore = findViewById(R.id.best_score);
    player = new PlayerEntity();
    database = GameDatabase.getInstance(this);

    new QueryTask().execute("example@example.com");

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

  public void openGame(){ //passes playerId to GameActivity
    Intent intent = new Intent(this, GameActivity.class);
    intent.putExtra(getString(R.string.playerIdKey), playerId);
    startActivity(intent);
  }

  private class ScoreTask extends AsyncTask<Long, Void, Integer>{

    @Override
    protected Integer doInBackground(Long... playerIds) {
      return database.getGameDao().maxScore(playerIds[0]);
    }

    @Override
    protected void onPostExecute(Integer score) {

    bestScore.setText("" + score);
    }
  }


  private class AddTask extends AsyncTask<PlayerEntity, Void, Long>{
    @Override
    protected Long doInBackground(PlayerEntity...players){
      database.getPlayerDao().insert(player);
      return player.getId();
    }

    @Override
    protected void onPostExecute(Long playerId){
      //TODO save player id in field variable to reference later
    }
  }

  private class QueryTask extends AsyncTask<String, Void, PlayerEntity>{
    @Override
    protected PlayerEntity doInBackground(String...email){
      List<PlayerEntity> players = new LinkedList<>();
      players = database.getPlayerDao().select(email[0]);
      if (players.size() < 1){
        PlayerEntity player = new PlayerEntity();
        player.setEmail(email[0]);
        long playerId = database.getPlayerDao().insert(player);
        player.setId(playerId); //telling entity what id is
        return player;
      }
      return players.get(0);
    }

    @Override
    protected void onPostExecute(PlayerEntity player){

      playerId = player.getId();
      new ScoreTask().execute(player.getId());
    }
  }

  private class UpdateTask extends AsyncTask<PlayerEntity, Void, Void>{
    @Override
    protected Void doInBackground(PlayerEntity...players){
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

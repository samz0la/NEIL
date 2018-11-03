package samuelandazola.com.neilservices.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import samuelandazola.com.neilservices.R;
import samuelandazola.com.neilservices.model.db.GameDatabase;


public class MainActivity extends AppCompatActivity {

  private GameDatabase database;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);
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

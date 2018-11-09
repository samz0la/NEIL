package samuelandazola.com.neilservices;

import android.app.Application;
import com.facebook.stetho.Stetho;
import samuelandazola.com.neilservices.model.db.GameDatabase;

public class GameApplication extends Application {

  GameDatabase gameDatabase;

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    gameDatabase = GameDatabase.getInstance(this);
    gameDatabase.getPlayerDao();
  }
}

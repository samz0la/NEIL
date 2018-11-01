package samuelandazola.com.neilservices;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class GameApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }
}

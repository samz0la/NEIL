package samuelandazola.com.neilservices;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import samuelandazola.com.neilservices.model.db.GameDatabase;

/**
 * The type Game application extends {@link Application} it applies
 * {@link com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension}
 * and initializes {@link Stetho}
 */
public class GameApplication extends Application {

  GameDatabase gameDatabase;
  private static GameApplication instance = null;
  private GoogleSignInClient client;
  private GoogleSignInAccount account;

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
    Stetho.initializeWithDefaults(this);
    gameDatabase = GameDatabase.getInstance(this);
    gameDatabase.getPlayerDao();

    GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestId()
        .build();
    client = GoogleSignIn.getClient(this, options);

  }

  /** Gets instance.
   * @return the instance
   */
  public static GameApplication getInstance() {
    return instance;
  }

  /** Sets instance.
   * @param instance the instance
   */
  public static void setInstance(GameApplication instance) {
    GameApplication.instance = instance;
  }

  /** Gets client.
   * @return the client
   */
  public GoogleSignInClient getClient() {
    return client;
  }

  /** Sets client.
   * @param client the client
   */
  public void setClient(GoogleSignInClient client) {
    this.client = client;
  }

  /** Gets account.
   * @return the account
   */
  public GoogleSignInAccount getAccount() {
    return account;
  }

  /** Sets account.
   * @param account the account
   */
  public void setAccount(GoogleSignInAccount account) {
    this.account = account;
  }
}

package samuelandazola.com.neilservices.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import java.util.Date;
import samuelandazola.com.neilservices.model.dao.GameDao;
import samuelandazola.com.neilservices.model.dao.PlayerDao;
import samuelandazola.com.neilservices.model.db.GameDatabase.Converters;
import samuelandazola.com.neilservices.model.entity.GameEntity;
import samuelandazola.com.neilservices.model.entity.PlayerEntity;

@Database(entities = {GameEntity.class, PlayerEntity.class},
    version = 1,
    exportSchema = true
)

@TypeConverters(Converters.class)
public abstract class GameDatabase extends RoomDatabase {

  private static final String DB_NAME = "game_db";

  private static GameDatabase instance = null;

  public abstract GameDao getGameDao();


  public abstract PlayerDao getPlayerDao();

  public synchronized static GameDatabase getInstance(Context context) {
    if(instance == null) {
      instance = Room.databaseBuilder(context.getApplicationContext(), GameDatabase.class, DB_NAME)
          .build();
      //TODO Add callback to prepopulate data
    }
    return instance;
  }
  public synchronized static void forgetInstance() {
    instance = null;
  }

  public static class Converters {


    @TypeConverter
    public static Date dateFromLong(Long time) {
      return (time != null) ? new Date(time) : null;
    }

    @TypeConverter
    public static Long longFromDate(Date date) {
      return (date != null) ? date.getTime() : null;
    }
  }
}

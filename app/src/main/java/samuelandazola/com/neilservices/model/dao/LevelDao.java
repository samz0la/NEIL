package samuelandazola.com.neilservices.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
import samuelandazola.com.neilservices.model.entity.Level;

@Dao
public interface LevelDao {
  @Insert(onConflict = OnConflictStrategy.FAIL)
  List<Long> insert(List<Level> levels);

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(Level level);

  @Query("SELECT * FROM Level WHERE level_id = :levelId")
  List<Level> select(int levelId);

  @Query("SELECT * FROM Level WHERE game_id = :gameId")
  List<Level> selectByGame(int gameId);

  @Delete
  int delete (List<Level> levels);

  @Delete
  int delete (Level level);

  @Update
  int update (Level level);
}

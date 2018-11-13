package samuelandazola.com.neilservices.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
import samuelandazola.com.neilservices.model.entity.GameEntity;

@Dao
public interface GameDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(GameEntity game);

  @Query("SELECT * FROM GameEntity WHERE player_id = :playerId")
  List<GameEntity> select(long playerId);

  @Query("SELECT MAX(score) FROM GameEntity WHERE player_id = :playerId")
  int maxScore(long playerId);

  @Delete
  int delete(GameEntity game);

  @Delete
  int delete(List<GameEntity> games);

  @Update
  int update(GameEntity game);
}

package samuelandazola.com.neilservices.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
import samuelandazola.com.neilservices.model.entity.PlayerEntity;
import samuelandazola.com.neilservices.model.pojo.PlayerStats;

@Dao
public interface PlayerDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  List<Long> insert(List<PlayerEntity> players);

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(PlayerEntity player);

  @Query("SELECT * FROM PlayerEntity WHERE player_id = :playerId")
  List<PlayerEntity> select(long playerId);

  @Query("SELECT DISTINCT p.* FROM PlayerEntity p LEFT JOIN GameEntity g ON g.player_id = p.player_id ORDER BY g.score DESC")
  List<PlayerEntity> select();

  @Query("SELECT * FROM PlayerEntity WHERE email = :email")
  List<PlayerEntity> select(String email);

  @Query("SELECT p.player_id, p.email, IFNULL(MAX(g.score), 0) AS highScore, COUNT(g.game_id) AS plays FROM PlayerEntity p LEFT JOIN GameEntity g ON g.player_id = p.player_id WHERE p.player_id = :playerId LIMIT 1")
  PlayerStats selectStats(long playerId);

  @Delete
  int delete (List<PlayerEntity> players);

  @Delete
  int delete (PlayerEntity player);

  @Update
  int update (PlayerEntity player);
}

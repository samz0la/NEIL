package samuelandazola.com.neilservices.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
import samuelandazola.com.neilservices.model.entity.PlayerEntity;

@Dao
public interface PlayerDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  List<Long> insert(List<PlayerEntity> players);

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(PlayerEntity player);

  @Query("SELECT * FROM PlayerEntity WHERE player_id = :playerId")
  List<PlayerEntity> select(long playerId);

  @Query("SELECT * FROM PlayerEntity ORDER BY high_score DESC")
  List<PlayerEntity> select();

  @Query("SELECT * FROM PlayerEntity WHERE email = :email")
  List<PlayerEntity> select(String email);

  @Delete
  int delete (List<PlayerEntity> players);

  @Delete
  int delete (PlayerEntity player);

  @Update
  int update (PlayerEntity player);
}

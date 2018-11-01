package samuelandazola.com.neilservices.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
import samuelandazola.com.neilservices.model.entity.Player;

@Dao
public interface PlayerDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  List<Long> insert(List<Player> players);

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(Player player);

  @Query("SELECT * FROM Player WHERE player_id = :playerId")
  List<Player> select(long playerId);

  @Query("SELECT * FROM Player ORDER BY high_score DESC")
  List<Player> select();

  @Delete
  int delete (List<Player> players);

  @Delete
  int delete (Player player);

  @Update
  int update (Player player);
}

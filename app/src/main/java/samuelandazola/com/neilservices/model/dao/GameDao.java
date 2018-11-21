package samuelandazola.com.neilservices.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
import samuelandazola.com.neilservices.model.entity.GameEntity;

/** The interface Game dao. */
@Dao
public interface GameDao {

  /** Insert long.
   * @param game the game
   * @return the long
   */
  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(GameEntity game);

  /** Select list.
   * @param playerId the player id
   * @return the list
   */
  @Query("SELECT * FROM GameEntity WHERE player_id = :playerId")
  List<GameEntity> select(long playerId);

  /** Max score int.
   * @param playerId the player id
   * @return the int
   */
  @Query("SELECT MAX(score) FROM GameEntity WHERE player_id = :playerId")
  int maxScore(long playerId);


  /** Delete int.
   * @param game the game
   * @return the int
   */
  @Delete
  int delete(GameEntity game);

  /** Delete int.
   * @param games the games
   * @return the int
   */
  @Delete
  int delete(List<GameEntity> games);

  /** Update int.
   * @param game the game
   * @return the int
   */
  @Update
  int update(GameEntity game);
}

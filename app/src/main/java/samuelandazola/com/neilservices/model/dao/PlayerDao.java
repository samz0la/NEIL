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

/** The interface Player dao. */
@Dao
public interface PlayerDao {

  /** Insert list.
   * @param players the players
   * @return the list
   */
  @Insert(onConflict = OnConflictStrategy.FAIL)
  List<Long> insert(List<PlayerEntity> players);

  /** Insert long.
   * @param player the player
   * @return the long
   */
  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(PlayerEntity player);

  /** Select list.
   * @param playerId the player id
   * @return the list
   */
  @Query("SELECT * FROM PlayerEntity WHERE player_id = :playerId")
  List<PlayerEntity> select(long playerId);

  /** Select list.
   * @return the list
   */
  @Query("SELECT DISTINCT p.* FROM PlayerEntity p LEFT JOIN GameEntity g ON g.player_id = p.player_id ORDER BY g.score DESC")
  List<PlayerEntity> select();

  /** Select list.
   * @param email the email
   * @return the list
   */
  @Query("SELECT * FROM PlayerEntity WHERE email = :email")
  List<PlayerEntity> select(String email);

  /**Select stats player stats.
   * @param playerId the player id
   * @return the player stats
   */
  @Query("SELECT p.player_id, p.email, IFNULL(MAX(g.score), 0) AS highScore, COUNT(g.game_id) AS plays FROM PlayerEntity p LEFT JOIN GameEntity g ON g.player_id = p.player_id WHERE p.player_id = :playerId LIMIT 1")
  PlayerStats selectStats(long playerId);

  /** Delete int.
   * @param players the players
   * @return the int
   */
  @Delete
  int delete (List<PlayerEntity> players);

  /** Delete int.
   * @param player the player
   * @return the int
   */
  @Delete
  int delete (PlayerEntity player);

  /** Update int.
   * @param player the player
   * @return the int
   */
  @Update
  int update (PlayerEntity player);
}

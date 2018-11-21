package samuelandazola.com.neilservices.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/** The type Game entity. */
@Entity(
    foreignKeys = @ForeignKey(entity = PlayerEntity.class, parentColumns = "player_id", childColumns = "player_id", onDelete = ForeignKey.CASCADE)
)
public class GameEntity {

  @ColumnInfo(name = "game_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

  @ColumnInfo(name = "score")
  private long score;

  @ColumnInfo(name = "player_id", index = true)
  private long playerId;


  /** Gets id.
   * @return the id
   */
  public long getId() {
    return id;
  }

  /** Sets id.
   * @param id the id
   */
  public void setId(long id) {
    this.id = id;
  }


  /** Gets player id.
   * @return the player id
   */
  public long getPlayerId() {
    return playerId;
  }

  /** Sets player id.
   * @param playerId the player id
   */
  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }

  /** Gets score.
   * @return the score
   */
  public long getScore() {
    return score;
  }

  /** Sets score.
   * @param score the score
   */
  public void setScore(long score) {
    this.score = score;
  }
}


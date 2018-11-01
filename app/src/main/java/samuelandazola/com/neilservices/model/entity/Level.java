package samuelandazola.com.neilservices.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;

@Entity(
    foreignKeys = @ForeignKey(entity = Game.class, parentColumns = "game_id", childColumns = "game_id", onDelete = ForeignKey.CASCADE)
)

public class Level {

  @ColumnInfo(name = "level_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

  @ColumnInfo(name = "game_id", index = true)
  private long gameId;

  @ColumnInfo(name = "score")
  private long score;

  @ColumnInfo(name = "results")
  private long results;

  @ColumnInfo(name = "time_stamp")
  private Date date;

  public long getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public long getResults() {
    return results;
  }

  public void setResults(int results) {
    this.results = results;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public long getGameId() {
    return gameId;
  }

  public void setGameId(int gameId) {
    this.gameId = gameId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}

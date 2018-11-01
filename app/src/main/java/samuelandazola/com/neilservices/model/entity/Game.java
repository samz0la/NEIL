package samuelandazola.com.neilservices.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;

@Entity(
    foreignKeys = @ForeignKey(entity = Player.class, parentColumns = "player_id", childColumns = "player_id", onDelete = ForeignKey.CASCADE)
)
public class Game {

  @ColumnInfo(name = "game_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

  @ColumnInfo(name = "player_id", index = true)
  private long playerId;
  @ColumnInfo(name = "time_stamp")
  private Date date;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}

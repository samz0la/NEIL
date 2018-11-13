package samuelandazola.com.neilservices.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class PlayerEntity {

  @ColumnInfo(name = "player_id")
  @PrimaryKey(autoGenerate = true)
  private long id;
  private String email;

  private long plays;

  @ColumnInfo(name = "high_score")
  private long score;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getPlays() {
    return plays;
  }

  public void setPlays(long plays) {
    this.plays = plays;
  }

  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}

package samuelandazola.com.neilservices.model.pojo;

import android.arch.persistence.room.Embedded;
import samuelandazola.com.neilservices.model.entity.PlayerEntity;

public class PlayerStats {

  @Embedded
  private PlayerEntity player;


  private int plays;
  private int highScore;

  public PlayerEntity getPlayer() {
    return player;
  }

  public void setPlayer(PlayerEntity player) {
    this.player = player;
  }

  public int getPlays() {
    return plays;
  }

  public void setPlays(int plays) {
    this.plays = plays;
  }

  public int getHighScore() {
    return highScore;
  }

  public void setHighScore(int highScore) {
    this.highScore = highScore;
  }
}

package samuelandazola.com.neilservices.model.pojo;

import android.arch.persistence.room.Embedded;
import samuelandazola.com.neilservices.model.entity.PlayerEntity;

/** The type Player stats. */
public class PlayerStats {

  @Embedded
  private PlayerEntity player;


  private int plays;
  private int highScore;

  /** Gets player.
   * @return the player
   */
  public PlayerEntity getPlayer() {
    return player;
  }

  /** Sets player.
   * @param player the player
   */
  public void setPlayer(PlayerEntity player) {
    this.player = player;
  }

  /** Gets plays.
   * @return the plays
   */
  public int getPlays() {
    return plays;
  }

  /** Sets plays.
   * @param plays the plays
   */
  public void setPlays(int plays) {
    this.plays = plays;
  }

  /** Gets high score.
   * @return the high score
   */
  public int getHighScore() {
    return highScore;
  }

  /** Sets high score.
   * @param highScore the high score
   */
  public void setHighScore(int highScore) {
    this.highScore = highScore;
  }
}

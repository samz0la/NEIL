package samuelandazola.com.neilservices.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/** The type Player entity. */
@Entity
public class PlayerEntity {

  @ColumnInfo(name = "player_id")
  @PrimaryKey(autoGenerate = true)
  private long id;
  private String email;


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

  /** Gets email.
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /** Sets email.
   * @param email the email
   */
  public void setEmail(String email) {
    this.email = email;
  }
}

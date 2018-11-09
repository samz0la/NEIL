package samuelandazola.com.neilservices.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
import samuelandazola.com.neilservices.model.entity.Game;

//@Dao
public interface GameDao {

//  @Insert(onConflict = OnConflictStrategy.FAIL)
//  long insert(Game game);
//
//  @Query("SELECT * FROM Game WHERE player_id = :playerId")
//  List<Game> select(long playerId);
//
//  @Delete
//  int delete(Game game);
//
//  @Delete
//  int delete(List<Game> games);
//
//  @Update
//  int update(Game game);
}

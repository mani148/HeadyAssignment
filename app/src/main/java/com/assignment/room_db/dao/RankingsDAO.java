package com.assignment.room_db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.assignment.room_db.entity.Rankings;

import java.util.List;

@Dao
public interface RankingsDAO {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Rankings rankings);

    @Query("DELETE FROM rankings_table")
    void deleteAll();

    @Query("SELECT * from rankings_table Where product_id == :id")
    List<Rankings> getAlphabetizedWords(int id);
}

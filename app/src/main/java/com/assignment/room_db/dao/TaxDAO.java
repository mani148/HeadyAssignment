package com.assignment.room_db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.assignment.room_db.entity.Tax;

import java.util.List;

@Dao
public interface TaxDAO {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Tax tax);

    @Query("DELETE FROM tax_table")
    void deleteAll();

    @Query("SELECT * from tax_table Where product_id == :id")
    List<Tax> getAlphabetizedWords(int id);
}

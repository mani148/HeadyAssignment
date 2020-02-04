package com.assignment.room_db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.assignment.room_db.entity.Categories;

import java.util.List;

@Dao
public interface CategoriesDAO {


    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Categories categories);

    @Query("DELETE FROM categories_table")
    void deleteAll();


    @Query("SELECT * from categories_table ORDER BY name ASC")
    List<Categories> getAlphabetizedWords();
}

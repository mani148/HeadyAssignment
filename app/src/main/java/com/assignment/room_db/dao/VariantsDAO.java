package com.assignment.room_db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.assignment.room_db.entity.Categories;
import com.assignment.room_db.entity.Variants;

import java.util.List;

@Dao
public interface VariantsDAO {


    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Variants variants);

    @Query("DELETE FROM variants_table")
    void deleteAll();

    @Query("SELECT * from variants_table Where product_id == :id")
    List<Variants> getVariantsById(int id);

    @Query("SELECT * from variants_table Where size == :size AND product_id == :prodId")
    List<Variants> getVariantsBySize(int size,int prodId);

    @Query("SELECT * from variants_table Where  product_id == :prodId")
    List<Variants> getVariantsBySize(int prodId);

    @Query("SELECT * from variants_table Where product_id == :prodId AND id == :varId")
    Variants getVariantsPrice(int prodId,int varId);
}



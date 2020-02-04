package com.assignment.room_db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.assignment.room_db.entity.Product;

import java.util.List;

@Dao
public interface ProductDAO {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Product product);

    @Query("DELETE FROM product_table")
    void deleteAll();

    @Query("SELECT * from product_table Where category_id == :id")
    Product getProdcutById(int id);

    @Query("SELECT * from product_table Where id == :id")
    Product getProduct(int id);

    @Query("SELECT * from product_table Where category_id == :cateId")
    List<Product> getAllProdcut(int cateId);
}

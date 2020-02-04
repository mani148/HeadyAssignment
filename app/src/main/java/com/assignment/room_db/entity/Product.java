package com.assignment.room_db.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class Product {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "category_id")
    private int category_id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "date_added")
    private String date_added;


    public Product(int id, int category_id,String name,String date_added){

    this.id= id;
    this.category_id= category_id;
    this.name= name;
    this.date_added=date_added;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate_added() {
        return date_added;
    }

    public int getCategory_id() {
        return category_id;
    }
}

package com.assignment.room_db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories_table")
public class Categories {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    public Categories(int id,String name) {this.id = id;this.name = name;}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}

package com.assignment.room_db.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tax_table")
public class Tax {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "product_id")
    private int product_id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "value")
    private String value;

    public Tax(int product_id,String name,String value){

        this.product_id= product_id;
        this.name=name;
        this.value=value;

    }
    public int getProduct_id() {
        return product_id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}

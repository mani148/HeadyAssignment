package com.assignment.room_db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "variants_table")
public class Variants {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;


    @NonNull
    @ColumnInfo(name = "product_id")
    private int product_id;

    @ColumnInfo(name = "color")
    private String color;

    @ColumnInfo(name = "price")
    private Integer price;

    @ColumnInfo(name = "size")
    private Integer size;



    public Variants(int id,int product_id,String color,Integer price,Integer size){
this.id=id;
        this.product_id= product_id;
        this.color=color;
        this.price=price;
        this.size=size;

    }

    public int getId() {
        return id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getColor() {
        return color;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getSize() {
        return size;
    }
}
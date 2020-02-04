package com.assignment.room_db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "rankings_table")
public class Rankings {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "product_id")
    private int product_id;


    @ColumnInfo(name = "view_count")
    private int view_count;

    @ColumnInfo(name = "ranking")
    private String ranking;


    @ColumnInfo(name = "order_count")
    private int order_count;


    @ColumnInfo(name = "shares")
    private int shares;

   public Rankings(int product_id, int view_count,int order_count,int shares,String ranking){

        this.product_id= product_id;
        this.ranking= ranking;
        this.view_count=view_count;
        this.order_count=order_count;
        this.shares=shares;


    }

    public String getRanking() {
        return ranking;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getView_count() {
        return view_count;
    }

    public int getOrder_count() {
        return order_count;
    }

    public int getShares() {
        return shares;
    }
}

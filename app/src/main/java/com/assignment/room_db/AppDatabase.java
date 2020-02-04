package com.assignment.room_db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.assignment.room_db.dao.CategoriesDAO;
import com.assignment.room_db.dao.ProductDAO;
import com.assignment.room_db.dao.RankingsDAO;
import com.assignment.room_db.dao.TaxDAO;
import com.assignment.room_db.dao.VariantsDAO;
import com.assignment.room_db.entity.Categories;
import com.assignment.room_db.entity.Product;
import com.assignment.room_db.entity.Rankings;
import com.assignment.room_db.entity.Tax;
import com.assignment.room_db.entity.Variants;
import com.assignment.utils.Constants;

@Database(entities = {Categories.class, Product.class, Rankings.class, Tax.class, Variants.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CategoriesDAO categoriesDao();
    public abstract ProductDAO productDao();
    public abstract RankingsDAO rankingsDAO();
    public abstract TaxDAO taxDao();
    public abstract VariantsDAO variantsDao();

    private static AppDatabase appDB;

    public static AppDatabase getInstance(Context context) {
        if (null == appDB) {
            appDB = buildDatabaseInstance(context);
        }
        return appDB;
    }

    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                Constants.DB_NAME)
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        appDB = null;
    }

}
package com.assignment.room_db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Categories.class, Product.class, Rankings.class, Tax.class, Variants.class}, version = 1, exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {

    public abstract CategoriesDAO categoriesDao();
    public abstract ProductDAO productDao();
    public abstract RankingsDAO rankingsDAO();
    public abstract TaxDAO taxDao();
    public abstract VariantsDAO variantsDao();

    private static volatile AppRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            /*databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
               // CategoriesDAO dao = INSTANCE.categoriesDao();
               // dao.deleteAll();

                *//*Categories categories = new Categories();
                dao.insert(word);*//*

            });*/
        }
    };
}
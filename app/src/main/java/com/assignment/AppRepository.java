package com.assignment;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.assignment.room_db.AppRoomDatabase;
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

import java.util.List;

public class AppRepository {

    private CategoriesDAO categoriesDAO;
    private ProductDAO productDAO;
    private RankingsDAO rankingsDAO;
    private TaxDAO taxDAO;
    private VariantsDAO variantsDAO;
    private List<Categories> mAllCategories;
    private List<Product> mAllProdcut;
    private Product mProduct;
    private List<Rankings> mAllRank;
    private List<Tax> mAllTax;
    private List<Variants> mAllVariants;
    private List<Variants> mAllSizeVariants;
    private Variants mVariant;


    private int cat_id,prod_id,size,varId;
    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    AppRepository(Application application) {
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        categoriesDAO = db.categoriesDao();
        productDAO = db.productDao();
        rankingsDAO = db.rankingsDAO();
        taxDAO = db.taxDao();
        variantsDAO = db.variantsDao();

        mAllCategories = categoriesDAO.getAlphabetizedWords();
        mAllProdcut = productDAO.getAllProdcut(cat_id);
        mProduct = productDAO.getProdcutById(cat_id);
        mProduct = productDAO.getProduct(prod_id);
        mAllRank = rankingsDAO.getAlphabetizedWords(prod_id);
        mAllVariants = variantsDAO.getVariantsById(prod_id);
        mAllSizeVariants = variantsDAO.getVariantsBySize(size,prod_id);
        mAllSizeVariants = variantsDAO.getVariantsBySize(prod_id);
        mVariant = variantsDAO.getVariantsPrice(prod_id,varId);
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    List<Categories> getmAllCategories() {
        return mAllCategories;
    }

    List<Product> getAllProducts() {
        return mAllProdcut;
    }

    Product getmAllProducts(int cat_id) {
        return mProduct;
    }

    Product getmProduct(int cat_id) {
        return mProduct;
    }

    List<Rankings> getmAllRanking(int prod_id) {
        return mAllRank;
    }

    List<Variants> getmAllVariants(int prod_id) {
        return mAllVariants;
    }

    List<Variants> getmAllSizeVariants(int size,int prod_id) {
        return mAllSizeVariants;
    }

    List<Variants> getmAllSizeVariants(int prod_id) {
        return mAllSizeVariants;
    }

    Variants getVariantsPrice(int prod_id,int varId) {
        return mVariant;
    }




    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Categories categories) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            categoriesDAO.insert(categories);
        });
    }
}

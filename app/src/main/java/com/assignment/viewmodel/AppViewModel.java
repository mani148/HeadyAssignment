package com.assignment.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.assignment.room_db.entity.Categories;

import java.util.List;

public class AppViewModel extends AndroidViewModel {

    private AppViewModel mRepository;

    private LiveData<List<Categories>> mAlCategories;

    public AppViewModel (Application application) {
        super(application);
        mRepository = new AppViewModel(application);
        mAlCategories = mRepository.getmAllCategories();
    }

    LiveData<List<Categories>> getmAllCategories() { return mAlCategories; }

    public void insert(Categories word) { mRepository.insert(word); }
}

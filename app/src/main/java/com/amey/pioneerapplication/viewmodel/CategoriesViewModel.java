package com.amey.pioneerapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.amey.pioneerapplication.model.CategoriesModelItem;
import com.amey.pioneerapplication.repository.RemoteDataSource;
import com.amey.pioneerapplication.utils.AppConstants;

import java.util.List;

public class CategoriesViewModel extends ViewModel {


    RemoteDataSource remoteDataSource;
    LiveData<List<CategoriesModelItem>> categoriesModelItems;
    // TODO: Implement the ViewModel


    public void init(){
        if(categoriesModelItems != null)
            return;


        remoteDataSource = RemoteDataSource.Companion.newInstance();
        categoriesModelItems = remoteDataSource.getCategories(AppConstants.CUSTOMER_ID, AppConstants.SECRET_TOKEN);
    }

    public LiveData<List<CategoriesModelItem>> getCategories(){
        return categoriesModelItems;
    }
}
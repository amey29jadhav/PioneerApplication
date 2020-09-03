package com.amey.pioneerapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.amey.pioneerapplication.model.CategoriesModelItem;
import com.amey.pioneerapplication.model.ProductsModelItem;
import com.amey.pioneerapplication.repository.RemoteDataSource;
import com.amey.pioneerapplication.utils.AppConstants;

import java.util.List;

public class ProductsViewModel extends ViewModel {

    RemoteDataSource remoteDataSource;
    LiveData<List<ProductsModelItem>> productsModelItems;
    LiveData<List<CategoriesModelItem>> categoriesModelItems;

    public void init(String categoryId) {
        if(productsModelItems != null)
            return;

        if(categoriesModelItems != null)
            return;


        remoteDataSource = RemoteDataSource.Companion.newInstance();
        productsModelItems = remoteDataSource.getProducts(AppConstants.CUSTOMER_ID, AppConstants.SECRET_TOKEN,"67fd06f7-0d6f-44a6-8a4c-0c53097ad406");
        categoriesModelItems = remoteDataSource.getCategories(AppConstants.CUSTOMER_ID, AppConstants.SECRET_TOKEN);

    }

    public LiveData<List<ProductsModelItem>> getProducts(){
        return productsModelItems;
    }

    public LiveData<List<CategoriesModelItem>> getCategories(){
        return categoriesModelItems;
    }

}
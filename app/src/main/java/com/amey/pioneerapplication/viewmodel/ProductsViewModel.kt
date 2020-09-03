package com.amey.pioneerapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.amey.pioneerapplication.model.CategoriesModelItem
import com.amey.pioneerapplication.model.ProductsModelItem
import com.amey.pioneerapplication.repository.RemoteDataSource
import com.amey.pioneerapplication.repository.RemoteDataSource.Companion.newInstance
import com.amey.pioneerapplication.utils.AppConstants

class ProductsViewModel : ViewModel() {
    var remoteDataSource: RemoteDataSource? = null
    lateinit var products: LiveData<List<ProductsModelItem?>>
    fun init(categoryId: String?) {
        remoteDataSource = newInstance()
        products = remoteDataSource!!.getProducts(
            "67fd06f7-0d6f-44a6-8a4c-0c53097ad406"
        )

    }

}
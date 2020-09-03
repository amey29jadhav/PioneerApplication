package com.amey.pioneerapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.amey.pioneerapplication.model.CategoriesModelItem
import com.amey.pioneerapplication.repository.RemoteDataSource
import com.amey.pioneerapplication.repository.RemoteDataSource.Companion.newInstance
import com.amey.pioneerapplication.utils.AppConstants

class CategoriesViewModel : ViewModel() {
    var remoteDataSource: RemoteDataSource? = null
    var categories: LiveData<List<CategoriesModelItem?>>? = null

    // TODO: Implement the ViewModel
    suspend fun init() {
        if (categories != null) return
        remoteDataSource = newInstance()
        assert(remoteDataSource != null)
        categories =
            remoteDataSource!!.getCategories()
    }

}
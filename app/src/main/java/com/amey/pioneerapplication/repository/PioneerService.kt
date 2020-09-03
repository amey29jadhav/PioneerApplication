package com.amey.pioneerapplication.repository

import com.amey.pioneerapplication.model.CategoriesModelItem
import com.amey.pioneerapplication.model.ProductsModelItem
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.*

interface PioneerService {

    @GET("GetCategories/")
    fun getCategories(@Query("secretToken") secretToken: String?, @Query("customerId") customerId: String?): Call<List<CategoriesModelItem?>>

    @GET("GetProducts/")
    fun getProduct(@Query("secretToken") secretToken: String?, @Query("customerId") customerId: String?, @Query("categoryId") categoryId: String?): Call<List<ProductsModelItem?>>
}
package com.amey.pioneerapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amey.pioneerapplication.model.CategoriesModelItem
import com.amey.pioneerapplication.model.ProductsModelItem
import com.amey.pioneerapplication.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

  class RemoteDataSource {

    companion object {
        private var remoteDataSource: RemoteDataSource? = null
        fun newInstance(): RemoteDataSource? {
            if (remoteDataSource == null) {
                remoteDataSource = RemoteDataSource()
            }
            return remoteDataSource
        }
    }

    fun getCategories(customerId: String?, secretToken: String?): LiveData<List<CategoriesModelItem?>>{


        val categroiesLiveData: MutableLiveData<List<CategoriesModelItem?>> by lazy {
            MutableLiveData<List<CategoriesModelItem?>>()
        }


        val pioneerService = RetrofitClientInstance.buildService(PioneerService:: class.java);
        val categories = pioneerService.getCategories( AppConstants.SECRET_TOKEN, AppConstants.CUSTOMER_ID)
        categories?.enqueue(object : Callback<List<CategoriesModelItem?>> {

            override fun onResponse(
                call: Call<List<CategoriesModelItem?>>,
                response: Response<List<CategoriesModelItem?>>
            ) {
                    categroiesLiveData.value = response.body()
            }

            override fun onFailure(call: Call<List<CategoriesModelItem?>>, t: Throwable) {
                println(t)
            }
        })

        return categroiesLiveData

    }
      fun getProducts(customerId: String?, secretToken: String?, categoryId: String?): LiveData<List<ProductsModelItem?>>{


          val productsLiveData: MutableLiveData<List<ProductsModelItem?>> by lazy {
              MutableLiveData<List<ProductsModelItem?>>()
          }


          val pioneerService = RetrofitClientInstance.buildService(PioneerService:: class.java);
          val products = pioneerService.getProduct( AppConstants.SECRET_TOKEN, AppConstants.CUSTOMER_ID,categoryId)
          products?.enqueue(object : Callback<List<ProductsModelItem?>> {

              override fun onResponse(
                  call: Call<List<ProductsModelItem?>>,
                  response: Response<List<ProductsModelItem?>>
              ) {
                  productsLiveData.value = response.body()
              }

              override fun onFailure(call: Call<List<ProductsModelItem?>>, t: Throwable) {
                  println(t)
              }
          })

          return productsLiveData

      }




}



package com.amey.pioneerapplication.utils

import com.amey.pioneerapplication.model.CategoriesModelItem

class AppConstants {

    companion object{
        const val API_HOST_NAME: String = "http://tabup.lightspeedsolutions.com/Api/"
        const val PATH_NAME: String = "Api/"
        const val GET_CATEGORIES = "GetCategories"
        const val SECRET_TOKEN = "appmobile"
        const val CUSTOMER_ID = "b7c13a1e-b99e-4069-8213-02ba9833110e"

        const val CATEGORY_UPDATE_BROADCAST ="com.amey.pioneerapplication.categoryupdate";

        var categroylist = listOf<CategoriesModelItem>()




    }

}
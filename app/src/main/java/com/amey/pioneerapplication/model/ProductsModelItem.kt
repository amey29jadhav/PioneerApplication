package com.amey.pioneerapplication.model

import java.util.*
import kotlin.Comparator

data class ProductsModelItem(val id: String,val name: String, val productSortOrder: Int, val shortInfo: String?,val subcategory: String, val subcategoryId: String,    val subcategorySortOrder: Int,    val unitPrice: Double) : Comparator<ProductsModelItem> {
    override fun compare(p0: ProductsModelItem?, p1: ProductsModelItem?): Int {
       return p0!!.subcategory.compareTo(p1!!.subcategory)
    }


}
package com.amey.pioneerapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amey.pioneerapplication.Fragments.ProductsFragment
import com.amey.pioneerapplication.R
import com.amey.pioneerapplication.model.ProductsModelItem

class ProductsAdapter(
    private val list: List<ProductsModelItem>,
    productsFragment: ProductsFragment
): RecyclerView.Adapter<ProductsAdapter.ProductsHolder>() {
    class ProductsHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(
        R.layout.products_row, parent, false)) {

        private var productName: TextView? = null
        private var productDescription: TextView? = null
        private var productPrice: TextView? = null


        init {
            productName = itemView.findViewById(R.id.product_name)
            productDescription = itemView.findViewById(R.id.product_description)
            productPrice = itemView.findViewById(R.id.product_price)

        }

        fun bind(products: ProductsModelItem) {
            productName?.text = products.name
            productDescription?.text = products.shortInfo

            if (products.shortInfo != null) {
                productDescription?.visibility = View.VISIBLE
            }else{
                productDescription?.visibility = View.GONE
            }
            productPrice?.text = "1.0"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHolder {
        return ProductsAdapter.ProductsHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        holder.bind(list[position])
    }
}
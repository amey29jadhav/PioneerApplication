package com.amey.pioneerapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amey.pioneerapplication.fragments.ProductsFragment
import com.amey.pioneerapplication.R
import com.amey.pioneerapplication.model.ProductsModelItem
import com.amey.pioneerapplication.utils.TypeFaceHelper

class ProductsAdapter(
    private val list: List<ProductsModelItem>,
    productsFragment: ProductsFragment
): RecyclerView.Adapter<ProductsAdapter.ProductsHolder>() {
    class ProductsHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(
        R.layout.products_row, parent, false)) {

        private var productName: TextView? = null
        private var productDescription: TextView? = null
        private var productPrice: TextView? = null

        //fontAwesomeFont = Typeface.createFromAsset(context.getAssets(), "FontAwesome.otf");
        private var robotoMedium = TypeFaceHelper.getInstance(parent.context)?.getStyleTypeFace(TypeFaceHelper.MEDIUM)





        init {
            productName = itemView.findViewById(R.id.product_name)
            productName?.typeface = robotoMedium
            productDescription = itemView.findViewById(R.id.product_description)
            productDescription?. typeface = robotoMedium
            productPrice = itemView.findViewById(R.id.product_price)
            productPrice?.typeface = robotoMedium

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
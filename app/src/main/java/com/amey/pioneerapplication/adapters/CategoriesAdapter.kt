package com.amey.pioneerapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amey.pioneerapplication.fragments.ClickCallback
import com.amey.pioneerapplication.R
import com.amey.pioneerapplication.model.CategoriesModelItem

class CategoriesAdapter(private val list: List<CategoriesModelItem>, val clickCallback: ClickCallback): RecyclerView.Adapter<CategoriesAdapter.CategoryHolder>() {
    class CategoryHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(
        R.layout.category_row, parent, false)) {

        private var categoryname: TextView? = null


        init {
            categoryname = itemView.findViewById(R.id.category_name)

        }

        fun bind(category: CategoriesModelItem,clickCallback: ClickCallback) {
            categoryname?.text = category.name
            itemView.setOnClickListener(View.OnClickListener {
                    clickCallback.onItemClick(category.id)
            })

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val category: CategoriesModelItem = list[position]
        holder.bind(category,clickCallback)
    }

}





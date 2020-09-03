package com.amey.pioneerapplication.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.amey.pioneerapplication.adapters.CategoriesAdapter
import com.amey.pioneerapplication.databinding.CategoryBottomSheetBinding
import com.amey.pioneerapplication.utils.AppConstants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CategoryBottomSheetDialogFragment : BottomSheetDialogFragment(), ClickCallback {
    var categoryBottomSheetBinding: CategoryBottomSheetBinding? =
        null
    companion object {

        const val TAG = "CategoryBottomSheetDialogFragment"

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        categoryBottomSheetBinding = CategoryBottomSheetBinding.inflate(inflater,container,false)
        val view= categoryBottomSheetBinding!!.root

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        categoryBottomSheetBinding!!.categoryrecyclerView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(context)
        categoryBottomSheetBinding!!.categoryrecyclerView.addItemDecoration(
            DividerItemDecoration(context,
                DividerItemDecoration.HORIZONTAL)
        )

        categoryBottomSheetBinding!!.categoryrecyclerView.layoutManager = linearLayoutManager
        categoryBottomSheetBinding!!.categoryrecyclerView.adapter = CategoriesAdapter(AppConstants.categroylist,this)

    }

    override fun onItemClick(id: String?) {

        this.dialog?.dismiss()
        val intent =  Intent()
        intent.action = AppConstants.CATEGORY_UPDATE_BROADCAST
        intent.putExtra("categoryId", id )
        activity?.sendBroadcast(intent)
    }
}

interface ClickCallback{
    fun onItemClick(id: String?)
}
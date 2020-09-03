package com.amey.pioneerapplication.Fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.amey.pioneerapplication.R
import com.amey.pioneerapplication.adapters.ProductsAdapter
import com.amey.pioneerapplication.databinding.ProductsFragmentBinding
import com.amey.pioneerapplication.model.CategoriesModelItem
import com.amey.pioneerapplication.model.ProductsModelItem
import com.amey.pioneerapplication.utils.AppConstants
import com.amey.pioneerapplication.viewmodel.ProductsViewModel
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.products_fragment.*

class ProductsFragment : Fragment()  {
    private var mViewModel: ProductsViewModel? = null
    var horizontalScrollView: HorizontalScrollView? = null
    lateinit var linearLayoutManager: LinearLayoutManager
    val chipList = mutableListOf<String>()
    var chipmap = mutableMapOf<String,Int>()

    private var productsFragmentBinding: ProductsFragmentBinding? =
        null
    var mycontext: Context? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mycontext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productsFragmentBinding =
            ProductsFragmentBinding.inflate(
                inflater,
                container,
                false
            )
        val view = productsFragmentBinding!!.root
        linearLayoutManager = LinearLayoutManager(mycontext)
        productsFragmentBinding!!.productsRecyclerView.addItemDecoration(
            DividerItemDecoration(mycontext,
                DividerItemDecoration.HORIZONTAL)
        )

        productsFragmentBinding!!.productsRecyclerView.layoutManager = linearLayoutManager
        productsFragmentBinding!!.producttoolbar.myToolbar.setOnClickListener(View.OnClickListener {
            it.findNavController().navigate(R.id.action_productsFragment_to_categoryBottomSheetDialogFragment)
        })

        return view
    }

    private fun registerReceiver(){
        val filter = IntentFilter()
        filter.addAction(AppConstants.CATEGORY_UPDATE_BROADCAST)
        val receiver: MyReceiver = MyReceiver(this)
        mycontext?.registerReceiver(receiver,filter)
    }

    private fun initHorizontalScrollView() {
        for (i in chipmap.keys.indices) {
            val chip = Chip(chipGroup.context)
            chip.text = chipmap.keys.elementAt(i)
            chip.isClickable = true
            chip.isCheckable = true
            productsFragmentBinding!!.chipGroup.addView(chip)

        }

        productsFragmentBinding!!.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            // Responds to child chip checked/unchecked
           val text  = (group.getChildAt(checkedId.dec())!! as Chip).text
            productsFragmentBinding?.productsRecyclerView?.smoothScrollToPosition(chipmap[text]!!)

        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel =
            ViewModelProviders.of(this).get(ProductsViewModel::class.java)
            getProducts("")

        mViewModel!!.categories.observe(viewLifecycleOwner,Observer<List<CategoriesModelItem>>{categories->
            AppConstants.categroylist =categories
        })
        // TODO: Use the ViewModel
    }

    public fun getProducts( id: String?){
        mViewModel!!.init(id)
        mViewModel!!.products.observe(viewLifecycleOwner, Observer<List<ProductsModelItem>>{ products ->
            for (x in products.indices){
                if(!chipList.contains(products[x].subcategory)) {
                    chipmap[products[x].subcategory] = x
                    chipList.add(products[x].subcategory)
                }
            }
            initHorizontalScrollView()
            productsFragmentBinding!!.productsRecyclerView.adapter = ProductsAdapter(products, this)
        })
    }

    companion object {
        fun newInstance(): ProductsFragment {
            return ProductsFragment()
        }
    }

    class MyReceiver(val productsFragment: ProductsFragment): BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            if(p1 != null){
                productsFragment.getProducts(p1.getStringExtra("categoryId"))
            }

        }

    }




}


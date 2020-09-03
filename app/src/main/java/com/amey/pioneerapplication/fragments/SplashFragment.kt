package com.amey.pioneerapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.amey.pioneerapplication.R
import com.amey.pioneerapplication.model.CategoriesModelItem
import com.amey.pioneerapplication.repository.PioneerService
import com.amey.pioneerapplication.repository.RemoteDataSource
import com.amey.pioneerapplication.repository.RetrofitClientInstance
import com.amey.pioneerapplication.utils.AppConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getCategories()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


    private fun getCategories() {
        val pioneerService = RetrofitClientInstance.buildService(PioneerService:: class.java);
        val categories = pioneerService.getCategories( AppConstants.SECRET_TOKEN, AppConstants.CUSTOMER_ID)
        categories?.enqueue(object : Callback<List<CategoriesModelItem?>> {

            override fun onResponse(
                call: Call<List<CategoriesModelItem?>>,
                response: Response<List<CategoriesModelItem?>>
            ) {
                if (response != null) {
                    AppConstants.categroylist = response.body() as List<CategoriesModelItem>
                }
                findNavController()
                    .navigate(R.id.action_splashFragment_to_productsFragment,
                        null,
                        NavOptions.Builder()
                            .setPopUpTo(R.id.splashFragment,
                                true).build()
                    )
            }

            override fun onFailure(call: Call<List<CategoriesModelItem?>>, t: Throwable) {
                println(t)
            }
        })


    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SplashFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): SplashFragment {
            val fragment = SplashFragment()
            return fragment
        }
    }
}
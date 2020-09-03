package com.amey.pioneerapplication.Fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amey.pioneerapplication.R;
import com.amey.pioneerapplication.viewmodel.ProductInfoViewModel;

public class ProductInfoFragment extends Fragment {

    private ProductInfoViewModel mViewModel;

    public static ProductInfoFragment newInstance() {
        return new ProductInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.product_info_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProductInfoViewModel.class);
        // TODO: Use the ViewModel
    }

}
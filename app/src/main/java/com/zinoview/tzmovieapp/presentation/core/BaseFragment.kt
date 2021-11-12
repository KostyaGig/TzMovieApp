package com.zinoview.tzmovieapp.presentation.core

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.zinoview.tzmovieapp.core.MovieApp

abstract class BaseFragment(@LayoutRes layoutResId: Int) : Fragment(layoutResId) {

    protected val moviesViewModel by lazy {
        ((requireActivity() as MainActivity).application as MovieApp).moviesViewModel(this)
    }

}
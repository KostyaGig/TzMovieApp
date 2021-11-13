package com.zinoview.tzmovieapp.presentation.state

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.zinoview.tzrecipesapp.presentation.core.Observe

interface StoreObserve<T> : Observe<List<MovieUiState>> {

    fun observeProgress(owner: LifecycleOwner,observe: Observer<T>)
}
package com.zinoview.tzmovieapp.presentation.state

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.zinoview.tzrecipesapp.presentation.core.Observe

interface MovieUiStateCommunication : Observe<List<MovieUiState>> {

    fun postValue(value: List<MovieUiState>)

    class Base : MovieUiStateCommunication {

        private val uiRecipeStateLiveData = MutableLiveData<List<MovieUiState>>()

        override fun postValue(value: List<MovieUiState>) {
            uiRecipeStateLiveData.value = value
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<MovieUiState>>) {
            uiRecipeStateLiveData.observe(owner, observer)
        }


    }
}
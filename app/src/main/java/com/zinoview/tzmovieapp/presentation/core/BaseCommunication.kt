package com.zinoview.tzmovieapp.presentation.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.zinoview.tzrecipesapp.presentation.core.Observe

interface Communication<T : Any> : Observe<T> {

    fun postValue(value: T)

    abstract class BaseCommunication<T : Any> : Communication<T> {

        private val liveData = MutableLiveData<T>()

        override fun observe(owner: LifecycleOwner, observer: Observer<T>)
                = liveData.observe(owner, observer)

        override fun postValue(value: T) {
            liveData.value = value
        }
    }
}

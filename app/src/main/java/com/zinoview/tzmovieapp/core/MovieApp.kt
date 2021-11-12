package com.zinoview.tzmovieapp.core

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.zinoview.tzmovieapp.data.cloud.MovieService
import com.zinoview.tzmovieapp.presentation.MoviesViewModel
import com.zinoview.tzmovieapp.service_locator.CoreModule
import com.zinoview.tzmovieapp.service_locator.movie.MoviesViewModelFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApp : Application() {

    private val coreModule = CoreModule()

    private val viewModelFactory  by lazy{
        MoviesViewModelFactory(
            coreModule
        )
    }

    override fun onCreate() {
        super.onCreate()

        coreModule.init(this)

    }

    fun moviesViewModel(owner: ViewModelStoreOwner) : MoviesViewModel {
        return ViewModelProvider(owner,viewModelFactory).get(MoviesViewModel.Base::class.java)
    }
}
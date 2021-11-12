package com.zinoview.tzmovieapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zinoview.tzmovieapp.domain.MovieInteractor
import com.zinoview.tzmovieapp.presentation.state.MovieUiState
import com.zinoview.tzmovieapp.presentation.state.MovieUiStateCommunication
import com.zinoview.tzmovieapp.presentation.state.MoviesUiStateMapper
import com.zinoview.tzrecipesapp.presentation.core.Observe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface MoviesViewModel : Observe<List<MovieUiState>> {

    fun movies()

    class Base(
        private val interactor: MovieInteractor,
        private val moviesUiMapper: MoviesUiMapper,
        private val moviesUiStateMapper: MoviesUiStateMapper,
        private val movieCommunication: MovieUiStateCommunication,
        private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
    ) : MoviesViewModel, ViewModel() {

        override fun movies() {
            movieCommunication.postValue(listOf(MovieUiState.Progress))

            viewModelScope.launch(defaultDispatcher) {
                val domainMovies = interactor.movies()
                val uiMovies = domainMovies.map(moviesUiMapper)
                val stateMovies = uiMovies.map(moviesUiStateMapper)

                withContext(Dispatchers.Main) {
                    movieCommunication.postValue(stateMovies)
                }
            }
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<MovieUiState>>)
            = movieCommunication.observe(owner,observer)

    }
}
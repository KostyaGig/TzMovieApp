package com.zinoview.tzmovieapp.presentation.state

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import kotlinx.coroutines.*

interface MoviesUiStateStore : StoreObserve<Int> {

    fun addAll(items: List<MovieUiState>)

    suspend fun nextPactItems(counterNextItems: Int)

    fun isEmpty() : Boolean

    fun isLoading(): Boolean

    class Base(
        private val nextPackMoviesCommunication: NextPackMoviesCommunication,
        private val footerProgressBarCommunication: FooterProgressBarCommunication
    ) : MoviesUiStateStore {

        private val moviesUiState = ArrayList<MovieUiState>()
        private val alreadyLoadedItems = mutableListOf<Int>()
        private var isLoading = false

        override fun addAll(items: List<MovieUiState>) {
            val baseMovies = items.filterIsInstance<MovieUiState.Base>()
            moviesUiState.addAll(baseMovies)
        }

        override suspend fun nextPactItems(counterNextItems: Int) {
             if (counterNextItems == MAX_COUNTER_ITEMS) {
                 withContext(Dispatchers.Main) {
                     footerProgressBarCommunication.postValue(View.GONE)
                 }
                 isLoading = false
                 nextPackMoviesCommunication.postValue(emptyList())
            } else {
                if (alreadyLoadedItems.contains(counterNextItems)) {
                    nextPackMoviesCommunication.postValue(emptyList())
                } else {
                    withContext(Dispatchers.Main) {
                        footerProgressBarCommunication.postValue(View.VISIBLE)
                    }
                    isLoading = true
                    CoroutineScope(Dispatchers.IO).launch {
                        delay(PROGRESS_DELAY.toLong())
                        val movies = moviesUiState.take(PACT_COUNT_MOVIES)
                        moviesUiState.removeAll(movies)
                        alreadyLoadedItems.add(counterNextItems)
                        isLoading = false
                        withContext(Dispatchers.Main) {
                            nextPackMoviesCommunication.postValue(movies)
                            footerProgressBarCommunication.postValue(View.GONE)
                        }
                    }
                }
            }

        }

        override fun isEmpty(): Boolean
            = moviesUiState.isEmpty()

        override fun isLoading(): Boolean
            = isLoading == true

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
            footerProgressBarCommunication.observe(owner,observer)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<MovieUiState>>) {
            nextPackMoviesCommunication.observe(owner, observer)
        }

        private companion object {
            private const val PACT_COUNT_MOVIES = 5
            private const val MAX_COUNTER_ITEMS = 25
            private const val PROGRESS_DELAY = 3000
        }
    }
}
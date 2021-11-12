package com.zinoview.tzmovieapp.presentation.state

import com.zinoview.tzmovieapp.presentation.core.log

interface MoviesUiStateStore {

    fun addAll(items: List<MovieUiState>)

    fun nextPactItems() : List<MovieUiState>

    fun isEmpty() : Boolean

    fun isLoading(): Boolean

    class Base : MoviesUiStateStore {

        private var moviesUiState = ArrayList<MovieUiState>()
        private var isLoadingNextItems = false

        override fun addAll(items: List<MovieUiState>) {
            val baseMovies = items.filter { item -> item is MovieUiState.Base}
            moviesUiState.addAll(baseMovies)
            log("add adll size ${moviesUiState.size}")
        }

        override fun nextPactItems(): List<MovieUiState> {
            return if (moviesUiState.isEmpty()) {
                isLoadingNextItems = false
                emptyList()
            } else {
                isLoadingNextItems = true
                val movies = moviesUiState.take(PACT_COUNT_MOVIES)
                log("NEXT FIVE SIZE ${movies.size}")
                moviesUiState.removeAll(movies)
                log("After remove give element ${moviesUiState.size}")
                isLoadingNextItems = false
                return movies
            }
        }

        override fun isEmpty(): Boolean
            = moviesUiState.isEmpty()

        override fun isLoading(): Boolean
            = isLoadingNextItems == true

        private companion object {
            private const val PACT_COUNT_MOVIES = 5
        }
    }
}
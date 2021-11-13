package com.zinoview.tzmovieapp.presentation.state

import android.widget.ImageView
import android.widget.TextView
import com.zinoview.tzmovieapp.core.BaseImage
import com.zinoview.tzmovieapp.presentation.adapter.MoviesAdapter

sealed class MovieUiState {


    open fun map(
        movieImageView: ImageView,
        movieTitleTextView: TextView,
        movieDescriptionTextView: TextView
    ) = Unit

    open fun map(
        movieErrorTextView: TextView
    ) = Unit

    open suspend fun nextPactMovies(moviesStore: MoviesUiStateStore) = Unit

    open fun handleState(adapter: MoviesAdapter) = Unit

    class Base(
        private val title: String,
        private val description: String,
        private val image: BaseImage
    ) : MovieUiState() {

        override fun map(
            movieImageView: ImageView,
            movieTitleTextView: TextView,
            movieDescriptionTextView: TextView
        ) {

            image.loadTo(movieImageView)
            movieTitleTextView.text = title
            movieDescriptionTextView.text = description
        }

        override suspend fun nextPactMovies(moviesStore: MoviesUiStateStore)
            = moviesStore.nextPactItems(NEXT_MOVIES_COUNT)

        private companion object {
            private const val NEXT_MOVIES_COUNT = 5
        }
    }

    class Failure(
        private val message: String
    ) : MovieUiState() {

        override fun map(movieErrorTextView: TextView) {
            movieErrorTextView.text = message
        }

        override fun handleState(adapter: MoviesAdapter)
            = adapter.update(listOf(this))
    }
}
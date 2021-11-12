package com.zinoview.tzmovieapp.presentation.state

import android.widget.ImageView
import android.widget.TextView
import com.zinoview.tzmovieapp.core.BaseImage
import com.zinoview.tzmovieapp.presentation.core.log

sealed class MovieUiState {

    open fun show() = Unit

    open fun map(
        movieImageView: ImageView,
        movieTitleTextView: TextView,
        movieDescriptionTextView: TextView
    ) = Unit

    open fun map(
        movieErrorTextView: TextView
    ) = Unit

    object Progress : MovieUiState() {

        override fun show() {
            log("Progress state...")
        }
    }

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

    }

    class Failure(
        private val message: String
    ) : MovieUiState() {

        override fun map(movieErrorTextView: TextView) {
            movieErrorTextView.text = message
        }
    }
}
package com.zinoview.tzmovieapp.presentation.state

import com.zinoview.tzmovieapp.core.Abstract
import com.zinoview.tzmovieapp.core.BaseMovie

interface MoviesUiStateMapper : Abstract.MoviesMapper<List<MovieUiState>> {

    class Base(
        private val movieUiStateMapper: MovieUiStateMapper
    ) : MoviesUiStateMapper {

        override fun map(movies: List<BaseMovie>): List<MovieUiState>
            = movies.map { baseMovie -> baseMovie.map(movieUiStateMapper) }

        override fun map(message: String): List<MovieUiState>
            = listOf(MovieUiState.Failure(message))

    }
}
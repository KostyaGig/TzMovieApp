package com.zinoview.tzmovieapp.core

import com.zinoview.tzmovieapp.presentation.state.MovieUiState
import com.zinoview.tzmovieapp.presentation.state.MovieUiStateMapper

interface BaseMovie {

    fun map(mapper: MovieUiStateMapper) : MovieUiState

    data class Base(
        private val title: String,
        private val description: String,
        private val image: BaseImage
    ) : BaseMovie {

        override fun map(mapper: MovieUiStateMapper) : MovieUiState
            = mapper.map(title, description, image)
    }
}
package com.zinoview.tzmovieapp.presentation.state

import com.zinoview.tzmovieapp.core.BaseImage

interface MovieUiStateMapper {

    fun map(
        title: String,
        description: String,
        image: BaseImage
    ) : MovieUiState

    class Base : MovieUiStateMapper {

        override fun map(title: String, description: String, image: BaseImage): MovieUiState
            = MovieUiState.Base(
                title, description, image
            )
    }
}
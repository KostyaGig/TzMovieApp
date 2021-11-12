package com.zinoview.tzmovieapp.presentation

import com.zinoview.tzmovieapp.core.Abstract
import com.zinoview.tzmovieapp.core.BaseMovie

interface MoviesUiMapper : Abstract.MoviesMapper<MoviesUi> {

    class Base : MoviesUiMapper {

        override fun map(movies: List<BaseMovie>): MoviesUi
            = MoviesUi.Success(movies)

        override fun map(message: String): MoviesUi
            = MoviesUi.Failure(message)

    }
}
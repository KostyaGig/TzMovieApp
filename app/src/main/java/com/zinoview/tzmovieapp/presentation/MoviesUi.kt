package com.zinoview.tzmovieapp.presentation

import com.zinoview.tzmovieapp.core.Abstract
import com.zinoview.tzmovieapp.core.BaseMovie

sealed class MoviesUi : Abstract.Movies  {

    class Success(
        private val movies: List<BaseMovie>
    ) : MoviesUi() {

        override fun <T> map(mapper: Abstract.MoviesMapper<T>): T
            = mapper.map(movies)
    }

    class Failure(
        private val message: String
    ) : MoviesUi() {

        override fun <T> map(mapper: Abstract.MoviesMapper<T>): T
            = mapper.map(message)
    }
}

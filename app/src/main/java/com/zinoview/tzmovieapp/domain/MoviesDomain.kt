package com.zinoview.tzmovieapp.domain

import com.zinoview.tzmovieapp.core.Abstract
import com.zinoview.tzmovieapp.core.BaseMovie

sealed class MoviesDomain : Abstract.Movies  {

    class Success(
        private val movies: List<BaseMovie>
    ) : MoviesDomain() {

        override fun <T> map(mapper: Abstract.MoviesMapper<T>): T
            = mapper.map(movies)
    }

    class Failure(
        private val message: String
    ) : MoviesDomain() {

        override fun <T> map(mapper: Abstract.MoviesMapper<T>): T
            = mapper.map(message)
    }
}

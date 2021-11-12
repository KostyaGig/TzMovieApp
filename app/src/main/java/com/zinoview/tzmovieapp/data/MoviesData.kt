package com.zinoview.tzmovieapp.data

import com.zinoview.tzmovieapp.core.Abstract
import com.zinoview.tzmovieapp.core.BaseMovie

sealed class MoviesData : Abstract.Movies  {

    class Success(
        private val movies: List<BaseMovie>
    ) : MoviesData() {

        override fun <T> map(mapper: Abstract.MoviesMapper<T>): T
            = mapper.map(movies)
    }

    data class Failure(
        private val message: String
    ) : MoviesData() {

        override fun <T> map(mapper: Abstract.MoviesMapper<T>): T
            = mapper.map(message)
    }

    data class Test(
        private val movies: List<BaseMovie>
    ) : MoviesData() {
        override fun <T> map(mapper: Abstract.MoviesMapper<T>): T
            = mapper.map("")
    }
}

package com.zinoview.tzmovieapp.domain

import com.zinoview.tzmovieapp.core.Abstract
import com.zinoview.tzmovieapp.core.BaseMovie

interface MoviesDomainMapper : Abstract.MoviesMapper<MoviesDomain> {

    class Base : MoviesDomainMapper {

        override fun map(movies: List<BaseMovie>): MoviesDomain
            = MoviesDomain.Success(movies)

        override fun map(message: String): MoviesDomain
            = MoviesDomain.Failure(message)

    }
}
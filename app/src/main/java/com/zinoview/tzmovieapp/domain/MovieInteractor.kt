package com.zinoview.tzmovieapp.domain

import com.zinoview.tzmovieapp.data.MovieRepository

interface MovieInteractor {

    suspend fun movies() : MoviesDomain

    class Base(
        private val repository: MovieRepository,
        private val moviesDomainMapper: MoviesDomainMapper
    ) : MovieInteractor {

        override suspend fun movies(): MoviesDomain {
            val dataMovies = repository.movies()
            return dataMovies.map(moviesDomainMapper)
        }

    }
}
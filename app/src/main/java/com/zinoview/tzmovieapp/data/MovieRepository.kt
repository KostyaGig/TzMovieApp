package com.zinoview.tzmovieapp.data

import com.zinoview.tzmovieapp.data.cloud.BaseMovieMapper
import com.zinoview.tzmovieapp.data.cloud.CloudDataSource
import java.lang.Exception

interface MovieRepository {

    suspend fun movies() : MoviesData

    class Base(
        private val cloudDataSource: CloudDataSource,
        private val baseMovieMapper: BaseMovieMapper,
        private val exceptionMapper: ExceptionMapper
    ) : MovieRepository {

        override suspend fun movies(): MoviesData {
            return try {
                val cloudMovies = cloudDataSource.movies()
                val baseImages = cloudMovies.map { cloudMovie -> cloudMovie.map(baseMovieMapper) }
                MoviesData.Success(baseImages)
            } catch (e: Exception) {
                val errorMessage = exceptionMapper.map(e)
                MoviesData.Failure(errorMessage)
            }
        }

    }

    class Test(
        private val cloudDataSource: CloudDataSource,
        private val baseMovieMapper: BaseMovieMapper
    ) : MovieRepository {

        private var count = 0

        override suspend fun movies(): MoviesData {
            val movies = cloudDataSource.movies()
            val result = if (count % 2 == 0) {
                MoviesData.Failure("No connection")
            } else {
                val baseMovies = movies.map { cloudMovie -> cloudMovie.map(baseMovieMapper)  }
                MoviesData.Test(baseMovies)
            }
            count++
            return result
        }

    }
}
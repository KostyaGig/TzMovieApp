package com.zinoview.tzmovieapp.data

import com.zinoview.tzmovieapp.core.BaseImage
import com.zinoview.tzmovieapp.core.BaseMovie
import com.zinoview.tzmovieapp.data.cloud.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Test for [com.zinoview.tzmovieapp.data.MovieRepository.Test]
 * */

class MovieRepositoryTest {

    private lateinit var repository: MovieRepository

    @Before
    fun setUp() {

        val cloudDataSource = CloudDataSource.Test()
        repository = MovieRepository.Test(
            cloudDataSource,
            BaseMovieMapper.Base(
                BaseImageMapper.Base()
            )
        )
    }

    @Test
    fun test_success_fetching_movies() = runBlocking {

       val expected = MoviesData.Test(
            listOf(
                BaseMovie.Base("Ono","This is desc for Ono film", BaseImage.Base("ono.jpg")),
                BaseMovie.Base("Network Hack","This is desc for NetWork Hack film", BaseImage.Base("network_hack.jpg")),
                BaseMovie.Base("Laptop","This is desc for Laptop film", BaseImage.Base("laptop.jpg")),
            )
        )

        repository.movies()

        val actual = repository.movies()
        assertEquals(expected, actual)
    }

    @Test
    fun test_failure_fetching_movies() = runBlocking {
        val expected = MoviesData.Failure("No connection")
        val actual = repository.movies()

        assertEquals(expected,actual)
    }

}
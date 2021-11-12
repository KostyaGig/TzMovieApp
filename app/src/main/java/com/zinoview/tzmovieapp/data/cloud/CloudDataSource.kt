package com.zinoview.tzmovieapp.data.cloud

interface CloudDataSource {

    suspend fun movies() : List<CloudMovie>

    class Base(
        private val movieService: MovieService
    ) : CloudDataSource {

        override suspend fun movies(): List<CloudMovie>
            = movieService.movies(API_KEY).movies()

        private companion object {
            private const val API_KEY = "N4zMkAYv3gDsiGS34xqJspjBZOEJ0k8e"
        }
    }

    class Test : CloudDataSource {

        override suspend fun movies(): List<CloudMovie> {
            return listOf(
                CloudMovie.Test("Ono","This is desc for Ono film",CloudImage.Test("ono.jpg")),
                CloudMovie.Test("Network Hack","This is desc for NetWork Hack film",CloudImage.Test("network_hack.jpg")),
                CloudMovie.Test("Laptop","This is desc for Laptop film",CloudImage.Test("laptop.jpg")),
            )
        }

    }
}
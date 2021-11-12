package com.zinoview.tzmovieapp.data.cloud

import com.google.gson.annotations.SerializedName
import com.zinoview.tzmovieapp.core.BaseImage
import com.zinoview.tzmovieapp.core.BaseMovie

interface CloudMovies {

    fun movies() : List<CloudMovie>

    class Base(
        @SerializedName("results")
        private val cloudMovies: List<CloudMovie.Base>
    ) : CloudMovies {

        override fun movies(): List<CloudMovie>
            = cloudMovies
    }
}

interface CloudMovie {

    fun map(baseMovieMapper: BaseMovieMapper) : BaseMovie

    class Base(
        @SerializedName("display_title")
        private val title: String,
        @SerializedName("summary_short")
        private val description: String,
        @SerializedName("multimedia")
        private val image: CloudImage.Base,
    ) : CloudMovie {

        override fun map(baseMovieMapper: BaseMovieMapper): BaseMovie
            = baseMovieMapper.map(title,description, image)
    }

    data class Test(
        private val title: String,
        private val description: String,
        private val image: CloudImage.Test
    ) : CloudMovie {

        override fun map(baseMovieMapper: BaseMovieMapper): BaseMovie
            = baseMovieMapper.map(title, description, image)
    }

}

interface CloudImage {


    fun map(baseImageMapper: BaseImageMapper): BaseImage

    class Base(
        @SerializedName("src")
        private val imageUrl: String
    ) : CloudImage {

        override fun map(baseImageMapper: BaseImageMapper): BaseImage
            = baseImageMapper.map(imageUrl)
    }

    data class Test(
        private val imageUrl: String
    ) : CloudImage {
        override fun map(baseImageMapper: BaseImageMapper): BaseImage
            = baseImageMapper.map(imageUrl)
    }
}
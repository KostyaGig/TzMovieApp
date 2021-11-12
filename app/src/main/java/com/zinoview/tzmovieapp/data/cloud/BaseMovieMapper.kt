package com.zinoview.tzmovieapp.data.cloud

import com.zinoview.tzmovieapp.core.BaseMovie

interface BaseMovieMapper {

    fun map(title: String,description: String,image: CloudImage) : BaseMovie

    class Base(
        private val baseImageMapper: BaseImageMapper
    ) : BaseMovieMapper {

        override fun map(title: String, description: String, image: CloudImage): BaseMovie
            = BaseMovie.Base(
                title,description,image.map(baseImageMapper)
            )

    }
}
package com.zinoview.tzmovieapp.data.cloud

import com.zinoview.tzmovieapp.core.BaseImage

interface BaseImageMapper {

    fun map(imageUrl: String) : BaseImage

    class Base : BaseImageMapper {

        override fun map(imageUrl: String): BaseImage
            = BaseImage.Base(imageUrl)
    }
}
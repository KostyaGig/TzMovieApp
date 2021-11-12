package com.zinoview.tzmovieapp.data

import com.zinoview.tzmovieapp.R
import com.zinoview.tzmovieapp.core.Abstract
import com.zinoview.tzmovieapp.core.ResourceProvider
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

interface ExceptionMapper : Abstract.FactoryMapper<Exception,String> {

    class Base(
        private val resourceProvider: ResourceProvider
    ) : ExceptionMapper {

        override fun map(param: Exception): String {
            return when(param) {
                is UnknownHostException -> resourceProvider.string(R.string.no_connection_error_text)
                is HttpException -> resourceProvider.string(R.string.some_went_wrong_text)
                else -> throw IllegalArgumentException("ResourceProvider.Base not handle arg: ${param.javaClass}")
            }
        }
    }

    //todo make test
    class Test : ExceptionMapper {

        override fun map(param: Exception): String {
            return when(param) {
                is UnknownHostException -> "No connection"
                is HttpException -> "Some went wrong"
                else -> throw IllegalArgumentException("ResourceProvider.Base not handle arg: ${param.javaClass}")
            }
        }
    }

}
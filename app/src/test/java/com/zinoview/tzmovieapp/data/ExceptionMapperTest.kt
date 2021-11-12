package com.zinoview.tzmovieapp.data

import org.junit.Assert.*
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

/**
 * Test for [com.zinoview.tzmovieapp.data.ExceptionMapper.Test]
 * */

class ExceptionMapperTest {

    @Test
    fun test_map_exception_to_string_message() {
        val exceptionMapper = ExceptionMapper.Test()
        var exception: Exception = UnknownHostException()
        var expected = "No connection"
        var actual = exceptionMapper.map(exception)

        assertEquals(expected, actual)

        exception = HttpException(Response.success(null))
        expected = "Some went wrong"
        actual = exceptionMapper.map(exception)

        assertEquals(expected, actual)
    }

}
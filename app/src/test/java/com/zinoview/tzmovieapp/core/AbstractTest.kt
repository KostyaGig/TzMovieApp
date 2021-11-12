package com.zinoview.tzmovieapp.core

import org.junit.Assert.*
import org.junit.Test

/**
 * Test for [com.zinoview.tzmovieapp.core.Abstract.MoviesMapper.TestDataMapper.DomainMapper]
 * */

class AbstractTest {

    @Test
    fun test_success_map_data() {
        val mapper = Abstract.MoviesMapper.TestDataMapper.DomainMapper()
        val dataLayer = Abstract.MoviesMapper.DataLayer.Success("Data from network")
        val actual = dataLayer.map(mapper)

        assertTrue(actual is Abstract.MoviesMapper.DomainLayer.Success)

        val expected = Abstract.MoviesMapper.DomainLayer.Success("Data from network")
        assertEquals(expected, actual)
    }

    @Test
    fun test_failure_map_data() {
        val mapper = Abstract.MoviesMapper.TestDataMapper.DomainMapper()
        val dataLayer = Abstract.MoviesMapper.DataLayer.Failure("No connection")
        val actual = dataLayer.map(mapper)

        assertTrue(actual is Abstract.MoviesMapper.DomainLayer.Failure)

        val expected = Abstract.MoviesMapper.DomainLayer.Failure("No connection")
        assertEquals(expected, actual)
    }
}
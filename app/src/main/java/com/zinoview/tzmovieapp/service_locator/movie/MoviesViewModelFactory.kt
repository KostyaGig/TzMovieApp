package com.zinoview.tzmovieapp.service_locator.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zinoview.tzmovieapp.data.ExceptionMapper
import com.zinoview.tzmovieapp.data.MovieRepository
import com.zinoview.tzmovieapp.data.cloud.BaseImageMapper
import com.zinoview.tzmovieapp.data.cloud.BaseMovieMapper
import com.zinoview.tzmovieapp.data.cloud.CloudDataSource
import com.zinoview.tzmovieapp.data.cloud.MovieService
import com.zinoview.tzmovieapp.domain.MovieInteractor
import com.zinoview.tzmovieapp.domain.MoviesDomainMapper
import com.zinoview.tzmovieapp.presentation.MoviesUiMapper
import com.zinoview.tzmovieapp.presentation.MoviesViewModel
import com.zinoview.tzmovieapp.presentation.state.MovieUiStateCommunication
import com.zinoview.tzmovieapp.presentation.state.MovieUiStateMapper
import com.zinoview.tzmovieapp.presentation.state.MoviesUiStateMapper
import com.zinoview.tzmovieapp.service_locator.CoreModule

class MoviesViewModelFactory(
    private val coreModule: CoreModule
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel.Base(
            MovieInteractor.Base(
                MovieRepository.Base(
                    CloudDataSource.Base(
                        coreModule.networkService(MovieService::class.java)
                    ),
                    BaseMovieMapper.Base(
                        BaseImageMapper.Base()
                    ),
                    ExceptionMapper.Base(
                        coreModule.resourceProvider
                    )
                ),
                MoviesDomainMapper.Base()
            ),
            MoviesUiMapper.Base(),
            MoviesUiStateMapper.Base(
                MovieUiStateMapper.Base()
            ),
            MovieUiStateCommunication.Base()
        ) as T
    }
}
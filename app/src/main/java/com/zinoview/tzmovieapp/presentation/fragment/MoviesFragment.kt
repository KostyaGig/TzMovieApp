package com.zinoview.tzmovieapp.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zinoview.tzmovieapp.R
import com.zinoview.tzmovieapp.presentation.adapter.MoviesAdapter
import com.zinoview.tzmovieapp.presentation.adapter.MoviesOnScrollListener
import com.zinoview.tzmovieapp.presentation.core.BaseFragment
import com.zinoview.tzmovieapp.presentation.state.FooterProgressBarCommunication
import com.zinoview.tzmovieapp.presentation.state.MovieUiState
import com.zinoview.tzmovieapp.presentation.state.MoviesUiStateStore
import com.zinoview.tzmovieapp.presentation.state.NextPackMoviesCommunication
import kotlinx.coroutines.*

class MoviesFragment : BaseFragment(R.layout.movies_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val footerProgressBar = view.findViewById<ProgressBar>(R.id.footer_pb)
        val moviesRecyclerView = view.findViewById<RecyclerView>(R.id.movies_rec_view)

        val moviesAdapter = MoviesAdapter.Base()
        val layoutManager = LinearLayoutManager(requireContext())

        val moviesUiStateStore = MoviesUiStateStore.Base(
            NextPackMoviesCommunication(),
            FooterProgressBarCommunication()
        )

        moviesRecyclerView.addOnScrollListener(
            object : MoviesOnScrollListener(layoutManager) {
                override fun isLastPage(): Boolean
                        = moviesUiStateStore.isEmpty()

                override fun isLoading(): Boolean {
                    return moviesUiStateStore.isLoading()
                }

                override fun loadItems(counterNextItems: Int) {
                    lifecycleScope.launch {
                        moviesUiStateStore.nextPactItems(counterNextItems)
                    }
                }
            }
        )

        moviesRecyclerView.adapter = moviesAdapter
        moviesRecyclerView.layoutManager = layoutManager

        moviesViewModel.observe(this) { movieUiState ->
            val firstItem = movieUiState[0]
            firstItem.handleState(moviesAdapter)
            moviesUiStateStore.addAll(movieUiState)
            lifecycleScope.launch {
                firstItem.nextPactMovies(moviesUiStateStore)
            }
        }

        moviesUiStateStore.observe(viewLifecycleOwner) { movies ->
            moviesAdapter.update(movies)
        }

        moviesUiStateStore.observeProgress(viewLifecycleOwner) { visibility ->
            footerProgressBar.visibility = visibility
        }

        moviesViewModel.movies()
    }
}
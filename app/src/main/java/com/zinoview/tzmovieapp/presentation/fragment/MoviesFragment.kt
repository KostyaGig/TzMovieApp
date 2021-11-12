package com.zinoview.tzmovieapp.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zinoview.tzmovieapp.R
import com.zinoview.tzmovieapp.presentation.adapter.MoviesAdapter
import com.zinoview.tzmovieapp.presentation.adapter.MoviesOnScrollListener
import com.zinoview.tzmovieapp.presentation.core.BaseFragment
import com.zinoview.tzmovieapp.presentation.core.log
import com.zinoview.tzmovieapp.presentation.state.MoviesUiStateStore
import kotlinx.coroutines.*

class MoviesFragment : BaseFragment(R.layout.movies_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val footerProgressBar = view.findViewById<ProgressBar>(R.id.footer_pb)
        val moviesRecyclerView = view.findViewById<RecyclerView>(R.id.movies_rec_view)
        val moviesAdapter = MoviesAdapter.Base()
        val layoutManager = LinearLayoutManager(requireContext())
        moviesRecyclerView.adapter = moviesAdapter
        moviesRecyclerView.layoutManager = layoutManager

        val moviesUiStateStore = MoviesUiStateStore.Base()

        moviesRecyclerView.addOnScrollListener(object : MoviesOnScrollListener(layoutManager) {

            override fun isLastPage(): Boolean
                = moviesUiStateStore.isEmpty()

            override fun isLoading(): Boolean {
                val isLoading = moviesUiStateStore.isLoading()
                if (isLoading) {
                    footerProgressBar.visibility = View.VISIBLE
                    CoroutineScope(Dispatchers.IO).launch {
                        delay(3000)

                        withContext(Dispatchers.Main) {}
                    }
                } else {
                    footerProgressBar.visibility = View.GONE
                }
                return isLoading
            }

            override fun loadItems() {
                val newItems = moviesUiStateStore.nextPactItems()
                log("load items size ${newItems.size}")
                moviesAdapter.update(newItems)
            }

        })

        moviesViewModel.observe(this) { movieUiState ->
            moviesUiStateStore.addAll(movieUiState)
            moviesAdapter.update(moviesUiStateStore.nextPactItems())
        }

        moviesViewModel.movies()
    }
}
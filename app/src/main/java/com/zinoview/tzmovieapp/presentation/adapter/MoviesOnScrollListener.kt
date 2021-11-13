package com.zinoview.tzmovieapp.presentation.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zinoview.tzmovieapp.presentation.core.log

abstract class MoviesOnScrollListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    private val alreadyLoadedItems = mutableListOf<Int>()
    private var firstShowMovies = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount;
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition() + 1
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition() + 1

        if (isLoading().not() && isLastPage().not()) {
            if (firstShowMovies) {
                alreadyLoadedItems.add(COUNT_MOVIE_PACK)
            } else {
                if (lastVisibleItemPosition % COUNT_MOVIE_PACK == 0) {
                    if (alreadyLoadedItems.contains(lastVisibleItemPosition)) {
                        val nextItems = lastVisibleItemPosition + COUNT_MOVIE_PACK
                        loadItems(nextItems)
                        alreadyLoadedItems.add(nextItems)
                    }
                }
            }
        }

        firstShowMovies = false
    }

    abstract fun isLastPage() : Boolean

    abstract fun isLoading() : Boolean

    abstract fun loadItems(counterNextItems: Int)

    private companion object {
        private const val ONE = 1
        private const val COUNT_MOVIE_PACK = 5
    }
}
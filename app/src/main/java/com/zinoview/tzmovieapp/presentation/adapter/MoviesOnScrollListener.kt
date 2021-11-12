package com.zinoview.tzmovieapp.presentation.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zinoview.tzmovieapp.presentation.core.log

abstract class MoviesOnScrollListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount;
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        log("Visible item count $visibleItemCount , titalItemCount $totalItemCount, firstVisibleItemPosition $firstVisibleItemPosition")

        if (isLoading().not() && isLastPage().not()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadItems()
            }
        }
    }

    abstract fun isLastPage() : Boolean

    abstract fun isLoading() : Boolean

    abstract fun loadItems()
}
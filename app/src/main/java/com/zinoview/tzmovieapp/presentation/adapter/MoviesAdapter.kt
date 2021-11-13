package com.zinoview.tzmovieapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zinoview.tzmovieapp.R
import com.zinoview.tzmovieapp.presentation.state.MovieUiState

interface MoviesAdapter {

    fun update(items: List<MovieUiState>)

    class Base() : MoviesAdapter, RecyclerView.Adapter<Base.MoviesViewHolder>() {

        private val movies = ArrayList<MovieUiState>()

        override fun update(items: List<MovieUiState>) {
            movies.addAll(items)
            notifyDataSetChanged()
        }

        private companion object {
            private const val BASE = 1
            private const val FAILURE = 2
        }

        override fun getItemViewType(position: Int): Int {
            return when (movies[position]) {
                is MovieUiState.Base -> BASE
                else -> FAILURE
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
            return when (viewType) {
                BASE -> MoviesViewHolder.Base(R.layout.movie_item.makeView(parent))
                else -> MoviesViewHolder.Failure(R.layout.failure_item.makeView(parent))
            }
        }

        override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
            val movie = movies[position]
            holder.bind(movie)
        }

        override fun getItemCount(): Int
            = movies.size

        abstract class MoviesViewHolder(
            view: View
        ) : RecyclerView.ViewHolder(view) {

            open fun bind(item: MovieUiState) {}

            class Base(view: View) : MoviesViewHolder(view) {
                private val movieImageView = view.findViewById<ImageView>(R.id.movie_image)
                private val movieTitleTextView = view.findViewById<TextView>(R.id.movie_title_tv)
                private val movieDescriptionTextView = view.findViewById<TextView>(R.id.movie_description_tv)

                override fun bind(item: MovieUiState) {
                    item.map(movieImageView,movieTitleTextView,movieDescriptionTextView)
                }
            }

            class Failure(view: View) : MoviesViewHolder(view) {
                private val movieErrorTextView = view.findViewById<TextView>(R.id.movie_failure_tv)

                override fun bind(item: MovieUiState) {
                    item.map(movieErrorTextView)
                }
            }
        }

        private fun Int.makeView(parent: ViewGroup) : View
            = LayoutInflater.from(parent.context).inflate(this,parent,false)
    }
}


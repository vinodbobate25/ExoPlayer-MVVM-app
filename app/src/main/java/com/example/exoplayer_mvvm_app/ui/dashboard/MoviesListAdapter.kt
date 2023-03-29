package com.example.exoplayer_mvvm_app.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exoplayer_mvvm_app.R
import com.example.exoplayer_mvvm_app.data.model.Movies
import com.example.exoplayer_mvvm_app.databinding.ListitemBinding

class MoviesListAdapter(private val listener: OnItemClickListener) :
ListAdapter<Movies, MoviesListAdapter.MovieHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = ListitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class MovieHolder(private val binding: ListitemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val movie = getItem(position)
                        listener.onItemClick(movie)
                    }
                }
                imgBookmark.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val movie = getItem(position)
                        listener.onBookMarkClick(movie)
                        imgBookmark.background =
                            root.context.getDrawable(R.drawable.ic_bookmark_selected)
                    }
                }
            }
        }

        fun bind(movie: Movies) {
            binding.apply {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2"+movie.backdrop_path)
                    .into(imageViewAvatar)
                txtMovies.text = movie.overview
                txtMovieTitle.text = movie.title
                tvSource.text = movie.release_date
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(movie: Movies)
        fun onBookMarkClick(movie: Movies)
    }
    class DiffCallback : DiffUtil.ItemCallback<Movies>() {
        override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem == newItem
        }

    }

}
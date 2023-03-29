package com.example.exoplayer_mvvm_app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exoplayer_mvvm_app.data.model.Movies
import com.example.exoplayer_mvvm_app.databinding.BookmarkItemBinding

class BookmarkAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Movies, BookmarkAdapter.BookMarkViewHolder>(
        DiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookMarkViewHolder {
        val binding =
            BookmarkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookMarkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookMarkViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class BookMarkViewHolder(private val binding: BookmarkItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val Movies = getItem(position)
                        listener.onItemClick(Movies)
                    }
                }
            }
        }

        fun bind(movie: Movies) {
            binding.apply {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2"+movie.backdrop_path)
                    .into(imageViewAvatar)
                txtHeader.text = movie.title
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(Movies: Movies)
        fun onBookMarkClick(Movies: Movies)
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

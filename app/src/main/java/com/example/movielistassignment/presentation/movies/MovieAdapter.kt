package com.example.movielistassignment.presentation.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movielistassignment.R
import com.example.movielistassignment.databinding.ItemMovieBinding


class MovieAdapter(private val movieList: List<MovieData>) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    inner class MovieHolder(private val binding: ItemMovieBinding) : ViewHolder(binding.root) {
        fun bind(movieData: MovieData) {
            binding.movieTv.text = movieData.title
            Glide.with(binding.root.context).load(movieData.imageUrl)
                .error(R.drawable.baseline_broken_image_24)
                .into(binding.movieImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) =
        holder.bind(movieList[position])

}
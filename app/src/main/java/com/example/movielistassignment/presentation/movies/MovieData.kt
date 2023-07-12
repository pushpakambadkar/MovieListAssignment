package com.example.movielistassignment.presentation.movies

import com.example.core.domain.models.MovieItem

data class MovieData(val title: String,
                     val imageUrl: String,
                     val id: Long) {

    companion object {
        fun from(movieItem: MovieItem, baseUrl: String): MovieData = MovieData(movieItem.title, baseUrl+movieItem.posterPath, movieItem.id)
    }
}
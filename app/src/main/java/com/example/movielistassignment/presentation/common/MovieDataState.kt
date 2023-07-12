package com.example.movielistassignment.presentation.common

import com.example.movielistassignment.presentation.movies.MovieData

sealed class MovieDataState {
    object LoadingState: MovieDataState()
    data class SuccessState(val data: List<MovieData>): MovieDataState()
    object ErrorState: MovieDataState()
}
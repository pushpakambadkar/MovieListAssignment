package com.example.movielistassignment.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.GetMovieUseCase
import com.example.movielistassignment.presentation.common.MovieDataState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val getMovieUseCase: GetMovieUseCase) :
    ViewModel() {

    private val _movieData = MutableLiveData<MovieDataState>()
    val movieData: LiveData<MovieDataState> = _movieData
    private var currentPage = 0
    private var totalPages = 0

    fun getMovies() {
        if (currentPage > totalPages) {
            return
        }
        if (currentPage == 0) {
            _movieData.value = MovieDataState.LoadingState
        }
        viewModelScope.launch {
            val defer = async { getMovieUseCase.getConfigurations() }
            when (val configurations = defer.await()) {
                is com.example.core.Result.Success, is com.example.core.Result.Error -> {
                    when (val data = getMovieUseCase.invoke(++currentPage)) {
                        is com.example.core.Result.Success -> {
                            val imageUrl = if (configurations is com.example.core.Result.Success) {
                                configurations.data.images.secureBaseUrl + configurations.data.images.posterSizes[3]
                            } else ""
                            _movieData.value = data.data.movieEntity.map { MovieData.from(it, imageUrl) }.let {
                                MovieDataState.SuccessState(it)
                            }
                            currentPage = data.data.page
                            totalPages = data.data.totalPages
                        }
                        else -> _movieData.value = MovieDataState.ErrorState
                    }
                }
            }
        }
    }

}
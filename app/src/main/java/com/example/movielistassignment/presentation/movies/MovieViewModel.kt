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
    private var baseImageUrl = ""
    private var currentPage = 0
    private var totalPages = 0

    fun getConfigurations() {
        if (currentPage > totalPages) {
            return
        }
        _movieData.value = MovieDataState.LoadingState
        viewModelScope.launch {
            val defer = async { getMovieUseCase.getConfigurations() }
            when (val configurations = defer.await()) {
                is com.example.core.Result.Success, is com.example.core.Result.Error -> {
                    baseImageUrl = if (configurations is com.example.core.Result.Success) {
                        configurations.data.images.secureBaseUrl + configurations.data.images.posterSizes[3]
                    } else ""
                    loadMovies(true)
                }
            }
        }
    }

    fun loadMovies(firstTime: Boolean) {
        if (firstTime) {
            currentPage = 0
        }
        viewModelScope.launch {
            when (val data = getMovieUseCase.invoke(++currentPage)) {
                is com.example.core.Result.Success -> {
                    _movieData.value =
                        data.data.movieEntity.map { MovieData.from(it, baseImageUrl) }.let {
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
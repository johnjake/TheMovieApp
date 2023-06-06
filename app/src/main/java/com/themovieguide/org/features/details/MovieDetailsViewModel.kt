package com.themovieguide.org.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieguide.data.features.details.DetailsMovieRepository
import com.themovieguide.domain.features.details.DetailsRepository
import com.themovieguide.domain.model.Movie
import com.themovieguide.domain.states.details.StateDetails
import com.themovieguide.domain.states.showing.StateMovies
import com.themovieguide.domain.states.showing.StateMoviesDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: DetailsRepository,
    private val localRepository: DetailsMovieRepository,
) : ViewModel() {
    private val showFlow: MutableSharedFlow<StateDetails> = MutableSharedFlow(replay = 1)
    val showShared: SharedFlow<StateDetails> = showFlow

    private val roomFlow: MutableSharedFlow<StateMovies> = MutableSharedFlow(replay = 1)
    val mediaShared: SharedFlow<StateMovies> = roomFlow

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            when (val state = repository.fetchMovieDetails(movieId = movieId)) {
                is StateDetails.ShowLoader -> showFlow.emit(StateDetails.ShowLoader)
                is StateDetails.HideLoader -> showFlow.emit(StateDetails.HideLoader)
                is StateDetails.OnSuccess -> showFlow.emit(StateDetails.OnSuccess(data = state.data))
                is StateDetails.OnFailed -> showFlow.emit(StateDetails.OnFailed(error = state.error))
                else -> showFlow.emit(StateDetails.HideLoader)
            }
        }
    }

    fun insertLocalMovie(movie: Movie) {
        localRepository.insertMovie(movie)
    }
    fun fetchVisitedMovies() {
        viewModelScope.launch {
            roomFlow.emit(StateMovies.ShowLoader)
            when (val response = localRepository.getMovies()) {
                is StateMoviesDB.OnSuccess -> roomFlow.emit(StateMovies.OnSuccess(data = response.data))
                is StateMoviesDB.OnFailure -> {
                    roomFlow.emit(StateMovies.OnFailure(error = response.error))
                }
                else -> roomFlow.emit(StateMovies.HideLoader)
            }
            roomFlow.emit(StateMovies.HideLoader)
        }
    }
}

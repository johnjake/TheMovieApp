package com.themovieguide.org.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieguide.domain.features.cast.CastRepository
import com.themovieguide.domain.states.cast.StateCast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CastViewModel @Inject constructor(private val repository: CastRepository) : ViewModel() {
    private val castFlow: MutableSharedFlow<StateCast> = MutableSharedFlow(replay = 1)
    val sharedFlow: SharedFlow<StateCast> = castFlow
    fun fetchMovieCast(movieId: Int) {
        viewModelScope.launch {
            when (val state = repository.fetchCast(movieId = movieId)) {
                is StateCast.ShowLoader -> castFlow.emit(StateCast.ShowLoader)
                is StateCast.HideLoader -> castFlow.emit(StateCast.HideLoader)
                is StateCast.OnSuccess -> castFlow.emit(StateCast.OnSuccess(data = state.data))
                is StateCast.OnFailed -> castFlow.emit(StateCast.OnFailed(error = state.error))
                else -> castFlow.emit(StateCast.HideLoader)
            }
        }
    }
}

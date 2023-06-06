package com.themovieguide.org.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieguide.domain.features.details.GetDetailsTelevisionRepository
import com.themovieguide.domain.states.television.StateSingleTelevision
import com.themovieguide.domain.states.television.StateTelevisionSingle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TelevisionDetailsViewModel @Inject constructor(
    private val repository: GetDetailsTelevisionRepository,
) : ViewModel() {
    private val detailsFlow: MutableSharedFlow<StateTelevisionSingle> = MutableSharedFlow(replay = 1)
    val detailsShared: SharedFlow<StateTelevisionSingle> = detailsFlow

    fun fetchMovieDetails(seriesId: Int) {
        viewModelScope.launch {
            when (val state = repository.fetchDetailsTelevisionShow(seriesId = seriesId)) {
                is StateSingleTelevision.ShowLoader -> detailsFlow.emit(StateTelevisionSingle.ShowLoader)
                is StateSingleTelevision.HideLoader -> detailsFlow.emit(StateTelevisionSingle.HideLoader)
                is StateSingleTelevision.OnSuccess -> detailsFlow.emit(StateTelevisionSingle.OnSuccess(data = state.data))
                is StateSingleTelevision.OnFailed -> detailsFlow.emit(StateTelevisionSingle.OnFailed(error = state.error))
                else -> detailsFlow.emit(StateTelevisionSingle.HideLoader)
            }
        }
    }
}

package com.themovieguide.org.features.ratedtv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieguide.domain.features.ratedtv.RatedTvRepository
import com.themovieguide.domain.model.television.LiveVision
import com.themovieguide.domain.states.television.StateTelevision
import com.themovieguide.org.features.state.StateLiveTelevision
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RatedTelevisionViewModel @Inject constructor(
    private val repository: RatedTvRepository,
) : ViewModel() {
    private val ratedFlow: MutableSharedFlow<StateLiveTelevision<List<LiveVision>>> = MutableSharedFlow(replay = 1)
    val ratedShared: SharedFlow<StateLiveTelevision<List<LiveVision>>> = ratedFlow

    fun fetchTopRated() {
        viewModelScope.launch {
            when (val response = repository.fetchTopRatedShow()) {
                is StateTelevision.ShowLoader -> ratedFlow.emit(StateLiveTelevision.ShowLoader)
                is StateTelevision.HideLoader -> ratedFlow.emit(StateLiveTelevision.HideLoader)
                is StateTelevision.OnSuccess -> {
                    response.data.collectLatest { tv ->
                        ratedFlow.emit(StateLiveTelevision.OnSuccess(data = tv))
                    }
                }
                is StateTelevision.OnFailed -> ratedFlow.emit(StateLiveTelevision.OnFailure(error = response.error ?: "TopRated Television: Unknown error"))
                else -> ratedFlow.emit(StateLiveTelevision.HideLoader)
            }
        }
    }
}

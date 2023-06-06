package com.themovieguide.org.features.discovertv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieguide.domain.features.discover.DiscoverTvRepository
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
class DiscoverTelevisionViewModel @Inject constructor(
    private val repository: DiscoverTvRepository
) : ViewModel() {
    private val discoverFlow: MutableSharedFlow<StateLiveTelevision<List<LiveVision>>> = MutableSharedFlow(replay = 1)
    val discoverShared: SharedFlow<StateLiveTelevision<List<LiveVision>>> = discoverFlow

    fun fetchDiscover() {
        viewModelScope.launch {
            when (val response = repository.fetchDiscoverShow()) {
                is StateTelevision.ShowLoader -> discoverFlow.emit(StateLiveTelevision.ShowLoader)
                is StateTelevision.HideLoader -> discoverFlow.emit(StateLiveTelevision.HideLoader)
                is StateTelevision.OnSuccess -> {
                    response.data.collectLatest { tv ->
                        discoverFlow.emit(StateLiveTelevision.OnSuccess(data = tv))
                    }
                }
                is StateTelevision.OnFailed -> discoverFlow.emit(StateLiveTelevision.OnFailure(error = response.error ?: "TopRated Television: Unknown error"))
                else -> discoverFlow.emit(StateLiveTelevision.HideLoader)
            }
        }
    }
}

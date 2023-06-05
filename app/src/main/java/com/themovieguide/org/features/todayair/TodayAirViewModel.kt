package com.themovieguide.org.features.todayair

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieguide.domain.features.todayair.TodayAirStorageRepository
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
class TodayAirViewModel @Inject constructor(
    private val repository: TodayAirStorageRepository,
) : ViewModel() {
    private val airFlow: MutableSharedFlow<StateLiveTelevision<List<LiveVision>>> = MutableSharedFlow(replay = 1)
    val airShared: SharedFlow<StateLiveTelevision<List<LiveVision>>> = airFlow

    fun fetchTodayAir() {
        viewModelScope.launch {
            when (val response = repository.fetchTodayAirShow()) {
                is StateTelevision.ShowLoader -> airFlow.emit(StateLiveTelevision.ShowLoader)
                is StateTelevision.HideLoader -> airFlow.emit(StateLiveTelevision.HideLoader)
                is StateTelevision.OnSuccess -> {
                    response.data.collectLatest { tv ->
                        airFlow.emit(StateLiveTelevision.OnSuccess(data = tv))
                    }
                }
                is StateTelevision.OnFailed -> airFlow.emit(StateLiveTelevision.OnFailure(error = response.error ?: "TopRated Television: Unknown error"))
                else -> airFlow.emit(StateLiveTelevision.HideLoader)
            }
        }
    }
}

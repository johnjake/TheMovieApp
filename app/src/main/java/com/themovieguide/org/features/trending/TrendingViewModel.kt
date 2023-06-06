package com.themovieguide.org.features.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieguide.domain.features.trending.TrendingTvRepository
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
class TrendingViewModel @Inject constructor(
    private val repository: TrendingTvRepository
) : ViewModel() {
    private val trendFlow: MutableSharedFlow<StateLiveTelevision<List<LiveVision>>> = MutableSharedFlow(replay = 1)
    val trendShared: SharedFlow<StateLiveTelevision<List<LiveVision>>> = trendFlow

    fun fetchTrending() {
        viewModelScope.launch {
            when (val response = repository.fetchTrendingShow()) {
                is StateTelevision.ShowLoader -> trendFlow.emit(StateLiveTelevision.ShowLoader)
                is StateTelevision.HideLoader -> trendFlow.emit(StateLiveTelevision.HideLoader)
                is StateTelevision.OnSuccess -> {
                    response.data.collectLatest { tv ->
                        trendFlow.emit(StateLiveTelevision.OnSuccess(data = tv))
                    }
                }
                is StateTelevision.OnFailed -> trendFlow.emit(StateLiveTelevision.OnFailure(error = response.error ?: "Trending Television: Unknown error"))
                else -> trendFlow.emit(StateLiveTelevision.HideLoader)
            }
        }
    }
}
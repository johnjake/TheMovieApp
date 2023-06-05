package com.themovieguide.org.features.searchtv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieguide.domain.features.searchtv.SearchTelevisionRepository
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
class SearchTelevisionViewModel @Inject constructor(
    private val repository: SearchTelevisionRepository,
) : ViewModel() {
    private val searchFlow: MutableSharedFlow<StateLiveTelevision<List<LiveVision>>> = MutableSharedFlow(replay = 1)
    val searchShared: SharedFlow<StateLiveTelevision<List<LiveVision>>> = searchFlow

    fun searchTelevision(query: String) {
        viewModelScope.launch {
            when (val response = repository.fetchSearch(query = query)) {
                is StateTelevision.ShowLoader -> searchFlow.emit(StateLiveTelevision.ShowLoader)
                is StateTelevision.HideLoader -> searchFlow.emit(StateLiveTelevision.HideLoader)
                is StateTelevision.OnSuccess -> {
                    response.data.collectLatest { tv ->
                        searchFlow.emit(StateLiveTelevision.OnSuccess(data = tv))
                    }
                }
                is StateTelevision.OnFailed -> searchFlow.emit(StateLiveTelevision.OnFailure(error = response.error ?: "TopRated Television: Unknown error"))
                else -> searchFlow.emit(StateLiveTelevision.HideLoader)
            }
        }
    }
}

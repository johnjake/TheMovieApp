package com.themovieguide.domain.states.showing

import com.themovieguide.domain.model.MetaMessage
import com.themovieguide.domain.model.Movie
import com.themovieguide.domain.model.Movies
import kotlinx.coroutines.flow.Flow

sealed class StateShowing {
    object ShowLoader : StateShowing()
    object HideLoader : StateShowing()

    data class OnSuccess(val data: Flow<List<Movies>>) : StateShowing()
    data class OnFailed(val error: String?) : StateShowing()
    data class NoInternet(val data: Flow<List<Movies>>) : StateShowing()
}

sealed class StateMeta {
    data class OnSuccess(val data: String) : StateMeta()
    data class OnFailed(val error: String?) : StateMeta()
    object NoInternet : StateMeta()
}

sealed class StateSingleMeta {
    data class OnSuccess(val data: Movie) : StateSingleMeta()
    data class OnFailed(val error: String?) : StateSingleMeta()
}

sealed class StateRawMeta {
    data class OnSuccess(val data: List<Movies>) : StateMeta()
    data class OnFailed(val error: String?) : StateMeta()
    data class OnFailedMessage(val data: MetaMessage)
}

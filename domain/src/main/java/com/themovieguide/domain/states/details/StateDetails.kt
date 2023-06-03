package com.themovieguide.domain.states.details

import com.themovieguide.domain.model.Movie

sealed class StateDetails {
    object ShowLoader : StateDetails()
    object HideLoader : StateDetails()
    data class OnSuccess(val data: Movie) : StateDetails()
    data class OnFailed(val error: String) : StateDetails()
}

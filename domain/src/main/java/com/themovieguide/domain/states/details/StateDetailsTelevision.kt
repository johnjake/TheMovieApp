package com.themovieguide.domain.states.details

import com.themovieguide.domain.model.television.DetailsTelevision

sealed class StateDetailsTelevision {
    object ShowLoader : StateDetailsTelevision()
    object HideLoader : StateDetailsTelevision()
    data class OnSuccess(val data: DetailsTelevision) : StateDetailsTelevision()
    data class OnFailed(val error: String) : StateDetailsTelevision()
}

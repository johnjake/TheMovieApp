package com.themovieguide.domain.states.youtube

sealed class StateVideo {
    object ShowLoader : StateVideo()
    object HideLoader : StateVideo()
    data class OnSuccess(val data: String) : StateVideo()
    data class OnFailure(val error: String) : StateVideo()
}

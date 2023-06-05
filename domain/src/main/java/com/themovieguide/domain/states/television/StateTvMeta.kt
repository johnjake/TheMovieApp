package com.themovieguide.domain.states.television

sealed class StateTvMeta {
    data class OnSuccess(val data: String) : StateTvMeta()
    data class OnFailed(val error: String?) : StateTvMeta()
    object NoInternet : StateTvMeta()
}

package com.themovieguide.domain.states.cast

import com.themovieguide.domain.model.cast.MainCast

sealed class StateCast {
    object HideLoader : StateCast()
    object ShowLoader : StateCast()
    data class OnSuccess(val data: MainCast) : StateCast()
    data class OnFailed(val error: String) : StateCast()
}

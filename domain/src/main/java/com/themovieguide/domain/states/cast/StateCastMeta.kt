package com.themovieguide.domain.states.cast

import com.themovieguide.domain.model.cast.MainCast

sealed class StateCastMeta {
    data class OnSuccess(val data: MainCast) : StateCastMeta()
    data class OnFailed(val error: String?) : StateCastMeta()
}

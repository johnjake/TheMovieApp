package com.themovieguide.org.features.state

import com.themovieguide.domain.model.Movies

sealed class StateMovie<out T> {
    object ShowLoader : StateMovie<Nothing>()
    object HideLoader : StateMovie<Nothing>()
    data class OnSuccess<out T>(val data: List<Movies>) : StateMovie<T>()
    data class OnFailure(val error: String) : StateMovie<Nothing>()
}

package com.themovieguide.org.features.state

import com.themovieguide.domain.model.television.LiveVision

sealed class StateLiveTelevision<out T> {
    object ShowLoader : StateLiveTelevision<Nothing>()
    object HideLoader : StateLiveTelevision<Nothing>()
    data class OnSuccess<out T>(val data: List<LiveVision>) : StateLiveTelevision<T>()
    data class OnFailure(val error: String) : StateLiveTelevision<Nothing>()
}

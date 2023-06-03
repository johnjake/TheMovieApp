package com.themovieguide.data.model.dto

import android.os.Parcelable
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class Playback(
    val id: String? = EMPTY,
    val iso31661: String? = EMPTY,
    val iso6391: String? = EMPTY,
    val key: String? = EMPTY,
    val name: String? = EMPTY,
    val official: Boolean? = false,
    val publishedAt: String? = EMPTY,
    val site: String? = EMPTY,
    val size: Int? = 0,
    val type: String? = EMPTY,
) : Parcelable

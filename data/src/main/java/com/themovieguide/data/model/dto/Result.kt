package com.themovieguide.data.model.dto

import android.os.Parcelable
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val id: String? = EMPTY,
    val iso_3166_1: String? = EMPTY,
    val iso_639_1: String? = EMPTY,
    val key: String? = EMPTY,
    val name: String? = EMPTY,
    val official: Boolean? = false,
    val published_at: String? = EMPTY,
    val site: String? = EMPTY,
    val size: Int? = 0,
    val type: String? = EMPTY,
) : Parcelable

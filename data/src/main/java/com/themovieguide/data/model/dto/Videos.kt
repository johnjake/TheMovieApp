package com.themovieguide.data.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Videos(
    val results: List<Result>? = emptyList(),
) : Parcelable {
    constructor() : this(results = emptyList())
}

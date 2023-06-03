package com.themovieguide.data.model.dto

import android.os.Parcelable
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dates(
    val maximum: String? = EMPTY,
    val minimum: String? = EMPTY,
) : Parcelable {
    constructor() : this(
        maximum = "",
        minimum = "",
    )
}

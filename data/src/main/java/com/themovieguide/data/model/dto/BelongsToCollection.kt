package com.themovieguide.data.model.dto

import android.os.Parcelable
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class BelongsToCollection(
    val backdrop_path: String? = EMPTY,
    val id: Int? = 0,
    val name: String? = EMPTY,
    val poster_path: String? = EMPTY,
) : Parcelable {
    constructor() : this(backdrop_path = EMPTY, id = 0, name = EMPTY, poster_path = EMPTY)
}

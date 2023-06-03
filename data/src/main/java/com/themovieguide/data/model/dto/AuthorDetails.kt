package com.themovieguide.data.model.dto

import android.os.Parcelable
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthorDetails(
    val avatar_path: String? = EMPTY,
    val name: String? = EMPTY,
    val rating: Double? = 0.0,
    val username: String? = EMPTY,
) : Parcelable {
    constructor() : this(avatar_path = EMPTY, name = EMPTY, rating = 0.0, username = EMPTY)
}

package com.themovieguide.data.model.dto.television.details

import android.os.Parcelable
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val id: Int? = 0,
    val name: String? = EMPTY,
) : Parcelable

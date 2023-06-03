package com.themovieguide.data.model.dto

import android.os.Parcelable
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class Casts(
    val adult: Boolean? = false,
    val cast_id: Int? = 0,
    val character: String? = EMPTY,
    val credit_id: String? = EMPTY,
    val gender: Int? = 0,
    val id: Int? = 0,
    val known_for_department: String? = EMPTY,
    val name: String? = EMPTY,
    val order: Int? = 0,
    val original_name: String? = EMPTY,
    val popularity: Double? = 0.0,
    val profile_path: String? = EMPTY,
) : Parcelable

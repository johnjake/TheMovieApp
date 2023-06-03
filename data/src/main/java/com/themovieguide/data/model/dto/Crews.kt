package com.themovieguide.data.model.dto

import android.os.Parcelable
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class Crews(
    val adult: Boolean? = false,
    val credit_id: String? = EMPTY,
    val department: String? = EMPTY,
    val gender: Int? = 0,
    val id: Int? = 0,
    val job: String? = EMPTY,
    val known_for_department: String? = EMPTY,
    val name: String? = EMPTY,
    val original_name: String? = EMPTY,
    val popularity: Double? = 0.0,
    val profile_path: String? = EMPTY,
) : Parcelable

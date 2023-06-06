package com.themovieguide.data.model.dto.television.details

import android.os.Parcelable
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreatedBy(
    val credit_id: String? = EMPTY,
    val gender: Int? = 0,
    val id: Int? = 0,
    val name: String? = EMPTY,
    val profile_path: String? = EMPTY,
) : Parcelable

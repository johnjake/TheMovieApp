package com.themovieguide.data.model.dto.television.details

import android.os.Parcelable
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductionCompany(
    val id: Int? = 0,
    val logo_path: String? = EMPTY,
    val name: String? = EMPTY,
    val origin_country: String? = EMPTY,
) : Parcelable

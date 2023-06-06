package com.themovieguide.data.model.dto.television.details

import android.os.Parcelable
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpokenLanguage(
    val english_name: String? = EMPTY,
    val iso_639_1: String? = EMPTY,
    val name: String? = EMPTY,
) : Parcelable

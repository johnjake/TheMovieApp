package com.themovieguide.data.model.dto.television.details

import android.os.Parcelable
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class Season(
    val air_date: String? = EMPTY,
    val episode_count: Int? = 0,
    val id: Int? = 0,
    val name: String? = EMPTY,
    val overview: String? = EMPTY,
    val poster_path: String? = EMPTY,
    val season_number: Int? = 0,
) : Parcelable

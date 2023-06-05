package com.themovieguide.data.model.dto.television.rated

import android.os.Parcelable
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class Television(
    val backdrop_path: String? = EMPTY,
    val first_air_date: String? = EMPTY,
    val genre_ids: List<Int>? = emptyList(),
    val id: Int? = 0,
    val name: String? = EMPTY,
    val origin_country: List<String>? = emptyList(),
    val original_language: String? = EMPTY,
    val original_name: String? = EMPTY,
    val overview: String? = EMPTY,
    val popularity: Double? = 0.0,
    val poster_path: String? = EMPTY,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0,
) : Parcelable

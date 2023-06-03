package com.themovieguide.data.model.dto

import android.os.Parcelable
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize
@Parcelize
data class Movie(
    val adult: Boolean? = false,
    val backdrop_path: String? = EMPTY,
    val genre_ids: List<Int>? = emptyList(),
    val id: Int? = 0,
    val original_language: String? = EMPTY,
    val original_title: String? = EMPTY,
    val overview: String? = EMPTY,
    val popularity: Double? = 0.0,
    val poster_path: String? = EMPTY,
    val release_date: String? = EMPTY,
    val title: String? = EMPTY,
    val video: Boolean? = false,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0,
) : Parcelable

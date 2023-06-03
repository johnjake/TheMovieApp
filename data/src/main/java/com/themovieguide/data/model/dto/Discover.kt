package com.themovieguide.data.model.dto

import android.os.Parcelable
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class Discover(
    var adult: Boolean? = false,
    val backdropPath: String? = EMPTY,
    val genreIds: List<Int>? = arrayListOf(),
    val id: Int? = 0,
    val originalLanguage: String? = EMPTY,
    val originalTitle: String? = EMPTY,
    val overview: String? = EMPTY,
    val popularity: Double? = 0.0,
    val posterPath: String? = EMPTY,
    val releaseDate: String? = EMPTY,
    val title: String? = EMPTY,
    val video: Boolean? = false,
    val voteAverage: Double? = 0.0,
    val voteCount: Int? = 0,
) : Parcelable

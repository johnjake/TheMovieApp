package com.themovieguide.data.model.meta

import android.os.Parcelable
import com.themovieguide.data.model.dto.Dates
import com.themovieguide.data.model.dto.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieMeta(
    val dates: Dates? = Dates(),
    val page: Int? = 0,
    val results: List<Movie>? = emptyList(),
    val total_pages: Int? = 0,
    val total_results: Int? = 0,
) : Parcelable

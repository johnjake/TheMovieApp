package com.themovieguide.data.model.meta.television

import android.os.Parcelable
import com.themovieguide.data.model.dto.television.rated.Television
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodayAirMeta(
    val page: Int? = 0,
    val results: List<Television>? = emptyList(),
    val total_pages: Int? = 0,
    val total_results: Int? = 0,
) : Parcelable

package com.themovieguide.data.model.meta

import android.os.Parcelable
import com.themovieguide.data.model.dto.Discover
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiscoverMeta(
    val page: Int? = 0,
    val results: List<Discover>? = emptyList(),
    val totalPages: Int? = 0,
    val totalResults: Int? = 0,
) : Parcelable

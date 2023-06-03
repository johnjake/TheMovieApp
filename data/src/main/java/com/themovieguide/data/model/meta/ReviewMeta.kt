package com.themovieguide.data.model.meta

import android.os.Parcelable
import com.themovieguide.data.model.dto.Review
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewMeta(
    val id: Int? = 0,
    val page: Int? = 0,
    val results: List<Review>? = emptyList(),
    val total_pages: Int? = 0,
    val total_results: Int? = 0,
) : Parcelable

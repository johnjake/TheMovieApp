package com.themovieguide.data.model.dto

import android.os.Parcelable
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review(
    val author: String? = EMPTY,
    val author_details: AuthorDetails? = AuthorDetails(),
    val content: String? = EMPTY,
    val created_at: String? = EMPTY,
    val id: String? = EMPTY,
    val updated_at: String? = EMPTY,
    val url: String? = EMPTY,
) : Parcelable

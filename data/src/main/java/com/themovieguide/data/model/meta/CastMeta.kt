package com.themovieguide.data.model.meta

import android.os.Parcelable
import com.themovieguide.data.model.dto.Casts
import com.themovieguide.data.model.dto.Crews
import kotlinx.parcelize.Parcelize

@Parcelize
data class CastMeta(
    val cast: List<Casts>? = emptyList(),
    val crew: List<Crews>? = emptyList(),
    val id: Int? = 0,
) : Parcelable

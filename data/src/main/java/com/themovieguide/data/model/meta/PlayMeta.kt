package com.themovieguide.data.model.meta

import android.os.Parcelable
import com.themovieguide.data.model.dto.Playback
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayMeta(
    val id: Int,
    val results: List<Playback>,
) : Parcelable

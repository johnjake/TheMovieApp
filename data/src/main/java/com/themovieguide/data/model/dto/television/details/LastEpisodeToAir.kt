package com.themovieguide.data.model.dto.television.details

import android.os.Parcelable
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class LastEpisodeToAir(
    val air_date: String? = EMPTY,
    val episode_number: Int? = 0,
    val id: Int? = 0,
    val name: String? = EMPTY,
    val overview: String? = EMPTY,
    val production_code: String? = EMPTY,
    val runtime: Int? = 0,
    val season_number: Int? = 0,
    val show_id: Int? = 0,
    val still_path: String? = EMPTY,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0,
) : Parcelable {
    constructor() : this(
        air_date = EMPTY,
        episode_number = 0,
        id = 0,
        name = EMPTY,
        overview = EMPTY,
        production_code = EMPTY,
        runtime = 0,
        season_number = 0,
        show_id = 0,
        still_path = EMPTY,
        vote_average = 0.0,
        vote_count = 0,
    )
}

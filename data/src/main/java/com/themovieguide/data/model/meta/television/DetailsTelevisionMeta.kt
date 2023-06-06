package com.themovieguide.data.model.meta.television

import android.os.Parcelable
import com.themovieguide.data.model.dto.television.details.CreatedBy
import com.themovieguide.data.model.dto.television.details.Genre
import com.themovieguide.data.model.dto.television.details.LastEpisodeToAir
import com.themovieguide.data.model.dto.television.details.Network
import com.themovieguide.data.model.dto.television.details.NextEpisodeToAir
import com.themovieguide.data.model.dto.television.details.ProductionCompany
import com.themovieguide.data.model.dto.television.details.ProductionCountry
import com.themovieguide.data.model.dto.television.details.Season
import com.themovieguide.data.model.dto.television.details.SpokenLanguage
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsTelevisionMeta(
    val adult: Boolean? = false,
    val backdrop_path: String? = EMPTY,
    val created_by: List<CreatedBy>? = emptyList(),
    val episode_run_time: List<String>? = emptyList(),
    val first_air_date: String? = EMPTY,
    val genres: List<Genre>? = emptyList(),
    val homepage: String? = EMPTY,
    val id: Int? = 0,
    val in_production: Boolean? = false,
    val languages: List<String>? = emptyList(),
    val last_air_date: String? = EMPTY,
    val last_episode_to_air: LastEpisodeToAir? = LastEpisodeToAir(),
    val name: String? = EMPTY,
    val networks: List<Network>? = emptyList(),
    val next_episode_to_air: NextEpisodeToAir? = NextEpisodeToAir(),
    val number_of_episodes: Int? = 0,
    val number_of_seasons: Int? = 0,
    val origin_country: List<String>? = emptyList(),
    val original_language: String? = EMPTY,
    val original_name: String? = EMPTY,
    val overview: String? = EMPTY,
    val popularity: Double? = 0.0,
    val poster_path: String? = EMPTY,
    val production_companies: List<ProductionCompany>? = emptyList(),
    val production_countries: List<ProductionCountry>? = emptyList(),
    val seasons: List<Season>? = emptyList(),
    val spoken_languages: List<SpokenLanguage>? = emptyList(),
    val status: String? = EMPTY,
    val tagline: String? = EMPTY,
    val type: String? = EMPTY,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0,
) : Parcelable {
    constructor() : this(
        adult = false,
        backdrop_path = EMPTY,
        created_by = emptyList(),
        episode_run_time = emptyList(),
        first_air_date = EMPTY,
        genres = emptyList(),
        homepage = EMPTY,
        id = 0,
        in_production = false,
        languages = emptyList(),
        last_air_date = EMPTY,
        last_episode_to_air = LastEpisodeToAir(),
        name = EMPTY,
        networks = emptyList(),
        next_episode_to_air = NextEpisodeToAir(),
        number_of_episodes = 0,
        number_of_seasons = 0,
        origin_country = emptyList(),
        original_language = EMPTY,
        original_name = EMPTY,
        overview = EMPTY,
        popularity = 0.0,
        poster_path = EMPTY,
        production_companies = emptyList(),
        production_countries = emptyList(),
        seasons = emptyList(),
        spoken_languages = emptyList(),
        status = EMPTY,
        tagline = EMPTY,
        type = EMPTY,
        vote_average = 0.0,
        vote_count = 0,
    )
}

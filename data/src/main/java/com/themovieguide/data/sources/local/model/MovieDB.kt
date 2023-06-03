package com.themovieguide.data.sources.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.themovieguide.data.utils.EMPTY

@Entity(tableName = MovieDB.TABLE_NAME_MOVIE)
data class MovieDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val adult: Boolean? = false,
    val backdropPath: String? = EMPTY,
    val belongsCollection: String? = EMPTY,
    val budget: Int? = 0,
    val genres: String? = EMPTY,
    val homepage: String? = EMPTY,
    val movieKey: Int? = 0,
    val imdbId: String? = EMPTY,
    val originalLang: String? = EMPTY,
    val originalTitle: String? = EMPTY,
    val overview: String? = EMPTY,
    val popularity: Double? = 0.0,
    val posterPath: String? = EMPTY,
    val prodCompanies: String? = EMPTY,
    val prodCountries: String? = EMPTY,
    val releaseDate: String? = EMPTY,
    val revenue: Int? = 0,
    val runtime: Int? = 0,
    val spokenLang: String? = EMPTY,
    val status: String? = EMPTY,
    val tagline: String? = EMPTY,
    val title: String? = EMPTY,
    val video: String? = EMPTY,
    val voteAverage: Double? = 0.0,
    val voteCount: Int? = 0,
    val visited: Long? = 0,
) {
    constructor() : this(
        id = 0,
        adult = false,
        backdropPath = EMPTY,
        belongsCollection = EMPTY,
        budget = 0,
        genres = EMPTY,
        homepage = EMPTY,
        movieKey = 0,
        imdbId = EMPTY,
        originalLang = EMPTY,
        originalTitle = EMPTY,
        overview = EMPTY,
        popularity = 0.0,
        posterPath = EMPTY,
        prodCompanies = EMPTY,
        prodCountries = EMPTY,
        releaseDate = EMPTY,
        revenue = 0,
        runtime = 0,
        spokenLang = EMPTY,
        status = EMPTY,
        tagline = EMPTY,
        title = EMPTY,
        video = EMPTY,
        voteAverage = 0.0,
        voteCount = 0,
        visited = System.currentTimeMillis(),
    )
    companion object {
        const val TABLE_NAME_MOVIE = "movies"
    }
}

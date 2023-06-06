package com.themovieguide.data.sources.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.themovieguide.data.utils.EMPTY

@Entity(tableName = TrendingDB.TABLE_NAME_MOVIE)
data class TrendingDB(
    @PrimaryKey(autoGenerate = true)
    val dbId: Int = 0,
    val backdropPath: String? = EMPTY,
    val firstAirDate: String? = EMPTY,
    val genreIds: String? = EMPTY,
    val id: Int? = 0,
    val name: String? = EMPTY,
    val originCountry: String? = EMPTY,
    val originalLanguage: String? = EMPTY,
    val originalName: String? = EMPTY,
    val overview: String? = EMPTY,
    val popularity: Double? = 0.0,
    val posterPath: String? = EMPTY,
    val voteAverage: Double? = 0.0,
    val voteCount: Int? = 0,
) {
    constructor() : this(
        dbId = 0,
        backdropPath = EMPTY,
        firstAirDate = EMPTY,
        genreIds = EMPTY,
        id = 0,
        name = EMPTY,
        originCountry = EMPTY,
        originalLanguage = EMPTY,
        originalName = EMPTY,
        overview = EMPTY,
        popularity = 0.0,
        posterPath = EMPTY,
        voteAverage = 0.0,
        voteCount = 0,
    )
    companion object {
        const val TABLE_NAME_MOVIE = "trending_tv"
    }
}

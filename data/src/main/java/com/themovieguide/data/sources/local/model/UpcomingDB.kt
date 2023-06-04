package com.themovieguide.data.sources.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.themovieguide.domain.utils.EMPTY

@Entity(tableName = UpcomingDB.TABLE_NAME_MOVIE)
data class UpcomingDB(
    @PrimaryKey(autoGenerate = true)
    val dbId: Int = 0,
    var movieKey: Int = 0,
    val adult: Boolean? = false,
    val backdropPath: String? = EMPTY,
    val genreIds: String? = EMPTY,
    val id: Int? = 0,
    val originalLanguage: String? = EMPTY,
    val originalTitle: String? = EMPTY,
    val overview: String? = EMPTY,
    val popularity: Double? = 0.0,
    val posterPath: String? = EMPTY,
    val releaseDate: String? = EMPTY,
    val title: String? = EMPTY,
    val video: Boolean? = false,
    val voteAverage: Double? = 0.0,
    val voteCount: Int? = 0,
) {
    constructor() : this(
        dbId = 0,
        movieKey = 0,
        adult = false,
        backdropPath = EMPTY,
        genreIds = EMPTY,
        id = 0,
        originalLanguage = EMPTY,
        originalTitle = EMPTY,
        overview = EMPTY,
        popularity = 0.0,
        posterPath = EMPTY,
        releaseDate = EMPTY,
        title = EMPTY,
        video = false,
        voteAverage = 0.0,
        voteCount = 0,
    )

    companion object {
        const val TABLE_NAME_MOVIE = "upcoming"
    }
}

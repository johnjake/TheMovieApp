package com.themovieguide.org.features.mapper

import com.themovieguide.data.model.dto.ParcelizeMovies
import com.themovieguide.domain.model.Movies

fun ParcelizeMovies.toMovieEntity(): Movies {
    return Movies(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
    )
}

fun List<ParcelizeMovies>.toMovieList(): List<Movies> {
    return this.map { movie ->
        movie.toMovieEntity()
    }
}

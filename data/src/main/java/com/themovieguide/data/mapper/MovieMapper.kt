package com.themovieguide.data.mapper

import com.themovieguide.data.model.dto.Movie
import com.themovieguide.domain.model.Movies

fun Movie.toMovieEntity(): Movies {
    return Movies(
        adult = this.adult,
        backdropPath = this.backdrop_path,
        genreIds = this.genre_ids,
        id = this.id,
        originalLanguage = this.original_language,
        originalTitle = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.poster_path,
        releaseDate = this.release_date,
        title = this.title,
        video = this.video,
        voteAverage = this.vote_average,
        voteCount = this.vote_count,
    )
}

fun List<Movie>.toMovieList(): List<Movies> {
    return this.map { movie ->
        movie.toMovieEntity()
    }
}

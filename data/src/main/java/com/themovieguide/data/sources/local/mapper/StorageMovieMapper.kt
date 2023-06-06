package com.themovieguide.data.sources.local.mapper

import com.themovieguide.data.sources.local.model.MovieDB
import com.themovieguide.data.sources.local.model.SearchDB
import com.themovieguide.data.sources.local.model.TheaterDB
import com.themovieguide.data.sources.local.model.TopRatedDB
import com.themovieguide.data.sources.local.model.UpcomingDB
import com.themovieguide.data.utils.castToClass
import com.themovieguide.data.utils.castToJson
import com.themovieguide.data.utils.castToList
import com.themovieguide.domain.model.BelongsCollectionEntity
import com.themovieguide.domain.model.GenreEntity
import com.themovieguide.domain.model.LanguageEntity
import com.themovieguide.domain.model.Movie
import com.themovieguide.domain.model.MovieEntity
import com.themovieguide.domain.model.Movies
import com.themovieguide.domain.model.ProductionCompanyEntity
import com.themovieguide.domain.model.ProductionCountryEntity
import com.themovieguide.domain.model.VideosEntity

fun Movie.castToMovieDB(): MovieDB {
    return MovieDB(
        adult = this.adult,
        backdropPath = this.backdropPath,
        belongsCollection = this.belongsCollection.castToJson(),
        budget = this.budget,
        genres = this.genres.castToJson(),
        homepage = this.homepage,
        movieKey = this.id,
        imdbId = this.imdbId,
        originalLang = this.originalLang,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        prodCompanies = this.prodCompanies.castToJson(),
        prodCountries = this.prodCountries.castToJson(),
        releaseDate = this.releaseDate,
        revenue = this.revenue,
        runtime = this.runtime,
        spokenLang = this.spokenLang.castToJson(),
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video.castToJson(),
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        visited = System.currentTimeMillis(),
    )
}
fun MovieDB.toMovieEntity(): MovieEntity {
    val belongsClass = if (this.belongsCollection?.isNotEmpty() == true) this.belongsCollection.castToClass<BelongsCollectionEntity>() else BelongsCollectionEntity()
    val genreList = if (this.genres?.isNotEmpty() == true) this.genres.castToList<GenreEntity>() else emptyList()
    val companyList = if (this.prodCompanies?.isNotEmpty() == true) this.prodCompanies.castToList<ProductionCompanyEntity>() else emptyList()
    val countryList = if (this.prodCountries?.isNotEmpty() == true) this.prodCountries.castToList<ProductionCountryEntity>() else emptyList()
    val languageList = if (this.spokenLang?.isNotEmpty() == true) this.spokenLang.castToList<LanguageEntity>() else emptyList()
    val previewEntity = if (this.video?.isNotEmpty() == true) this.video.castToClass<VideosEntity>() else VideosEntity()
    return MovieEntity(
        adult = this.adult,
        backdropPath = this.backdropPath,
        belongsCollection = belongsClass,
        budget = this.budget,
        genres = genreList,
        homepage = this.homepage,
        id = this.id,
        imdbId = this.imdbId,
        originalLang = this.originalLang,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        prodCompanies = companyList,
        prodCountries = countryList,
        releaseDate = this.releaseDate,
        revenue = this.revenue,
        runtime = this.runtime,
        spokenLang = languageList,
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = previewEntity,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
    )
}

fun Movies.castToTheaterDB(): TheaterDB {
    return TheaterDB(
        movieKey = this.key,
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds.castToJson(),
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

fun Movies.castToTopRatedDB(): TopRatedDB {
    return TopRatedDB(
        movieKey = this.key,
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds.castToJson(),
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

fun Movies.castToSearchDB(): SearchDB {
    return SearchDB(
        movieKey = this.key,
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds.castToJson(),
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

fun Movies.castToUpcomingDB(): UpcomingDB {
    return UpcomingDB(
        movieKey = this.key,
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds.castToJson(),
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

fun TopRatedDB.castToTopRated(): Movies {
    val genres = if (this.genreIds?.isNotEmpty() == true) this.genreIds.castToList<Int>() else emptyList()
    return Movies(
        key = this.movieKey,
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = genres,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.originalTitle,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
    )
}

fun SearchDB.castToSearch(): Movies {
    val genres = if (this.genreIds?.isNotEmpty() == true) this.genreIds.castToList<Int>() else emptyList()
    return Movies(
        key = this.movieKey,
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = genres,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.originalTitle,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
    )
}

fun UpcomingDB.castToUpcoming(): Movies {
    val genres = if (this.genreIds?.isNotEmpty() == true) this.genreIds.castToList<Int>() else emptyList()
    return Movies(
        key = this.movieKey,
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = genres,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.originalTitle,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
    )
}

fun TheaterDB.castToMovies(): Movies {
    val genres = if (this.genreIds?.isNotEmpty() == true) this.genreIds.castToList<Int>() else emptyList()
    return Movies(
        key = this.movieKey,
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = genres,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.originalTitle,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
    )
}

private fun MovieDB.castToMovies(): Movies {
    return Movies(
        key = this.movieKey ?: 0,
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genres?.castToList(),
        id = this.id,
        originalLanguage = this.originalLang,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = false,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
    )
}

fun List<MovieDB>.castToListMovies(): List<Movies> {
    return this.map {
        it.castToMovies()
    }
}

fun List<TheaterDB>.castToMovieList(): List<Movies> {
    return this.map { db ->
        db.castToMovies()
    }
}

fun List<TopRatedDB>.castToTopRatedList(): List<Movies> {
    return this.map { db ->
        db.castToTopRated()
    }
}

fun List<SearchDB>.castToSearchList(): List<Movies> {
    return this.map { db ->
        db.castToSearch()
    }
}

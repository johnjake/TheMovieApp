package com.themovieguide.data.sources.local.mapper

import com.themovieguide.data.sources.local.model.MovieDB
import com.themovieguide.data.sources.local.model.TheaterDB
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
import com.themovieguide.domain.utils.EMPTY

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
        key = this.key,
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

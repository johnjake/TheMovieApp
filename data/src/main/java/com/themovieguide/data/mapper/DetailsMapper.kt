package com.themovieguide.data.mapper

import com.themovieguide.data.model.dto.Result
import com.themovieguide.data.model.dto.Videos
import com.themovieguide.data.model.meta.DetailsMeta
import com.themovieguide.data.sources.local.model.MovieDB
import com.themovieguide.data.sources.local.model.SearchDB
import com.themovieguide.data.sources.local.model.TheaterDB
import com.themovieguide.data.sources.local.model.TopRatedDB
import com.themovieguide.data.sources.local.model.UpcomingDB
import com.themovieguide.data.utils.castToJson
import com.themovieguide.data.utils.castToList
import com.themovieguide.domain.model.BelongsCollectionEntity
import com.themovieguide.domain.model.GenreEntity
import com.themovieguide.domain.model.LanguageEntity
import com.themovieguide.domain.model.Movie
import com.themovieguide.domain.model.Movies
import com.themovieguide.domain.model.ProductionCompanyEntity
import com.themovieguide.domain.model.ProductionCountryEntity
import com.themovieguide.domain.model.ResultEntity
import com.themovieguide.domain.model.VideosEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun DetailsMeta.toMovieEntity(): Movie {
    val genString = this.genres.castToJson()
    val prodComp = this.production_companies.castToJson()
    val genreList = genString.castToList<GenreEntity>()
    val prodCompanies = prodComp.castToList<ProductionCompanyEntity>()
    val prodCounty = this.production_countries.castToJson()
    val productCountries = prodCounty.castToList<ProductionCountryEntity>()
    val spokeLanguages = this.spoken_languages.castToJson()
    val listLang = spokeLanguages.castToList<LanguageEntity>()
    val belongs = this.belongs_to_collection

    return Movie(
        adult = this.adult,
        backdropPath = this.backdrop_path,
        belongsCollection = BelongsCollectionEntity(
            backdrop_path = belongs?.backdrop_path,
            id = belongs?.id,
            name = belongs?.name,
            poster_path = belongs?.poster_path,
        ),
        budget = this.budget,
        genres = genreList,
        homepage = this.homepage,
        id = this.id,
        imdbId = this.imdb_id,
        originalLang = this.original_language,
        originalTitle = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.poster_path,
        prodCompanies = prodCompanies,
        prodCountries = productCountries,
        releaseDate = this.release_date,
        revenue = this.revenue,
        runtime = this.runtime,
        spokenLang = listLang,
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = VideosEntity(
            results = this.videos?.toVideoList(),
        ),
        voteAverage = 0.0,
        voteCount = 0,
    )
}

fun Result.toEntity(): ResultEntity {
    return ResultEntity(
        id = this.id,
        iso_3166_1 = this.iso_3166_1,
        iso_639_1 = this.iso_639_1,
        key = this.key,
        name = this.name,
        official = this.official,
        published_at = this.published_at,
        site = this.site,
        size = this.size,
        type = this.type,
    )
}

fun Videos.toVideoList(): List<ResultEntity> {
    return this.results?.map {
        it.toEntity()
    } ?: emptyList()
}

fun Flow<List<MovieDB>>.toDomainFlowList(): Flow<List<Movies>> {
    return this.map { list ->
        list.map { db ->
            Movies(
                key = db.id,
                adult = db.adult,
                backdropPath = db.backdropPath,
                genreIds = emptyList(),
                id = db.movieKey,
                originalLanguage = db.originalTitle,
                originalTitle = db.originalTitle,
                overview = db.overview,
                popularity = db.popularity,
                posterPath = db.posterPath,
                releaseDate = db.visited.toString(),
                title = db.title,
                video = false,
                voteAverage = db.voteAverage,
                voteCount = db.voteCount,
            )
        }
    }
}

fun Flow<List<TheaterDB>>.toTheaterFlowLists(): Flow<List<Movies>> {
    return this.map { list ->
        list.map { db ->
            Movies(
                key = db.movieKey,
                adult = db.adult,
                backdropPath = db.backdropPath,
                genreIds = emptyList(),
                id = db.id,
                originalLanguage = db.originalTitle,
                originalTitle = db.originalTitle,
                overview = db.overview,
                popularity = db.popularity,
                posterPath = db.posterPath,
                releaseDate = db.releaseDate,
                title = db.title,
                video = false,
                voteAverage = db.voteAverage,
                voteCount = db.voteCount,
            )
        }
    }
}

fun Flow<List<SearchDB>>.castSearchFlowLists(): Flow<List<Movies>> {
    return this.map { list ->
        list.map { db ->
            Movies(
                key = db.movieKey,
                adult = db.adult,
                backdropPath = db.backdropPath,
                genreIds = emptyList(),
                id = db.id,
                originalLanguage = db.originalTitle,
                originalTitle = db.originalTitle,
                overview = db.overview,
                popularity = db.popularity,
                posterPath = db.posterPath,
                releaseDate = db.releaseDate,
                title = db.title,
                video = false,
                voteAverage = db.voteAverage,
                voteCount = db.voteCount,
            )
        }
    }
}

fun Flow<List<TopRatedDB>>.castTopRatedFlowLists(): Flow<List<Movies>> {
    return this.map { list ->
        list.map { db ->
            Movies(
                key = db.movieKey,
                adult = db.adult,
                backdropPath = db.backdropPath,
                genreIds = emptyList(),
                id = db.id,
                originalLanguage = db.originalTitle,
                originalTitle = db.originalTitle,
                overview = db.overview,
                popularity = db.popularity,
                posterPath = db.posterPath,
                releaseDate = db.releaseDate,
                title = db.title,
                video = false,
                voteAverage = db.voteAverage,
                voteCount = db.voteCount,
            )
        }
    }
}

fun Flow<List<UpcomingDB>>.castUpcomingFlowLists(): Flow<List<Movies>> {
    return this.map { list ->
        list.map { db ->
            Movies(
                key = db.movieKey,
                adult = db.adult,
                backdropPath = db.backdropPath,
                genreIds = emptyList(),
                id = db.id,
                originalLanguage = db.originalTitle,
                originalTitle = db.originalTitle,
                overview = db.overview,
                popularity = db.popularity,
                posterPath = db.posterPath,
                releaseDate = db.releaseDate,
                title = db.title,
                video = false,
                voteAverage = db.voteAverage,
                voteCount = db.voteCount,
            )
        }
    }
}

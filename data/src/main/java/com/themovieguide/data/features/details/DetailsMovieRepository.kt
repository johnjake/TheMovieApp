package com.themovieguide.data.features.details

import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.local.mapper.castToListMovies
import com.themovieguide.data.sources.local.mapper.castToMovieDB
import com.themovieguide.domain.features.showing.LocalRepository
import com.themovieguide.domain.model.Movie
import com.themovieguide.domain.states.showing.StateMoviesDB
import com.themovieguide.domain.utils.EMPTY
import javax.inject.Inject

class DetailsMovieRepository @Inject constructor(private val db: AppDatabase) : LocalRepository {
    override fun insertMovie(movie: Movie) {
        val movieDB = movie.castToMovieDB()
        db.movieDao().insertMovie(movieDB)
    }

    override suspend fun getMovies(): StateMoviesDB {
        return try {
            val response = db.movieDao().getMovies()
            StateMoviesDB.OnSuccess(data = response.castToListMovies())
        } catch (ex: Exception) {
            StateMoviesDB.OnFailure(error = ex.message ?: EMPTY)
        }
    }
}

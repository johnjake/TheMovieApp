package com.themovieguide.data.module
import com.themovieguide.data.features.cast.CastImpl
import com.themovieguide.data.features.details.DbMovieRepository
import com.themovieguide.data.features.details.MovieDetailsImpl
import com.themovieguide.data.features.search.SearchImpl
import com.themovieguide.data.features.showing.ShowingImpl
import com.themovieguide.data.features.theater.TheaterImpl
import com.themovieguide.data.features.toprated.TopRatedImpl
import com.themovieguide.data.features.upcoming.UpcomingImpl
import com.themovieguide.data.repository.cast.MovieCastCase
import com.themovieguide.data.repository.details.MovieDetailsCase
import com.themovieguide.data.repository.search.SearchCase
import com.themovieguide.data.repository.showing.ShowingCase
import com.themovieguide.data.repository.theater.InTheaterCase
import com.themovieguide.data.repository.toprated.TopRatedCase
import com.themovieguide.data.repository.upcoming.UpcomingCase
import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.domain.features.cast.CastRepository
import com.themovieguide.domain.features.cast.MovieCast
import com.themovieguide.domain.features.details.Details
import com.themovieguide.domain.features.details.DetailsRepository
import com.themovieguide.domain.features.search.Search
import com.themovieguide.domain.features.search.SearchRepository
import com.themovieguide.domain.features.showing.LocalRepository
import com.themovieguide.domain.features.showing.Showing
import com.themovieguide.domain.features.showing.ShowingRepository
import com.themovieguide.domain.features.theater.Theater
import com.themovieguide.domain.features.theater.TheaterRepository
import com.themovieguide.domain.features.toprated.TopRated
import com.themovieguide.domain.features.toprated.TopRatedRepository
import com.themovieguide.domain.features.upcoming.Upcoming
import com.themovieguide.domain.features.upcoming.UpcomingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)

    @Provides
    fun provideShowing(api: ApiServices): Showing = ShowingImpl(api)

    @Provides
    fun providesShowingRepository(repository: Showing): ShowingRepository = ShowingCase(repository)

    @Provides
    fun provideTopRated(api: ApiServices): TopRated = TopRatedImpl(api)

    @Provides
    fun provideTopRatedCase(repository: TopRated): TopRatedRepository = TopRatedCase(repository)

    @Provides
    fun provideUpcoming(api: ApiServices): Upcoming = UpcomingImpl(api)

    @Provides
    fun provideUpcomingCase(repository: Upcoming): UpcomingRepository = UpcomingCase(repository)

    @Provides
    fun provideInTheater(api: ApiServices): Theater = TheaterImpl(api)

    @Provides
    fun provideInTheaterCase(repository: Theater): TheaterRepository = InTheaterCase(repository)

    @Provides
    fun providesSearchMovie(api: ApiServices): Search = SearchImpl(api)

    @Provides
    fun providesSearchRepository(search: Search): SearchRepository = SearchCase(search)

    @Provides
    fun providesMovieDetails(api: ApiServices): Details = MovieDetailsImpl(api)

    @Provides
    fun providesDetailsRepository(repository: Details): DetailsRepository = MovieDetailsCase(repository)

    @Provides
    fun providesMovieCast(api: ApiServices): MovieCast = CastImpl(api)

    @Provides
    fun providesCastRepository(repository: MovieCast): CastRepository = MovieCastCase(repository)

    @Provides
    fun providesLocalRepository(db: AppDatabase): LocalRepository = DbMovieRepository(db)
}

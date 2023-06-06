package com.themovieguide.data.module
import com.themovieguide.data.features.cast.CastImpl
import com.themovieguide.data.features.details.DetailsMovieRepository
import com.themovieguide.data.features.details.MovieDetailsImpl
import com.themovieguide.data.features.discover.DiscoverTvImpl
import com.themovieguide.data.features.ratedtv.RatedTvImpl
import com.themovieguide.data.features.search.SearchImpl
import com.themovieguide.data.features.searchtv.SearchTelevisionImpl
import com.themovieguide.data.features.showing.ShowingImpl
import com.themovieguide.data.features.theater.TheaterImpl
import com.themovieguide.data.features.todayair.TodayAirStorageImpl
import com.themovieguide.data.features.toprated.TopRatedImpl
import com.themovieguide.data.features.trending.TrendingImpl
import com.themovieguide.data.features.upcoming.UpcomingImpl
import com.themovieguide.data.repository.cast.MovieCastCase
import com.themovieguide.data.repository.details.MovieDetailsCase
import com.themovieguide.data.repository.discovertv.DiscoverTvShowCase
import com.themovieguide.data.repository.ratedtv.RatedTvShowCase
import com.themovieguide.data.repository.search.SearchCase
import com.themovieguide.data.repository.searchtv.SearchTelevisionCase
import com.themovieguide.data.repository.showing.ShowingCase
import com.themovieguide.data.repository.todayair.TodayAirStorageCase
import com.themovieguide.data.repository.toprated.TopRatedCase
import com.themovieguide.data.repository.trending.TrendingTvShowCase
import com.themovieguide.data.repository.upcoming.UpcomingCase
import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.local.repository.discover.DiscoverDBRepository
import com.themovieguide.data.sources.local.repository.ratedtv.RatedTelevisionDBRepository
import com.themovieguide.data.sources.local.repository.search.SearchDBRepository
import com.themovieguide.data.sources.local.repository.searchtv.SearchTvStorageRepository
import com.themovieguide.data.sources.local.repository.theater.InTheaterDBRepository
import com.themovieguide.data.sources.local.repository.todaytv.TodayAirDBRepository
import com.themovieguide.data.sources.local.repository.toprated.TopRatedDBRepository
import com.themovieguide.data.sources.local.repository.trending.TrendingDBRepository
import com.themovieguide.data.sources.local.repository.upcoming.UpcomingDBRepository
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.data.utils.Connectivity
import com.themovieguide.domain.features.cast.CastRepository
import com.themovieguide.domain.features.cast.MovieCast
import com.themovieguide.domain.features.details.Details
import com.themovieguide.domain.features.details.DetailsRepository
import com.themovieguide.domain.features.discover.DiscoverTv
import com.themovieguide.domain.features.discover.DiscoverTvRepository
import com.themovieguide.domain.features.ratedtv.RatedTv
import com.themovieguide.domain.features.ratedtv.RatedTvRepository
import com.themovieguide.domain.features.search.Search
import com.themovieguide.domain.features.search.SearchRepository
import com.themovieguide.domain.features.searchtv.SearchTelevision
import com.themovieguide.domain.features.searchtv.SearchTelevisionRepository
import com.themovieguide.domain.features.showing.LocalRepository
import com.themovieguide.domain.features.showing.Showing
import com.themovieguide.domain.features.showing.ShowingRepository
import com.themovieguide.domain.features.theater.Theater
import com.themovieguide.domain.features.todayair.TodayAirStorage
import com.themovieguide.domain.features.todayair.TodayAirStorageRepository
import com.themovieguide.domain.features.toprated.TopRated
import com.themovieguide.domain.features.toprated.TopRatedRepository
import com.themovieguide.domain.features.trending.TrendingTv
import com.themovieguide.domain.features.trending.TrendingTvRepository
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
    fun provideShowing(api: ApiServices, storage: InTheaterDBRepository, signal: Connectivity): Showing = ShowingImpl(api, storage, signal)

    @Provides
    fun providesShowingRepository(repository: Showing, db: InTheaterDBRepository): ShowingRepository = ShowingCase(repository, db)

    @Provides
    fun provideTopRated(api: ApiServices, db: TopRatedDBRepository, signal: Connectivity): TopRated = TopRatedImpl(api, db, signal)

    @Provides
    fun provideTopRatedCase(repository: TopRated, db: TopRatedDBRepository): TopRatedRepository = TopRatedCase(repository, db)

    @Provides
    fun provideUpcoming(api: ApiServices, storage: UpcomingDBRepository, signal: Connectivity): Upcoming = UpcomingImpl(api, storage, signal)

    @Provides
    fun provideUpcomingCase(repository: Upcoming, storage: UpcomingDBRepository): UpcomingRepository = UpcomingCase(repository, storage)

    @Provides
    fun provideInTheater(api: ApiServices, db: InTheaterDBRepository): Theater = TheaterImpl(api, db)

    @Provides
    fun providesSearchMovie(api: ApiServices, storage: SearchDBRepository, signal: Connectivity): Search = SearchImpl(api, storage, signal)

    @Provides
    fun providesSearchRepository(search: Search, storage: SearchDBRepository): SearchRepository = SearchCase(search, storage)

    @Provides
    fun providesMovieDetails(api: ApiServices): Details = MovieDetailsImpl(api)

    @Provides
    fun providesDetailsRepository(repository: Details): DetailsRepository = MovieDetailsCase(repository)

    @Provides
    fun providesMovieCast(api: ApiServices): MovieCast = CastImpl(api)

    @Provides
    fun providesCastRepository(repository: MovieCast): CastRepository = MovieCastCase(repository)

    @Provides
    fun providesLocalRepository(db: AppDatabase): LocalRepository = DetailsMovieRepository(db)

    /** provide instance of television **/

    @Provides
    fun provideRatedTv(
        remote: ApiServices,
        storage: RatedTelevisionDBRepository,
        signal: Connectivity,
    ): RatedTv = RatedTvImpl(
        api = remote,
        storage = storage,
        signal = signal,
    )

    @Provides
    fun provideRatedRepository(
        television: RatedTv,
        storage: RatedTelevisionDBRepository,
    ): RatedTvRepository = RatedTvShowCase(
        television = television,
        storage = storage,
    )

    @Provides
    fun provideTvStorageSearch(
        remote: ApiServices,
        storage: SearchTvStorageRepository,
        signal: Connectivity,
    ): SearchTelevision = SearchTelevisionImpl(
        api = remote,
        storage = storage,
        signal = signal,
    )

    @Provides
    fun provideSearchTvRepository(
        repository: SearchTelevision,
        storage: SearchTvStorageRepository,
    ): SearchTelevisionRepository = SearchTelevisionCase(
        repository = repository,
        storage = storage,
    )

    @Provides
    fun provideTodayAirStorage(
        api: ApiServices,
        storage: TodayAirDBRepository,
        signal: Connectivity,
    ): TodayAirStorage = TodayAirStorageImpl(
        api = api,
        storage = storage,
        signal = signal,
    )

    @Provides
    fun providesTodayAirRepository(
        repository: TodayAirStorage,
        storage: TodayAirDBRepository,
    ): TodayAirStorageRepository = TodayAirStorageCase(
        repository = repository,
        storage = storage,
    )

    @Provides
    fun providesDiscoverTelevision(
        api: ApiServices,
        storage: DiscoverDBRepository,
        signal: Connectivity,
    ): DiscoverTv = DiscoverTvImpl(
        api = api,
        storage = storage,
        signal = signal,
    )

    @Provides
    fun provideDiscoverTvRepository(
        television: DiscoverTv,
        storage: DiscoverDBRepository,
    ): DiscoverTvRepository = DiscoverTvShowCase(
        television = television,
        storage = storage,
    )

    @Provides
    fun providesTrendingTelevision(
        api: ApiServices,
        storage: TrendingDBRepository,
        signal: Connectivity,
    ): TrendingTv = TrendingImpl(
        api = api,
        storage = storage,
        signal = signal,
    )

    @Provides
    fun provideTrendingTvRepository(
        television: TrendingTv,
        storage: TrendingDBRepository,
    ): TrendingTvRepository = TrendingTvShowCase(
        television = television,
        storage = storage,
    )
}

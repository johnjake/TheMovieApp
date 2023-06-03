package com.themovieguide.data.module

import android.content.Context
import com.themovieguide.data.BuildConfig
import com.themovieguide.data.utils.ChuckerManager
import com.themovieguide.data.utils.CurlLoggerInterceptor
import com.themovieguide.data.utils.json
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        curlLoggerInterceptor: CurlLoggerInterceptor,
        chuckerManager: ChuckerManager,
    ) =
        OkHttpClient.Builder().apply {
            connectTimeout(120, TimeUnit.SECONDS)
            readTimeout(120, TimeUnit.SECONDS)
            writeTimeout(120, TimeUnit.SECONDS)
            addInterceptor(chuckerManager.getChuckerInterceptor())
            addInterceptor(loggingInterceptor)
            addInterceptor(curlLoggerInterceptor)
        }.build()

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().apply {
        client(okHttpClient)
        baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(json()),
            )
    }.build()

    @Singleton
    @Provides
    fun provideCurlInterceptor() = CurlLoggerInterceptor()

    @Singleton
    @Provides
    fun provideChuckerManager(
        @ApplicationContext ctx: Context,
    ): ChuckerManager {
        return ChuckerManager(ctx)
    }
}

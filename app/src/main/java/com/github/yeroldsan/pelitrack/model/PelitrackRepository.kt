package com.github.yeroldsan.pelitrack.model

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.themoviedb.org/3/"

interface PelitrackRepository {

    @GET("movie/popular")
    suspend fun getMovies(@Query("api_key") apiKey: String?): Response<MoviesResponse>

    @GET("tv/popular")
    suspend fun getSeries(@Query("api_key") apiKey: String?): Response<SeriesResponse>

    companion object {
        // Using Lazy delegate for thread-safe lazy initialization. Otherwise it could lead to race conditions
        private val retrofitInstance: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        fun create(): PelitrackRepository {
            return retrofitInstance.create(PelitrackRepository::class.java)
        }
    }
}

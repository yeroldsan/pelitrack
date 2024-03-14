package com.github.yeroldsan.pelitrack.model

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val  BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "your_api_key" // TODO: Replace with your own API key

interface PelitrackRepository {

    @GET("movie/popular?api_key=$API_KEY")
    suspend fun getMovies(): Response<PelitrackData>

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

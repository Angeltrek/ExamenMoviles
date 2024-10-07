package com.feature.examenmoviles.data.network

import com.feature.examenmoviles.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModuleDI {
    private val okHttpClient: OkHttpClient = OkHttpClient()
    private val gsonFactory: GsonConverterFactory = GsonConverterFactory.create()

    operator fun invoke(): CharacterAPIService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
            .create(CharacterAPIService::class.java)
    }
}
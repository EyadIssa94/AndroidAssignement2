package com.example.androidassignment2.model.remote

import com.example.androidassignment2.common.BASE_URL
import com.example.androidassignment2.common.ROCK_END_POINT
import com.example.androidassignment2.model.apiResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RockTracksService {
    @GET(ROCK_END_POINT)
    fun getRockTracks(): Call<apiResponse>

    companion object {
        fun initRetrofit(): RockTracksService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RockTracksService::class.java)
        }
    }
}
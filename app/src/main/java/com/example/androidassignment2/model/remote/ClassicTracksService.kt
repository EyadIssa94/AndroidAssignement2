package com.example.androidassignment2.model.remote

import com.example.androidassignment2.common.BASE_URL
import com.example.androidassignment2.common.CLASSIC_END_POINT
import com.example.androidassignment2.model.apiResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ClassicTracksService {
    @GET(CLASSIC_END_POINT)
    fun getClassicTracks(): Call<apiResponse>

    companion object {
        fun initRetrofit(): ClassicTracksService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ClassicTracksService::class.java)
        }
    }
}
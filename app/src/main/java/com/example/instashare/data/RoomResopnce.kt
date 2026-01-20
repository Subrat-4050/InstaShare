package com.example.instashare.data

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.POST
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.Call


data class RoomResponse(
    val code: String,
    val message: String
)

interface ApiService {
    @FormUrlEncoded
    @POST("create-room")
    fun saveRoom(
        @Field("code") roomCode: String,
        @Field("message") message: String
    ): Call<String>
}

object RetrofitClient{
    private const val BASE_URL = "http://192.168.1.48:5000/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }

}
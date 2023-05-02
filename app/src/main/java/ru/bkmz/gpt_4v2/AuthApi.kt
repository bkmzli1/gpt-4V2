package ru.bkmz.gpt_4

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/login")
    fun login(@Body user: User): Call<LoginResponse>
}
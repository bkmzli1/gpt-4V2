package ru.bkmz.gpt_4

data class LoginResponse(
    val user: User,
    val accessToken: String
)
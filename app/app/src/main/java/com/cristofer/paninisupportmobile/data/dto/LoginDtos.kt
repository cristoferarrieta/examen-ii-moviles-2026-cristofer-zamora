package com.cristofer.paninisupportmobile.data.dto

data class LoginRequestDto(
    val email: String,
    val password: String
)

data class LoginResponseDto(
    val token: String,
    val userEmail: String
)

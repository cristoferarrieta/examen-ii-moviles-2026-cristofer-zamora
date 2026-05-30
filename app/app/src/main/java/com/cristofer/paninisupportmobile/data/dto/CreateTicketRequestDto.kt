package com.cristofer.paninisupportmobile.data.dto

data class CreateTicketRequestDto(
    val title: String,
    val description: String,
    val providerName: String,
    val category: String,
    val priority: String
)

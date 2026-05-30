package com.cristofer.paninisupportmobile.data.dto

data class TicketDto(
    val id: String,
    val title: String,
    val description: String,
    val providerName: String,
    val category: String,
    val priority: String,
    val status: String,
    val createdDate: String
)

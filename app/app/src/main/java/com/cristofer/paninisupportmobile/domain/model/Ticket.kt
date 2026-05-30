package com.cristofer.paninisupportmobile.domain.model

data class Ticket(
    val id: String,
    val title: String,
    val description: String,
    val providerName: String,
    val category: TicketCategory,
    val priority: TicketPriority,
    val status: TicketStatus,
    val createdDate: String
)

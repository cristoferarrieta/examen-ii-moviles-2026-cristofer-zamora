package com.cristofer.paninisupportmobile.domain.model

enum class TicketPriority(val displayName: String, val sortOrder: Int) {
    HIGH("Alta", 0),
    MEDIUM("Media", 1),
    LOW("Baja", 2)
}

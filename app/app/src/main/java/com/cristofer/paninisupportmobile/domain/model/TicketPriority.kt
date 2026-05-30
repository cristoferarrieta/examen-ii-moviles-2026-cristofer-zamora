package com.cristofer.paninisupportmobile.domain.model

enum class TicketPriority(val displayName: String, val sortOrder: Int) {
    HIGH("High", 0),
    MEDIUM("Medium", 1),
    LOW("Low", 2)
}

package com.cristofer.paninisupportmobile.domain.model

enum class TicketStatus(val displayName: String) {
    OPEN("Abierto"),
    IN_PROGRESS("En proceso"),
    RESOLVED("Resuelto"),
    CLOSED("Cerrado")
}

package com.cristofer.paninisupportmobile.domain.model

enum class TicketCategory(val displayName: String) {
    INVENTORY("Inventory"),
    DISTRIBUTION("Distribution"),
    MISSING_PACKAGES("Missing Packages"),
    DAMAGED_PRODUCT("Damaged Product"),
    LOGISTICS("Logistics"),
    PROVIDER_SUPPORT("Provider Support")
}

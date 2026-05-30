package com.cristofer.paninisupportmobile.domain.model

enum class TicketCategory(val displayName: String) {
    INVENTORY("Inventario"),
    DISTRIBUTION("Distribución"),
    MISSING_PACKAGES("Faltante de paquetes"),
    DAMAGED_PRODUCT("Producto dañado"),
    LOGISTICS("Logística"),
    PROVIDER_SUPPORT("Soporte a proveedor")
}

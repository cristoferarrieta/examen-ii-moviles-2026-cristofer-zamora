package com.cristofer.paninisupportmobile.data.repository

import com.cristofer.paninisupportmobile.domain.model.Ticket
import com.cristofer.paninisupportmobile.domain.model.TicketCategory
import com.cristofer.paninisupportmobile.domain.model.TicketPriority
import com.cristofer.paninisupportmobile.domain.model.TicketStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class MockTicketRepository : TicketRepository {
    private val _tickets = MutableStateFlow(mockTickets)
    override val tickets: StateFlow<List<Ticket>> = _tickets.asStateFlow()

    override fun getTicket(ticketId: String): Ticket? {
        return _tickets.value.firstOrNull { it.id == ticketId }
    }

    override fun createTicket(
        title: String,
        description: String,
        providerName: String,
        category: TicketCategory,
        priority: TicketPriority
    ) {
        val ticket = Ticket(
            id = UUID.randomUUID().toString(),
            title = title.trim(),
            description = description.trim(),
            providerName = providerName.trim(),
            category = category,
            priority = priority,
            status = TicketStatus.OPEN,
            createdDate = today()
        )
        _tickets.value = _tickets.value + ticket
    }

    override fun updateStatus(ticketId: String, status: TicketStatus) {
        _tickets.value = _tickets.value.map { ticket ->
            if (ticket.id == ticketId) ticket.copy(status = status) else ticket
        }
    }

    override fun updatePriority(ticketId: String, priority: TicketPriority) {
        _tickets.value = _tickets.value.map { ticket ->
            if (ticket.id == ticketId) ticket.copy(priority = priority) else ticket
        }
    }

    private fun today(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
    }

    private companion object {
        val mockTickets = listOf(
            Ticket(
                id = "ticket-1",
                title = "Missing sticker package batch at San Jose distributor",
                description = "The distributor reported that a confirmed batch of FIFA 2026 sticker packages did not arrive with the morning shipment.",
                providerName = "Distribuidora Central CR",
                category = TicketCategory.MISSING_PACKAGES,
                priority = TicketPriority.HIGH,
                status = TicketStatus.OPEN,
                createdDate = "2026-05-24"
            ),
            Ticket(
                id = "ticket-2",
                title = "Delayed delivery to Heredia point of sale",
                description = "The weekly album replenishment route is delayed and the Heredia point of sale may run out before the weekend.",
                providerName = "Punto Venta Heredia",
                category = TicketCategory.DISTRIBUTION,
                priority = TicketPriority.MEDIUM,
                status = TicketStatus.IN_PROGRESS,
                createdDate = "2026-05-25"
            ),
            Ticket(
                id = "ticket-3",
                title = "Damaged FIFA 2026 album boxes received",
                description = "Several album boxes arrived with visible packaging damage and cannot be placed on shelves.",
                providerName = "Logistica Mundial 2026",
                category = TicketCategory.DAMAGED_PRODUCT,
                priority = TicketPriority.HIGH,
                status = TicketStatus.OPEN,
                createdDate = "2026-05-26"
            ),
            Ticket(
                id = "ticket-4",
                title = "Inventory mismatch in Alajuela warehouse",
                description = "Warehouse count differs from the dispatch manifest for starter packs and sticker multipacks.",
                providerName = "Almacen Alajuela",
                category = TicketCategory.INVENTORY,
                priority = TicketPriority.MEDIUM,
                status = TicketStatus.RESOLVED,
                createdDate = "2026-05-27"
            ),
            Ticket(
                id = "ticket-5",
                title = "Provider did not confirm dispatch route",
                description = "The northern provider has not confirmed the planned dispatch route for the next restock window.",
                providerName = "Proveedor Norte",
                category = TicketCategory.LOGISTICS,
                priority = TicketPriority.LOW,
                status = TicketStatus.OPEN,
                createdDate = "2026-05-28"
            )
        )
    }
}

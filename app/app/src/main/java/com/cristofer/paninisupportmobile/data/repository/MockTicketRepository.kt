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
                title = "Faltante de paquetes de stickers en distribuidor de San José",
                description = "El distribuidor reportó que un lote confirmado de sobres de stickers FIFA 2026 no llegó con la entrega de la mañana.",
                providerName = "Distribuidora Central CR",
                category = TicketCategory.MISSING_PACKAGES,
                priority = TicketPriority.HIGH,
                status = TicketStatus.OPEN,
                createdDate = "2026-05-24"
            ),
            Ticket(
                id = "ticket-2",
                title = "Entrega retrasada hacia punto de venta en Heredia",
                description = "La ruta semanal de reposición de álbumes está retrasada y el punto de venta en Heredia podría quedarse sin inventario antes del fin de semana.",
                providerName = "Punto de Venta Heredia",
                category = TicketCategory.DISTRIBUTION,
                priority = TicketPriority.MEDIUM,
                status = TicketStatus.IN_PROGRESS,
                createdDate = "2026-05-25"
            ),
            Ticket(
                id = "ticket-3",
                title = "Cajas de álbumes FIFA 2026 recibidas con daños",
                description = "Varias cajas de álbumes llegaron con daños visibles en el empaque y no pueden colocarse en exhibición.",
                providerName = "Logística Mundial 2026",
                category = TicketCategory.DAMAGED_PRODUCT,
                priority = TicketPriority.HIGH,
                status = TicketStatus.OPEN,
                createdDate = "2026-05-26"
            ),
            Ticket(
                id = "ticket-4",
                title = "Diferencia de inventario en almacén de Alajuela",
                description = "El conteo del almacén no coincide con el manifiesto de despacho para paquetes iniciales y multipaquetes de stickers.",
                providerName = "Almacén Alajuela",
                category = TicketCategory.INVENTORY,
                priority = TicketPriority.MEDIUM,
                status = TicketStatus.RESOLVED,
                createdDate = "2026-05-27"
            ),
            Ticket(
                id = "ticket-5",
                title = "Proveedor no confirmó la ruta de despacho",
                description = "El proveedor de zona norte no ha confirmado la ruta de despacho planificada para la próxima reposición.",
                providerName = "Proveedor Zona Norte",
                category = TicketCategory.LOGISTICS,
                priority = TicketPriority.LOW,
                status = TicketStatus.OPEN,
                createdDate = "2026-05-28"
            )
        )
    }
}

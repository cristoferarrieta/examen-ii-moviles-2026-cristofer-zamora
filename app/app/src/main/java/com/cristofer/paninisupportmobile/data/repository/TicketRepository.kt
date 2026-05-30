package com.cristofer.paninisupportmobile.data.repository

import com.cristofer.paninisupportmobile.domain.model.Ticket
import com.cristofer.paninisupportmobile.domain.model.TicketCategory
import com.cristofer.paninisupportmobile.domain.model.TicketPriority
import com.cristofer.paninisupportmobile.domain.model.TicketStatus
import kotlinx.coroutines.flow.StateFlow

interface TicketRepository {
    val tickets: StateFlow<List<Ticket>>

    fun getTicket(ticketId: String): Ticket?

    fun createTicket(
        title: String,
        description: String,
        providerName: String,
        category: TicketCategory,
        priority: TicketPriority
    )

    fun updateStatus(ticketId: String, status: TicketStatus)

    fun updatePriority(ticketId: String, priority: TicketPriority)
}

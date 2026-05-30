package com.cristofer.paninisupportmobile.ui.viewmodel

import com.cristofer.paninisupportmobile.core.ui.UiState
import com.cristofer.paninisupportmobile.data.repository.TicketRepository
import com.cristofer.paninisupportmobile.domain.model.Ticket
import com.cristofer.paninisupportmobile.domain.model.TicketPriority
import com.cristofer.paninisupportmobile.domain.model.TicketStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TicketDetailViewModel(
    private val repository: TicketRepository,
    private val ticketId: String
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    val uiState: StateFlow<UiState<Ticket>> = repository.tickets
        .map { tickets ->
            val ticket = tickets.firstOrNull { it.id == ticketId }
            if (ticket == null) {
                UiState.Error("No se encontró el ticket.")
            } else {
                UiState.Success(ticket)
            }
        }
        .stateIn(scope, SharingStarted.Eagerly, UiState.Loading)

    fun updateStatus(status: TicketStatus) {
        repository.updateStatus(ticketId, status)
    }

    fun updatePriority(priority: TicketPriority) {
        repository.updatePriority(ticketId, priority)
    }
}

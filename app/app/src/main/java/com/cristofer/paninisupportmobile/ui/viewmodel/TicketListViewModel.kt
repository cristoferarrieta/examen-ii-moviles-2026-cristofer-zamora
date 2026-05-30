package com.cristofer.paninisupportmobile.ui.viewmodel

import com.cristofer.paninisupportmobile.core.ui.UiState
import com.cristofer.paninisupportmobile.data.repository.TicketRepository
import com.cristofer.paninisupportmobile.domain.model.Ticket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TicketListViewModel(repository: TicketRepository) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    val uiState: StateFlow<UiState<List<Ticket>>> = repository.tickets
        .map { tickets ->
            val orderedTickets = tickets.sortedWith(
                compareBy<Ticket> { it.priority.sortOrder }.thenByDescending { it.createdDate }
            )
            UiState.Success(orderedTickets)
        }
        .stateIn(scope, SharingStarted.Eagerly, UiState.Loading)
}

package com.cristofer.paninisupportmobile.ui.screens.tickets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cristofer.paninisupportmobile.core.featureflags.FeatureFlags
import com.cristofer.paninisupportmobile.core.ui.UiState
import com.cristofer.paninisupportmobile.domain.model.Ticket
import com.cristofer.paninisupportmobile.ui.components.EmptyState
import com.cristofer.paninisupportmobile.ui.components.ErrorState
import com.cristofer.paninisupportmobile.ui.components.LoadingState
import com.cristofer.paninisupportmobile.ui.components.TicketCard
import com.cristofer.paninisupportmobile.ui.viewmodel.TicketListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketListScreen(
    viewModel: TicketListViewModel,
    onTicketClick: (String) -> Unit,
    onOpenSettings: () -> Unit,
    onCreateTicket: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tickets de soporte") },
                actions = {
                    TextButton(onClick = onOpenSettings) {
                        Text("Configuración de prueba")
                    }
                }
            )
        },
        floatingActionButton = {
            if (FeatureFlags.enableTicketCreation) {
                FloatingActionButton(onClick = onCreateTicket) {
                    Text("+", style = MaterialTheme.typography.headlineSmall)
                }
            }
        }
    ) { innerPadding ->
        when (val state = uiState) {
            UiState.Loading -> LoadingState()
            is UiState.Error -> ErrorState(
                message = state.message,
                modifier = Modifier.padding(innerPadding)
            )
            is UiState.Success -> TicketListContent(
                tickets = state.data,
                onTicketClick = onTicketClick,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun TicketListContent(
    tickets: List<Ticket>,
    onTicketClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (tickets.isEmpty()) {
        EmptyState(
            message = "No hay tickets disponibles.",
            modifier = modifier.fillMaxSize()
        )
        return
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(tickets, key = { it.id }) { ticket ->
            TicketCard(
                ticket = ticket,
                onClick = { onTicketClick(ticket.id) }
            )
        }
    }
}

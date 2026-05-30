package com.cristofer.paninisupportmobile.ui.screens.tickets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cristofer.paninisupportmobile.core.featureflags.FeatureFlags
import com.cristofer.paninisupportmobile.core.ui.UiState
import com.cristofer.paninisupportmobile.domain.model.Ticket
import com.cristofer.paninisupportmobile.domain.model.TicketPriority
import com.cristofer.paninisupportmobile.domain.model.TicketStatus
import com.cristofer.paninisupportmobile.ui.components.ErrorState
import com.cristofer.paninisupportmobile.ui.components.LoadingState
import com.cristofer.paninisupportmobile.ui.components.SectionTitle
import com.cristofer.paninisupportmobile.ui.components.TicketPriorityBadge
import com.cristofer.paninisupportmobile.ui.components.TicketStatusBadge
import com.cristofer.paninisupportmobile.ui.viewmodel.TicketDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketDetailScreen(
    viewModel: TicketDetailViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del ticket") },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        when (val state = uiState) {
            UiState.Loading -> LoadingState()
            is UiState.Error -> ErrorState(
                message = state.message,
                modifier = Modifier.padding(innerPadding)
            )
            is UiState.Success -> TicketDetailContent(
                ticket = state.data,
                onStatusSelected = viewModel::updateStatus,
                onPrioritySelected = viewModel::updatePriority,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun TicketDetailContent(
    ticket: Ticket,
    onStatusSelected: (TicketStatus) -> Unit,
    onPrioritySelected: (TicketPriority) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = ticket.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TicketPriorityBadge(priority = ticket.priority)
            TicketStatusBadge(status = ticket.status)
        }
        SectionTitle("Información")
        DetailRow(label = "Proveedor", value = ticket.providerName)
        DetailRow(label = "Categoría", value = ticket.category.displayName)
        DetailRow(label = "Fecha de creación", value = ticket.createdDate)
        DetailRow(label = "Descripción", value = ticket.description)
        SectionTitle("Estado")
        StatusSelector(
            selectedStatus = ticket.status,
            onStatusSelected = onStatusSelected
        )
        if (FeatureFlags.enablePriorityUpdate) {
            SectionTitle("Prioridad")
            PrioritySelector(
                selectedPriority = ticket.priority,
                onPrioritySelected = onPrioritySelected
            )
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun StatusSelector(
    selectedStatus: TicketStatus,
    onStatusSelected: (TicketStatus) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        TicketStatus.entries.forEach { status ->
            val selected = status == selectedStatus
            val buttonModifier = Modifier.fillMaxWidth()
            if (selected) {
                Button(onClick = { onStatusSelected(status) }, modifier = buttonModifier) {
                    Text(status.displayName)
                }
            } else {
                OutlinedButton(onClick = { onStatusSelected(status) }, modifier = buttonModifier) {
                    Text(status.displayName)
                }
            }
        }
    }
}

@Composable
private fun PrioritySelector(
    selectedPriority: TicketPriority,
    onPrioritySelected: (TicketPriority) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedButton(onClick = { expanded = true }) {
            Text(selectedPriority.displayName)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            TicketPriority.entries.forEach { priority ->
                DropdownMenuItem(
                    text = { Text(priority.displayName) },
                    onClick = {
                        expanded = false
                        onPrioritySelected(priority)
                    }
                )
            }
        }
    }
}

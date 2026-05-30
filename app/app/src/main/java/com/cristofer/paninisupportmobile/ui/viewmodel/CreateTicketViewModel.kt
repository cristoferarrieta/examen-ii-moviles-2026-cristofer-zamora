package com.cristofer.paninisupportmobile.ui.viewmodel

import com.cristofer.paninisupportmobile.data.repository.TicketRepository
import com.cristofer.paninisupportmobile.domain.model.TicketCategory
import com.cristofer.paninisupportmobile.domain.model.TicketPriority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class CreateTicketUiState(
    val title: String = "",
    val description: String = "",
    val providerName: String = "",
    val category: TicketCategory = TicketCategory.INVENTORY,
    val priority: TicketPriority = TicketPriority.MEDIUM,
    val errorMessage: String? = null
)

class CreateTicketViewModel(
    private val repository: TicketRepository
) {
    private val _uiState = MutableStateFlow(CreateTicketUiState())
    val uiState: StateFlow<CreateTicketUiState> = _uiState.asStateFlow()

    fun onTitleChanged(value: String) {
        _uiState.value = _uiState.value.copy(title = value, errorMessage = null)
    }

    fun onDescriptionChanged(value: String) {
        _uiState.value = _uiState.value.copy(description = value, errorMessage = null)
    }

    fun onProviderNameChanged(value: String) {
        _uiState.value = _uiState.value.copy(providerName = value, errorMessage = null)
    }

    fun onCategoryChanged(value: TicketCategory) {
        _uiState.value = _uiState.value.copy(category = value)
    }

    fun onPriorityChanged(value: TicketPriority) {
        _uiState.value = _uiState.value.copy(priority = value)
    }

    fun saveTicket(): Boolean {
        val state = _uiState.value
        if (state.title.isBlank() || state.description.isBlank() || state.providerName.isBlank()) {
            _uiState.value = state.copy(errorMessage = "Title, description, and provider are required.")
            return false
        }

        repository.createTicket(
            title = state.title,
            description = state.description,
            providerName = state.providerName,
            category = state.category,
            priority = state.priority
        )
        return true
    }
}

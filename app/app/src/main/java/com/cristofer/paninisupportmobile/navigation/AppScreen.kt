package com.cristofer.paninisupportmobile.navigation

sealed class AppScreen {
    data object Login : AppScreen()
    data object TicketList : AppScreen()
    data class TicketDetail(val ticketId: String) : AppScreen()
    data object CreateTicket : AppScreen()
}

package com.cristofer.paninisupportmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.cristofer.paninisupportmobile.core.featureflags.FeatureFlags
import com.cristofer.paninisupportmobile.data.repository.TicketRepositoryProvider
import com.cristofer.paninisupportmobile.navigation.AppScreen
import com.cristofer.paninisupportmobile.ui.screens.login.LoginScreen
import com.cristofer.paninisupportmobile.ui.screens.settings.FeatureFlagsScreen
import com.cristofer.paninisupportmobile.ui.screens.tickets.CreateTicketScreen
import com.cristofer.paninisupportmobile.ui.screens.tickets.TicketDetailScreen
import com.cristofer.paninisupportmobile.ui.screens.tickets.TicketListScreen
import com.cristofer.paninisupportmobile.ui.theme.PaniniSupportMobileTheme
import com.cristofer.paninisupportmobile.ui.viewmodel.CreateTicketViewModel
import com.cristofer.paninisupportmobile.ui.viewmodel.LoginViewModel
import com.cristofer.paninisupportmobile.ui.viewmodel.TicketDetailViewModel
import com.cristofer.paninisupportmobile.ui.viewmodel.TicketListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaniniSupportMobileTheme {
                PaniniSupportApp()
            }
        }
    }
}

@Composable
private fun PaniniSupportApp() {
    val repository = remember { TicketRepositoryProvider.repository }
    var currentScreen by remember { mutableStateOf<AppScreen>(AppScreen.Login) }

    when (val screen = currentScreen) {
        AppScreen.Login -> {
            val viewModel = remember { LoginViewModel() }
            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = { currentScreen = AppScreen.TicketList }
            )
        }

        AppScreen.TicketList -> {
            val viewModel = remember { TicketListViewModel(repository) }
            TicketListScreen(
                viewModel = viewModel,
                onTicketClick = { currentScreen = AppScreen.TicketDetail(it) },
                onOpenSettings = { currentScreen = AppScreen.FeatureFlags },
                onCreateTicket = {
                    if (FeatureFlags.enableTicketCreation) {
                        currentScreen = AppScreen.CreateTicket
                    }
                }
            )
        }

        is AppScreen.TicketDetail -> {
            BackHandler { currentScreen = AppScreen.TicketList }
            val viewModel = remember(screen.ticketId) {
                TicketDetailViewModel(repository, screen.ticketId)
            }
            TicketDetailScreen(
                viewModel = viewModel,
                onBack = { currentScreen = AppScreen.TicketList }
            )
        }

        AppScreen.CreateTicket -> {
            if (FeatureFlags.enableTicketCreation) {
                BackHandler { currentScreen = AppScreen.TicketList }
                val viewModel = remember { CreateTicketViewModel(repository) }
                CreateTicketScreen(
                    viewModel = viewModel,
                    onBack = { currentScreen = AppScreen.TicketList },
                    onTicketCreated = { currentScreen = AppScreen.TicketList }
                )
            } else {
                LaunchedEffect(Unit) {
                    currentScreen = AppScreen.TicketList
                }
            }
        }

        AppScreen.FeatureFlags -> {
            BackHandler { currentScreen = AppScreen.TicketList }
            FeatureFlagsScreen(
                onBack = { currentScreen = AppScreen.TicketList }
            )
        }
    }
}

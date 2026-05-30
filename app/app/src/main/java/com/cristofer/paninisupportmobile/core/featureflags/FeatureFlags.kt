package com.cristofer.paninisupportmobile.core.featureflags

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object FeatureFlags {
    var enableTicketCreation by mutableStateOf(true)
    var enablePriorityUpdate by mutableStateOf(true)
}

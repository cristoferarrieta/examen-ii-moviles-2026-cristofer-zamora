package com.cristofer.paninisupportmobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cristofer.paninisupportmobile.domain.model.TicketStatus

@Composable
fun TicketStatusBadge(status: TicketStatus, modifier: Modifier = Modifier) {
    val color = when (status) {
        TicketStatus.OPEN -> Color(0xFF005EB8)
        TicketStatus.IN_PROGRESS -> Color(0xFF6750A4)
        TicketStatus.RESOLVED -> Color(0xFF146C2E)
        TicketStatus.CLOSED -> Color(0xFF555555)
    }

    Box(
        modifier = modifier
            .background(color = color, shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = status.displayName,
            color = Color.White,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

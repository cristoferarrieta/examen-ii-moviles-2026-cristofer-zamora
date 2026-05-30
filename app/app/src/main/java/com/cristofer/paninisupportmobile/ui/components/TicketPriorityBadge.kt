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
import com.cristofer.paninisupportmobile.domain.model.TicketPriority

@Composable
fun TicketPriorityBadge(priority: TicketPriority, modifier: Modifier = Modifier) {
    val color = when (priority) {
        TicketPriority.HIGH -> Color(0xFFB3261E)
        TicketPriority.MEDIUM -> Color(0xFF8B5000)
        TicketPriority.LOW -> Color(0xFF146C2E)
    }

    Box(
        modifier = modifier
            .background(color = color, shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = priority.displayName,
            color = Color.White,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

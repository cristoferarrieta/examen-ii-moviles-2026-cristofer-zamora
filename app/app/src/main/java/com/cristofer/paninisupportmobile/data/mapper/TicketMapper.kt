package com.cristofer.paninisupportmobile.data.mapper

import com.cristofer.paninisupportmobile.data.dto.TicketDto
import com.cristofer.paninisupportmobile.domain.model.Ticket
import com.cristofer.paninisupportmobile.domain.model.TicketCategory
import com.cristofer.paninisupportmobile.domain.model.TicketPriority
import com.cristofer.paninisupportmobile.domain.model.TicketStatus

fun TicketDto.toDomain(): Ticket {
    return Ticket(
        id = id,
        title = title,
        description = description,
        providerName = providerName,
        category = enumValueOf(category),
        priority = enumValueOf(priority),
        status = enumValueOf(status),
        createdDate = createdDate
    )
}

fun Ticket.toDto(): TicketDto {
    return TicketDto(
        id = id,
        title = title,
        description = description,
        providerName = providerName,
        category = category.name,
        priority = priority.name,
        status = status.name,
        createdDate = createdDate
    )
}

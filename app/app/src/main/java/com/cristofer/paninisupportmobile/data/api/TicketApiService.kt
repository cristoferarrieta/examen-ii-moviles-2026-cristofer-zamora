package com.cristofer.paninisupportmobile.data.api

import com.cristofer.paninisupportmobile.data.dto.CreateTicketRequestDto
import com.cristofer.paninisupportmobile.data.dto.LoginRequestDto
import com.cristofer.paninisupportmobile.data.dto.LoginResponseDto
import com.cristofer.paninisupportmobile.data.dto.TicketDto
import com.cristofer.paninisupportmobile.data.dto.UpdateTicketPriorityRequestDto
import com.cristofer.paninisupportmobile.data.dto.UpdateTicketStatusRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface TicketApiService {
    @POST("/api/v1/auth/login")
    suspend fun login(@Body request: LoginRequestDto): LoginResponseDto

    @GET("/api/v1/tickets")
    suspend fun getTickets(): List<TicketDto>

    @GET("/api/v1/tickets/{ticketId}")
    suspend fun getTicket(@Path("ticketId") ticketId: String): TicketDto

    @POST("/api/v1/tickets")
    suspend fun createTicket(@Body request: CreateTicketRequestDto): TicketDto

    @PATCH("/api/v1/tickets/{ticketId}/status")
    suspend fun updateTicketStatus(
        @Path("ticketId") ticketId: String,
        @Body request: UpdateTicketStatusRequestDto
    ): TicketDto

    @PATCH("/api/v1/tickets/{ticketId}/priority")
    suspend fun updateTicketPriority(
        @Path("ticketId") ticketId: String,
        @Body request: UpdateTicketPriorityRequestDto
    ): TicketDto
}

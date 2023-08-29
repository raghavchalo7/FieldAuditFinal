package com.chalo.fieldauditapp.model

data class Lists(
    val auditEndBusStopId: Int,
    val auditEndTs: String,
    val auditStartBusStopId: Int,
    val auditStartTs: String,
    val createdDate: String,
    val crewId: Int,
    val fines: List<FineXX>,
    val id: Int,
    val passengerCount: Int,
    val totalFinesCollected: Double,
    val totalFinesCount: Double,
    val totalTicketCount: Int,
    val tripNumber: String,
    val updatedDate: String,
    val waybillNumber: Int
)
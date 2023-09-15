package com.chalo.fieldauditapp.model

data class  Content(
    val auditEndBusStopId: String,
    val auditEndBusStopName: String,
    val auditEndTs: String,
    val auditStartBusStopId: String,
    val auditStartBusStopName: String,
    val auditStartTs: String,
    val busNo: String,
    val createdDate: String,
    val crewId: Int,
    val id: Int,
    val routeId: String,
    val routeName: String,
    val totalFinesCollected: Double,
    val totalFinesCount: Double,
    val totalTicketCount: Int,
    val tripNumber: String,
    val updatedDate: String,
    val waybillNumber: Int
)
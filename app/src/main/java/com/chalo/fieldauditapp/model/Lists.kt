package com.chalo.fieldauditapp.model

data class Lists(
    val auditEndBusStopId: String,
    val auditEndTs: String,
    val auditStartBusStopId: String,
    val auditStartTs: String,
    val bus_no: String,
    val createdDate: String,
    val crewId: Int,
    //val fines: List<FineXX>,
    val id: Int,
    val passengerCount: Int,
    val route_id: String,
    val route_name:String,
    val totalFinesCollected: Double,
    val totalFinesCount: Double,
    val totalTicketCount: Int,
    val tripNumber: String,
    val updatedDate: String,
    val waybillNumber: Int
)
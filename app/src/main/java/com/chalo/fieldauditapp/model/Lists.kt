package com.chalo.fieldauditapp.model

data class Lists(
    val auditEndBusStopId: String,
    val auditEndTs: String,
    val auditStartBusStopId: String,
    val auditStartBusStopName: String,
    val auditStartTs: String,
    val busNo: String,
    val createdDate: String,
    val crewId: Int,
    //val fines: List<FineXX>,
    val id: Int,
    //val passengerCount: Int,
    val routeId: String,
    val routeName:String,
    val totalFinesCollected: Double,
    val totalFinesCount: Double,
    val totalTicketCount: Int,
    val tripNumber: String,
    val updatedDate: String,
    val waybillNumber: Int
)
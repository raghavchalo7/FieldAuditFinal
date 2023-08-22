package com.chalo.fieldauditapp.model

data class AuditReportRequestItem(
    val auditEndBusStopId: Int?=null,
    val auditEndTs: String?=null,
    val auditStartBusStopId: Int?=null,
    val auditStartTs: String?=null,
    val createdDate: String?=null,
    val crewId: Int?=null,
    val id: Int?=null,
    val passengerCount: Int?=null,
    val totalFinesCollected: Double?=null,
    val totalFinesCount: Double?=null,
    val totalTicketCount: Int?=null,
    val tripNumber: String?=null,
    val updatedDate: String?=null,
    val waybillNumber: Int?=null
)
package com.chalo.fieldauditapp.model

data class CreateAuditRequest(
    val audit_end_bus_stop_id: String,
    val audit_end_bus_stop_name: String,
    val audit_end_ts: Long,
    val audit_start_bus_stop_id: String,
    val audit_start_bus_stop_name: String,
    val audit_start_ts: Long,
    val bus_no: String,
    val fines: List<Fine>,
    val passenger_count: Int,
    //val total_fines_collected: Double,
    val route_id: String,
    val route_name:String,
    val total_ticket_count: Int,
    val trip_number: String,
    val waybill_number: Int
)
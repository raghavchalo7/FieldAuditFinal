package com.chalo.fieldauditapp.model

data class CreateAuditRequest(
    val audit_end_bus_stop_id: Int,
    val audit_end_ts: String,
    val audit_start_bus_stop_id: Int,
    val audit_start_ts: String,
    val fines: List<Fine>,
    val passenger_count: Int,
    val total_fines_collected: Double,
    val total_ticket_count: Int,
    val trip_number: String,
    val waybill_number: Int
)
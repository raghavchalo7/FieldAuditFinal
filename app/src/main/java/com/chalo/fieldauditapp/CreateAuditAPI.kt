package com.chalo.fieldauditapp

import com.chalo.fieldauditapp.model.CreateAuditRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CreateAuditAPI {

    @Headers("Content-Type: application/json" , "Authorization: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBsaWNhdGlvbiI6eyJpZCI6MTcsIm5hbWUiOiJBRkNTX1N0YWdpbmciLCJ1cmwiOiJodHRwOi8vc3RhZ2luZ2FmY3MuY2hhbG8uY29tIiwiaW5hY3RpdmUiOmZhbHNlLCJnb29nbGVBdXRoIjp0cnVlLCJmYXR0VG9rZW4iOmZhbHNlfSwidXNlcl9uYW1lIjoic2hpdmFtLnNpc29kaXlhQHZvZ28uaW4iLCJzY29wZSI6WyJBRkNTX1ByZXByb2QiXSwibmFtZSI6IlNoaXZhbSIsIm1vYmlsZSI6IjczODk1MjA4MjQiLCJleHAiOjE2OTIzODc0ODUsInVzZXJJZCI6NjQ4OCwibG9naW5Nb2RlIjoiZ29vZ2xlIiwiYXV0aG9yaXRpZXMiOlt7ImFwcElkIjoxNywicm9sZXMiOlt7InJvbGUiOiJBZG1pbiIsInJvbGVJbmZvIjpbW3siaWQiOjYyLCJuYW1lIjoiQ2l0eSIsInZhbHVlIjoiTXVtYmFpIiwibWV0YUlkIjo4MjIsImNpdHlJZCI6IjIyMDkxODU4MiJ9LHsiaWQiOjYzLCJuYW1lIjoiQWdlbmN5IiwidmFsdWUiOiJXYWRhbGEgRGVwb3QiLCJtZXRhSWQiOjgyMywiYWdlbmN5SWQiOiIyMjA5MTg1ODMifSx7ImlkIjo2NCwibmFtZSI6IkRlcG90IiwidmFsdWUiOiJXYWRhbGEgRGVwb3QiLCJtZXRhSWQiOjgyNCwiZGVwb3RJZCI6IjIyMDkxODU4NCJ9XV19XX1dLCJqdGkiOiJmYWM2OTFmZC05MmMzLTQzMzktOTUwYy0xNjgzNzczZTNhZDEiLCJjbGllbnRfaWQiOiJwcmVwcm9kX2FmY3MifQ.MiIzBX_To9-W1zOyDSMl7POIQrSAL8_5EoMqQ97Xx8dQTjX67veLtwUoBeUhsoMdBm0o9hTHAgkg042yWPmcqlPXwCqKNVty9vYHjj_nGMzcsgfNwATYwRz5HyDW1Pp0EV8aJkesaZ1P2-Qf9cxOClIGWiApU98W-J6cH911wrq14nq2o8DpfrYI3xkCHsF9rv-RmYknF6lH3YHcl_8kT3i9BaKwJdwrAO0CjPiYVGlaBCwAqaW99CclorpfSnZtMYo4IfGJK9Y-oa1Pb2taxFRNFQ6f6g9CW7UNh0BpfwGdNNEpn9xmcoHJB3ObXiMYE64wAg4Nd_HGei36GB67GA")
    @POST("create_field_audit")
    fun sendAuditData(
        @Body createAuditRequest: CreateAuditRequest
    ):Call<String>
}
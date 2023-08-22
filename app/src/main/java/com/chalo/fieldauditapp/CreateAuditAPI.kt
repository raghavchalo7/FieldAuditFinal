package com.chalo.fieldauditapp

import com.chalo.fieldauditapp.model.AuditReportRequestItem
import com.chalo.fieldauditapp.model.CreateAuditRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface CreateAuditAPI {

    @Headers("Authorization:eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBsaWNhdGlvbiI6eyJpZCI6MTcsIm5hbWUiOiJBRkNTX1N0YWdpbmciLCJ1cmwiOiJodHRwOi8vc3RhZ2luZ2FmY3MuY2hhbG8uY29tIiwiaW5hY3RpdmUiOmZhbHNlLCJnb29nbGVBdXRoIjp0cnVlLCJmYXR0VG9rZW4iOmZhbHNlfSwidXNlcl9uYW1lIjoic2hpdmFtLnNpc29kaXlhQHZvZ28uaW4iLCJzY29wZSI6WyJBRkNTX1ByZXByb2QiXSwibmFtZSI6IlNoaXZhbSIsIm1vYmlsZSI6IjczODk1MjA4MjQiLCJleHAiOjE2OTI2MzcyOTgsInVzZXJJZCI6NjQ4OCwibG9naW5Nb2RlIjoiZ29vZ2xlIiwiYXV0aG9yaXRpZXMiOlt7ImFwcElkIjoxNywicm9sZXMiOlt7InJvbGUiOiJBZG1pbiIsInJvbGVJbmZvIjpbW3siaWQiOjYyLCJuYW1lIjoiQ2l0eSIsInZhbHVlIjoiTXVtYmFpIiwibWV0YUlkIjo4MjIsImNpdHlJZCI6IjIyMDkxODU4MiJ9LHsiaWQiOjYzLCJuYW1lIjoiQWdlbmN5IiwidmFsdWUiOiJXYWRhbGEgRGVwb3QiLCJtZXRhSWQiOjgyMywiYWdlbmN5SWQiOiIyMjA5MTg1ODMifSx7ImlkIjo2NCwibmFtZSI6IkRlcG90IiwidmFsdWUiOiJXYWRhbGEgRGVwb3QiLCJtZXRhSWQiOjgyNCwiZGVwb3RJZCI6IjIyMDkxODU4NCJ9XV19XX1dLCJqdGkiOiI4MDA2ZGM5Zi05Nzc1LTQ0YjQtYWU2Ny01Y2Q1ZTY1ZGY3MWMiLCJjbGllbnRfaWQiOiJwcmVwcm9kX2FmY3MifQ.UzUusUMcjUo3KCBtBrQhlrK2RdFqOPSKcRI7XsfdURHtvHnQfN_7Dj2NQycQxAez-M1Bl-0cPn2Mctcgdv8DRjdvdvI4DI2vgyUzM2kuC7puNghsnZYIiDz7ZDRIw1FrPk5peQJ6BBK0MTTnFoew5mTqCAOJwoVOx1ZGBjsxFB1TNzn8LI1pfkhKJ9nw8w9P8HWizvHjvWuzddXpEDzdEzfckHj39QBXx9wD1TfEqwg6-ZsXE9Q3CVu3gfhF-3jMI8B-rmHVMWPdKFQw1JfyuCLziGzVGjoeDeW4__YivVAj83DEknxy4BcMGOht1SKGeDqG0S11g8jK_P2eDANcoA")
    @POST("create_field_audit")
    fun sendAuditData(
        @Body createAuditRequest: CreateAuditRequest
    ):Call<String>

    @Headers("Authorization:eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBsaWNhdGlvbiI6eyJpZCI6MTcsIm5hbWUiOiJBRkNTX1N0YWdpbmciLCJ1cmwiOiJodHRwOi8vc3RhZ2luZ2FmY3MuY2hhbG8uY29tIiwiaW5hY3RpdmUiOmZhbHNlLCJnb29nbGVBdXRoIjp0cnVlLCJmYXR0VG9rZW4iOmZhbHNlfSwidXNlcl9uYW1lIjoic2hpdmFtLnNpc29kaXlhQHZvZ28uaW4iLCJzY29wZSI6WyJBRkNTX1ByZXByb2QiXSwibmFtZSI6IlNoaXZhbSIsIm1vYmlsZSI6IjczODk1MjA4MjQiLCJleHAiOjE2OTI3MjUyMTcsInVzZXJJZCI6NjQ4OCwibG9naW5Nb2RlIjoiZ29vZ2xlIiwiYXV0aG9yaXRpZXMiOlt7ImFwcElkIjoxNywicm9sZXMiOlt7InJvbGUiOiJBZG1pbiIsInJvbGVJbmZvIjpbW3siaWQiOjYyLCJuYW1lIjoiQ2l0eSIsInZhbHVlIjoiTXVtYmFpIiwibWV0YUlkIjo4MjIsImNpdHlJZCI6IjIyMDkxODU4MiJ9LHsiaWQiOjYzLCJuYW1lIjoiQWdlbmN5IiwidmFsdWUiOiJXYWRhbGEgRGVwb3QiLCJtZXRhSWQiOjgyMywiYWdlbmN5SWQiOiIyMjA5MTg1ODMifSx7ImlkIjo2NCwibmFtZSI6IkRlcG90IiwidmFsdWUiOiJXYWRhbGEgRGVwb3QiLCJtZXRhSWQiOjgyNCwiZGVwb3RJZCI6IjIyMDkxODU4NCJ9XV19XX1dLCJqdGkiOiI4ZDE2NTVhNS0wN2M0LTQ5OTctOWVkNS0wZDM5NmQ5NzUyZDUiLCJjbGllbnRfaWQiOiJwcmVwcm9kX2FmY3MifQ.isXD8r_5JrBa8a_Sqr80wTKyWWl_jbjPFXrtv6_cWOXVgTsvIC6InLe0JhobZD5xMKZdEJRNO2ag6C0eL1XOTvuuri4c19gV-Yj3iR-R1EUr-8NDnt4u7tCwi2YjvfD9ekUgU-HTCjenc9hIJcrtIXPjEnd39DYv5mldar844tDYCR7kHpX25YiGIlS6Z2g6sCC9Z50raSlkQfc05Ue96I76voHEwN-IQQ0e6Fzxbe-FVqSv8wFVto8PxSt6R9zTzrUm9u2kwoeZy-Fmom7qeU8blgBxxK9ZYDrYRo-zLPZQmtGRhMkY5ePk_0iEaVKPlExmC5_UfMZgFRjpPOjuoQ")
    @GET("audits-by-crew?crewId=1")
    fun getAuditReports(
    ):Call<ArrayList<AuditReportRequestItem>>
}
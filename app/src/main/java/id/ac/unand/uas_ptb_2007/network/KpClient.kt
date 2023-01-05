package id.ac.unand.uas_ptb_2007.network

import id.ac.unand.uas_ptb_2007.models.*
import retrofit2.Call
import retrofit2.http.*

interface KpClient {
    @FormUrlEncoded
    @POST("/api/login/")
    fun login(@Field("username") username: String, @Field("password") password:String): Call<LoginResponse>;

    @POST("/api/logout")
    fun logout(@Header("Authorization") token: String): Call<LogoutResponse>

    @GET("/api/my-internship/{id}/logbook")
    fun listlogbook(@Header("Authorization") token: String,
                    @Path("id") id:Int
    ):Call<ListLogBookResponse>

    @GET("/api/my-internship/{id}/logbook/{id_logbook}")
    fun detailLogbookMaha(@Header("Authorization") token: String,
                      @Path("id") id: Int,
                      @Path("id_logbook") id_logbook: String?
    ):Call<DetailLogBookResponse>
    @FormUrlEncoded
    @PATCH("/api/internship-students/{id}/logbook/{id_logbook}")
    fun isirespon(@Header("Authorization") token: String,
                      @Path("id") id: Int,
                      @Path("id_logbook") id_logbook: String?,
                      @Field("status") status:Int,
                      @Field("note") note:String,
    ):Call<IsiResponResponse>
}
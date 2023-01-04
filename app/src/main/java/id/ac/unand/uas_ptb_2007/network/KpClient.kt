package id.ac.unand.uas_ptb_2007.network

import id.ac.unand.uas_ptb_2007.models.ListLogBookResponse
import id.ac.unand.uas_ptb_2007.models.LoginResponse
import id.ac.unand.uas_ptb_2007.models.LogoutResponse
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
}
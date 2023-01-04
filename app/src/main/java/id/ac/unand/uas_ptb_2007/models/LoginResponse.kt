package id.ac.unand.uas_ptb_2007.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("authorisation")
    val authorisation: Authorisation? = null,

    @field:SerializedName("user")
    val user: User? = null,

    @field:SerializedName("status")
    val status: String? = null
)
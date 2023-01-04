package id.ac.unand.uas_ptb_2007.models

import com.google.gson.annotations.SerializedName

data class LogoutResponse(

    @field:SerializedName("message")
    val message: String? = null
)
package id.ac.unand.uas_ptb_2007.models

import com.google.gson.annotations.SerializedName

data class Authorisation(

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("token")
    val token: String? = null
)
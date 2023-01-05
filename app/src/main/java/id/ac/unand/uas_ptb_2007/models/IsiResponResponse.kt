package id.ac.unand.uas_ptb_2007.models

import com.google.gson.annotations.SerializedName

data class IsiResponResponse(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)
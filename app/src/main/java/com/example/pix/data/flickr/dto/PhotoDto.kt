package com.example.pix.data.flickr.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PhotoDto(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val title: String,
    @SerializedName("ispublic")
    val isPublic: Int,
    @SerializedName("isfriend")
    val isFriend: Int,
    @SerializedName("isfamily")
    val isFamily: Int,
)
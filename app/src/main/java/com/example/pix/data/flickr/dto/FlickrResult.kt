package com.example.pix.data.flickr.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class FlickrResult(
    @SerializedName("photos")
    val photos: PhotosDto?,
    val stat: String?,
    val code: Int?,
    val message: String?,
)
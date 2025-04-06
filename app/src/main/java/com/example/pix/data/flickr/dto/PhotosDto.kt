package com.example.pix.data.flickr.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PhotosDto(
    val page: Int,
    val pages: Int,
    @SerializedName("perpage")
    val perPage: Int,
    val photo: List<PhotoDto>,
    val total: Int
)
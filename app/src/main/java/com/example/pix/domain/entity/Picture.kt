package com.example.pix.domain.entity

data class Picture(
    val title: String,
    val server: String,
    val id: String,
    val secret: String,
) {
    fun createUrl(sizeSuffix: String = "w"): String {
        return "https://live.staticflickr.com/${server}/${id}_${secret}_${sizeSuffix}.jpg"
    }
}
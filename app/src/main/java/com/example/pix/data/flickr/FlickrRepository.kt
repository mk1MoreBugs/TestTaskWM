package com.example.pix.data.flickr

import com.example.pix.domain.entity.Picture

interface FlickrRepository {
    suspend fun searchPhotos(
        text: String = "cats",
        page: Int = 1,
        count: Int = 100,
    ): List<Picture>
}
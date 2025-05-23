package com.example.pix.data.flickr

import android.util.Log
import com.example.pix.data.flickr.dto.FlickrResult
import com.example.pix.data.flickr.mapper.toPicture
import com.example.pix.domain.entity.Picture
import javax.inject.Inject

class FlickrRepositoryImpl @Inject constructor(
    private val flickrApi: FlickrApi,
) : FlickrRepository {
    override suspend fun searchPhotos(
        text: String,
        page: Int,
        count: Int,
    ): List<Picture> {
        try {
            val result: FlickrResult = flickrApi.search(text, page, count)
            val  listPictures = result.photos?.photo?.map { it.toPicture() }
            return listPictures ?: listOf()
        } catch (e: Exception) {
            Log.e("Error retrofit", e.message.toString())
            return listOf()
        }
    }
}
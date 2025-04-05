package com.example.pix.data.flickr

import com.example.pix.data.flickr.dto.FlickrResult
import com.example.pix.data.flickr.mapper.toPicture
import com.example.pix.domain.entity.Picture

class FlickrRepositoryImpl(
    private val flickrApi: FlickrApi,
) : FlickrRepository {
    override suspend fun searchPhotos(
        text: String,
        page: Int,
        count: Int,
    ): List<Picture> {
        val result: FlickrResult = flickrApi.search(text, page, count)
        val  listPictures = result.photos?.photo?.map { it.toPicture() }
        return listPictures ?: listOf()
    }
}
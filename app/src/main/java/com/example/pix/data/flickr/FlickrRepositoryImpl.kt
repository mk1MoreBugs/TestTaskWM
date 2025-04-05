package com.example.pix.data.flickr

import com.example.pix.domain.entity.Picture

class FlickrRepositoryImpl(
    private val flickrApi: FlickrApi,
) : FlickrRepository {
    override suspend fun search(
        text: String,
        page: Int,
        count: Int
    ): Result<List<Picture>> = runCatching {
        val result = flickrApi.search(text, page, count)
        TODO()
    }
}
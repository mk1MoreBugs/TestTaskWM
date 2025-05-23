package com.example.pix.data.flickr.mapper

import com.example.pix.data.flickr.dto.PhotoDto
import com.example.pix.domain.entity.Picture

// https://www.flickr.com/services/api/misc.urls.html
fun PhotoDto.toPicture(): Picture = Picture(
    title = title,
    server = server,
    id = id,
    secret = secret,
)

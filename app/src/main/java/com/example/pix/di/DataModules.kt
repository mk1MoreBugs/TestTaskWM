package com.example.pix.di

import com.example.pix.data.flickr.FlickrApi
import com.example.pix.data.flickr.FlickrRepository
import com.example.pix.data.flickr.FlickrRepositoryImpl
import com.example.pix.data.flickr.FlickrRetrofit
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindRepository (repository: FlickrRepositoryImpl): FlickrRepository
}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = FlickrRetrofit.create()

    @Singleton
    @Provides
    fun provideFlickrApi(flickrRetrofit: Retrofit): FlickrApi {
        return flickrRetrofit.create(FlickrApi::class.java)
    }
}
